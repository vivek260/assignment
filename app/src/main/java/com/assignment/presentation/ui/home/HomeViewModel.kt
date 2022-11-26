package com.assignment.presentation.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.assignment.di.db.UsersDao
import com.assignment.di.entity.RepoEntity
import com.assignment.domain.entities.RepoListWrapperResponse
import com.assignment.domain.usecase.RepoListUseCase
import com.assignment.presentation.model.RepoListResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val dao: UsersDao) : ViewModel() {
    private val userUserCase by lazy { RepoListUseCase() }
    val repoList = MutableLiveData<List<RepoListResponse>>()
    val onError = MutableLiveData<Boolean>()
    val repoFromDatabase = MutableLiveData<List<RepoEntity>>()
    var pageNo = 1
    fun searchRepoList(query: String) {
        userUserCase.fetchRepoList(query, pageNo, ::onApiSuccess, ::onApiFailure)
    }

    private fun onApiSuccess(list: List<RepoListWrapperResponse>) {
        val data =  list.map {RepoListResponse(it.id,it.node_id,it.name,it.description,it.created_at,it.git_url)}
        repoList.value = data
//        CoroutineScope(Dispatchers.IO).launch {
//            //data loaded to the entity
//            list.map {
//                val repoEntity = RepoEntity(
//                    it.id,
//                    it.node_id,
//                    it.name,
//                    it.description,
//                    it.created_at,
//                    it.git_url
//                )
//                dao.insertRepo(repoEntity)
//            }
//            //using dao to insert data to the database
//        }
    }

    private fun onApiFailure(exception: Exception) {
        onError.value = true
    }

    fun getAllRepo() {
        CoroutineScope(Dispatchers.IO).launch {
            val list = dao.getAllRepo()
            repoFromDatabase.postValue(list)
        }
    }
}