package com.seif.pagingv3demo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.seif.pagingv3demo.model.CharacterData
import com.seif.pagingv3demo.network.ApiService
import com.seif.pagingv3demo.util.CharacterPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    fun getListData(): Flow<PagingData<CharacterData>> {
        return Pager(config = PagingConfig(
            pageSize = 3,
            maxSize = 100, // max items in recycler view
        enablePlaceholders = false),
            pagingSourceFactory = { CharacterPagingSource(apiService) }).flow.cachedIn(
            viewModelScope
        )
    }
}