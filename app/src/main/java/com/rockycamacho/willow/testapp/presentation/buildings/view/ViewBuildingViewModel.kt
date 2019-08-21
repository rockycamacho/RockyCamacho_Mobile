package com.rockycamacho.willow.testapp.presentation.buildings.view

import com.ww.roxie.BaseViewModel
import com.ww.roxie.Reducer
import io.reactivex.Single
import io.reactivex.rxkotlin.ofType
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class ViewBuildingViewModel @Inject constructor() : BaseViewModel<Action, State>() {

    override val initialState = State(false)

    private val reducer: Reducer<State, Change> = { state, change ->
        when (change) {
            is Change.Loading -> state.copy(
                isLoading = true,
                exception = null
            )
            is Change.Success -> state.copy(
                isLoading = false,
                data = change.data,
                exception = null
            )
            is Change.Error -> state.copy(
                isLoading = false,
                exception = change.exception
            )
        }
    }

    init {
        bindActions()
    }

    private fun bindActions() {
        val fetchBuildingChange = actions.ofType<Action.FetchBuilding>()
            .switchMap { action ->
                Single.just(action.building)
                    .subscribeOn(Schedulers.io())
                    .toObservable()
                    .map<Change> { Change.Success(it) }
                    .onErrorReturn { Change.Error(it) }
                    .startWith(Change.Loading)
            }


        disposables += fetchBuildingChange
            .scan(initialState, reducer)
            .distinctUntilChanged()
            .subscribe(state::postValue, Timber::e)
    }

}
