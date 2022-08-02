package com.seif.pagingv3demo.util

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.seif.pagingv3demo.model.CharacterData
import com.seif.pagingv3demo.network.ApiService
import com.seif.pagingv3demo.util.Constants.Companion.FIRST_PAGE_INDEX
import java.lang.Exception

class CharacterPagingSource(private val apiService: ApiService) : PagingSource<Int, CharacterData>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterData>): Int? {
        return state.anchorPosition
        // Most recently accessed index in the list, including placeholders. null if no access in the PagingData has been made yet.
        // E.g., if this snapshot was generated before or during the first load.
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterData> { // will be called again and again when we loading the pages next to each other
        return try {
            val nextPages = params.key ?: FIRST_PAGE_INDEX
            val response = apiService.getDataFromApi(nextPages)
            val nextPageNumber: Int?
            // check page number
            val uri = Uri.parse(response.info.next)
            val nextPageQuery = uri.getQueryParameter("page")
            nextPageNumber = nextPageQuery?.toInt()
            LoadResult.Page(
                data = response.characterData,
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}
// the best part of the version 3 library is we can handle error since there is an exception we can pass it and we can handle this exception