package com.assignment.presentation.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.assignment.di.db.UsersDao
import com.assignment.di.entity.RepoEntity
import com.assignment.domain.entities.RepoListWrapperResponse
import com.assignment.domain.usecase.RepoListUseCase
import com.assignment.presentation.model.OwnerResponse
import com.assignment.presentation.model.RepoListResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val dao: UsersDao) : ViewModel() {
    private val repoUseCase by lazy { RepoListUseCase() }
    val repoList = MutableLiveData<List<RepoListResponse>>()
    val onError = MutableLiveData<Boolean>()
    var pageNo = 1
    fun searchRepoList(query: String) {
        repoUseCase.fetchRepoList(query, pageNo, ::onApiSuccess, ::onApiFailure)
    }

    private fun onApiSuccess(list: List<RepoListWrapperResponse>) {
        val data =  list.map {RepoListResponse(it.id,it.node_id,it.name,it.description,it.updated_at,it.html_url,it.contributors_url,it.owner.let {it1-> OwnerResponse(it1.login,it1.avatar_url)})}
        repoList.value = data
    }

    private fun onApiFailure(exception: Exception) {
        onError.value = true
    }

    fun getAllRepo() {
        CoroutineScope(Dispatchers.IO).launch {
            val list = dao.getAllRepo()
//            repoFromDatabase.postValue(list)
        }
    }
}