package com.rick.githubtour.utils

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rick.githubtour.model.UserListResponse

@Dao
interface GithubUsersDao {

    //@Insert(onConflict = OnConflictStrategy.REPLACE)
    //suspend fun insertAll(tourItems: MutableList<UserListResponse>)

    //@Query("SELECT * FROM UserListResponse")
    //fun getAll(): LiveData<MutableList<UserListResponse>>

    //@Delete
    //suspend fun delete(userListResponse: UserListResponse)

    //@Query("SELECT * FROM UserListResponse WHERE name LIKE :searchQuery")
    //fun searchTourItems(searchQuery: String): LiveData<List<UserListResponse>>

}