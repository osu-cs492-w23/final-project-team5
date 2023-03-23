package com.example.myapplication_final

class BookmarkedActivitiesRepository (private val dao: dao){
    suspend fun insertBookmarkedActivity(data: BoredData) = dao.insert(data)
    suspend fun deleteBookmarkedActivity(data: BoredData) = dao.delete(data)
}