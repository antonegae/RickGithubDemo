package com.rick.githubtour.view.main.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rick.githubtour.model.UserInfo
import com.rick.githubtour.model.UserListResponse
import com.rick.githubtour.repository.githubRepository
import com.rick.githubtour.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor(private var repository: githubRepository)
    :ViewModel() {

    private val TAG = UserInfoViewModel::class.java.simpleName

    val userInfo: MutableLiveData<UserInfo> = MutableLiveData()
    private val _isError = MutableLiveData<Event<String>>()
    val isError: LiveData<Event<String>> get() = _isError

    //pass login(user login "name") to get user's data
    fun getUserInfo(name:String) = viewModelScope.launch {
        try {
             val response = repository.callGithubApiServiceInfo(name)
             if (response.isSuccessful) {
                 userInfo.postValue(response.body())
             }
             else {
                 _isError.postValue(Event("讀取失敗，請稍後再試 ${response.errorBody().toString()}"))
             }
        } catch (e: Exception) {

            Log.d(TAG, "Error: ${e.localizedMessage}")
            _isError.postValue(Event("讀取失敗，請稍後再試 ${e.localizedMessage}"))

        }
    }
}