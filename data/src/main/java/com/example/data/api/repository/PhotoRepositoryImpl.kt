package com.example.data.api.repository

import com.example.data.api.service.PhotoService
import com.example.domain.models.Photo
import com.example.domain.repository.PhotoRepository
import com.example.domain.usecase.base.Error
import com.example.domain.usecase.base.Result
import java.lang.Exception

class PhotoRepositoryImpl(val photoService: PhotoService) : PhotoRepository {

    override suspend fun getPhotos(page: Int): Result<List<Photo>, Error> {
        try {
            val response = photoService.getPhotos(page = page)

            if (response.isSuccessful && response.body() != null) {
                return Result.Success(response.body()!!.map {
                    return@map Photo(
                        it.id,
                        url = it.imageUrls.full,
                        color = it.color,
                        width = it.width,
                        height = it.height

                    )
                })
            } else {
                return Result.Failure(Error.ResponseError)
            }
        } catch (error: Exception) {
            return Result.Failure(Error.NetworkError)
        }
    }
}