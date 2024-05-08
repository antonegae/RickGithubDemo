package com.rick.githubtour.view.main.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rick.githubtour.model.UserListResponse
import com.rick.githubtour.repository.githubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject
import com.rick.githubtour.utils.Event

@HiltViewModel
class GitHubUserMainActivityViewModel @Inject constructor(private var repository: githubRepository)
    :ViewModel() {

    private val TAG = GitHubUserMainActivityViewModel::class.java.simpleName

    val userList: MutableLiveData<List<UserListResponse>> = MutableLiveData()
    var userListResponse: List<UserListResponse>? = null

    private val _isError = MutableLiveData<Event<String>>()
    val isError: LiveData<Event<String>> get() = _isError

    init {
        getUserList(0,70)
    }

    fun getUserList(since: Int, perpage :Int) = viewModelScope.launch {

        try {

            val response = repository.callGithubApiService(since,perpage)
            if (response.isSuccessful) {
                userList.postValue(response.body())
            }else {
                _isError.postValue(Event("讀取失敗，請稍後再試 ${response.errorBody().toString()}"))
            }
        } catch (e: Exception) {

            Log.d(TAG, "Error: ${e.localizedMessage}")
            _isError.postValue(Event("讀取失敗，請稍後再試 ${e.localizedMessage}"))

        }
    }
    //function handling paging (not finished yet)/*
    private fun handleUserListResponse(response: Response<List<UserListResponse>>) : Result<List<UserListResponse>> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                //since=0 shift?//perpage maintains the same value?
                //since+=20? for next time we call again getUserList(since,perpage)
                if(userListResponse == null) {
                    userListResponse = resultResponse
                } else {
                    val oldUserList = userListResponse
                    val newUserList = resultResponse
                    //oldUserList.addAll(newUserList)
                }
                return Result.success(userListResponse ?: resultResponse)
            }
        }
        return Result.failure(exception = Throwable("Error"))
    }



}