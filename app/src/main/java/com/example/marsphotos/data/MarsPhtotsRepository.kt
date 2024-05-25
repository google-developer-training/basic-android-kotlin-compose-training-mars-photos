package com.example.marsphotos.data

import com.example.marsphotos.model.MarsPhoto

interface MarsPhotosRepository {

    suspend fun getMarsPhotos(): List<MarsPhoto>


}

class NetworkMarsPhotosRepository():MarsPhotosRepository{
    override suspend fun getMarsPhotos(): List<MarsPhoto> {
        TODO("Not yet implemented")
    }
}