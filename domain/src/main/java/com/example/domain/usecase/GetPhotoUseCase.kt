package com.example.domain.usecase

import com.example.domain.repository.PhotoRepository
import com.example.domain.usecase.base.BaseUseCase
import com.example.domain.usecase.base.Result

class GetPhotoUseCase(private val photoRepository: PhotoRepository) : BaseUseCase<Int>() {

    override suspend fun run(params: Int) {
        // started loading
        resultChannel.send(Result.State.Loading)

        //Get person from persistence and send it, synchronous
        resultChannel.send(photoRepository.getPhotos(params))

        resultChannel.send(Result.State.Loaded)
    }
}