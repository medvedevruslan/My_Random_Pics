package com.example.domain.repository

import com.example.domain.models.Photo
import com.example.domain.usecase.base.Result
import com.example.domain.usecase.base.Error

/**
 * Interface that represents a Repository for getting [Photo] related data.
 */
interface PhotoRepository {

    /**
     * Get a List of [Photo].
     */
    suspend fun getPhotos(page: Int): Result<List<Photo>, Error>
}