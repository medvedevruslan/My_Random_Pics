package com.example.cleanarchitecturerandompics.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.cleanarchitecturerandompics.base.BaseViewModel
import com.example.domain.models.Photo
import com.example.domain.usecase.GetPhotoUseCase
import com.example.domain.usecase.base.Error
import com.example.domain.usecase.base.Result
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ReceiveChannel

@ObsoleteCoroutinesApi
class PhotoListViewModel(private val getPhotosUseCase: GetPhotoUseCase) : BaseViewModel() {


    override val receiveChannel: ReceiveChannel<Result<Any, Error>>
        get() = getPhotosUseCase.receiveChannel

    private val _items = MutableLiveData<List<Photo>>().apply { value = emptyList() }
    val items: LiveData<List<Photo>> = _items

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    // This LiveData depends on another so we can use a transformation.
    val empty: LiveData<Boolean> = Transformations.map(_items) {
        it.isEmpty()
    }

    var page: Int = 1


    override fun resolve(value: Result<Any, Error>) {
        value.handleResult(::handleSuccess, ::handleFailure, ::handleState)
    }

    fun requestItems() {
        getPhotosUseCase(page)
    }

    fun refresh() {
        _items.value = emptyList()
        page = 1
        requestItems()
    }

    fun handleSuccess(data: Any) {
        val oldItems = _items.value!!
        val items = oldItems + (data as List<Photo>)
        _items.postValue(items)
        page++
    }

    fun handleFailure(error: Error) {

    }

    fun handleState(state: Result.State) {
        _dataLoading.postValue(state is Result.State.Loading)
    }
}