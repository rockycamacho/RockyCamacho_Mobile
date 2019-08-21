package com.rockycamacho.willow.testapp.presentation.buildings.list

import android.app.Application
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import com.rockycamacho.willow.testapp.data.network.ApiService
import com.rockycamacho.willow.testapp.data.network.models.Building
import com.rockycamacho.willow.testapp.presentation.buildings.BuildingFilter
import com.ww.roxie.BaseViewModel
import com.ww.roxie.Reducer
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.rxkotlin.ofType
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class ListBuildingsViewModel @Inject constructor(
    private val application: Application,
    private val apiService: ApiService
) : BaseViewModel<Action, State>() {

    override val initialState = State(false)

    private val reducer: Reducer<State, Change> = { state, change ->
        when (change) {
            is Change.Loading -> state.copy(
                isLoading = true,
                exception = null
            )
            is Change.FilterData -> state.copy(
                isLoading = false,
                filter = change.filter,
                filteredData = filterBuildings(state.data, change.filter),
                exception = null
            )
            is Change.Success -> state.copy(
                isLoading = false,
                data = change.data,
                filteredData = filterBuildings(change.data, state.filter),
                exception = null
            )
            is Change.Error -> state.copy(
                isLoading = false,
                exception = change.exception
            )
        }
    }

    private fun filterBuildings(
        data: List<Building>,
        filter: BuildingFilter?
    ): List<Building> {
        if(filter == null) {
            return data
        }
        if(filter.cities.isEmpty()) {
            return data
        }
        val list = data.filter { filter.cities.contains(it.address!!.city) }
        Timber.d("unfiltered data: %s", data)
        Timber.d("filtered list: %s", list)
        return list
    }

    init {
        bindActions()
    }

    private fun bindActions() {
        val fetchItemsChange = actions.ofType<Action.FetchBuildings>()
            .switchMap { ReactiveNetwork.observeNetworkConnectivity(application) }
            .switchMap { action ->
                apiService.getBuildings()
                    .subscribeOn(Schedulers.io())
                    .toObservable()
                    .map<Change> { Change.Success(it) }
                    .onErrorReturn { Change.Error(it) }
                    .startWith(Change.Loading)
            }
        val filterDataChange = actions.ofType<Action.ChangeFilter>()
            .switchMap { action ->
                Single.just(action.filters)
                    .subscribeOn(Schedulers.io())
                    .toObservable()
                    .map<Change> { Change.FilterData(it) }
                    .onErrorReturn { Change.Error(it) }
                    .startWith(Change.Loading)
            }

        var allChanges = Observable.merge(fetchItemsChange, filterDataChange)

        disposables += allChanges
            .scan(initialState, reducer)
            .distinctUntilChanged()
            .subscribe(state::postValue, Timber::e)
    }

}
