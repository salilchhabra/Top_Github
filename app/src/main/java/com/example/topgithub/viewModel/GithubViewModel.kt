package com.example.topgithub.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.topgithub.base.BaseViewModel
import com.example.topgithub.base.Result
import com.example.topgithub.model.Items
import com.example.topgithub.repository.MyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GithubViewModel : BaseViewModel() {
    val successLiveData by lazy { MutableLiveData<List<Items>>() }
    val errorLiveData by lazy { MutableLiveData<String>() }
    private val myLauncherRepository by lazy { MyRepository() }

    fun getGithubDetails(lang: String) {
        viewModelScope.launch {
            when (val result =
                withContext(Dispatchers.IO) { myLauncherRepository.getGithubRepo(lang) }) {
                is Result.Success<*> -> {
                    successLiveData.value = result.data
                }
                is Result.Error -> {
                    errorLiveData.value = result.exception
                }
            }
        }
    }
}