package com.seif.pagingv3demo.util

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.seif.pagingv3demo.model.CharacterData
import com.seif.pagingv3demo.network.ApiService
import com.seif.pagingv3demo.util.Constants.Companion.FIRST_PAGE_INDEX
import java.lang.Exception

class CharacterPagingSource(private val apiService: ApiService) :
    PagingSource<Int, CharacterData>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterData>): Int? {
        return state.anchorPosition
        // Most recently accessed index in the list, including placeholders. null if no access in the PagingData has been made yet.
        // E.g., if this snapshot was generated before or during the first load.
    }

    // trigger api request and turns our results into pages
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterData> { // will be called again and again when we loading the pages next to each other
        val position = params.key ?: FIRST_PAGE_INDEX // will be null when it's first time

        return try {
            val response = apiService.getDataFromApi(position)
            LoadResult.Page(
                data = response.characterData,
                prevKey = if (position == FIRST_PAGE_INDEX) null else position-1,
                nextKey = if(response.characterData.isEmpty()) null else position+1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


    //        return try {
//            val nextPages = params.key ?: FIRST_PAGE_INDEX
//            val response = apiService.getDataFromApi(nextPages)
//
//            var nextPageNumber: Int? = null
//            if(response?.info?.next != null) {
//                val uri = Uri.parse(response?.info?.next!!)
//                val nextPageQuery = uri.getQueryParameter("page")
//                nextPageNumber = nextPageQuery?.toInt()
//            }
//            var prevPageNumber: Int? = null
//            if(response?.info?.prev != null) {
//                val uri = Uri.parse(response?.info?.prev!! as String?)
//                val prevPageQuery = uri.getQueryParameter("page")
//
//                prevPageNumber = prevPageQuery?.toInt()
//            }
//
//            LoadResult.Page(
//                data = response.characterData,
//                prevKey = prevPageNumber,
//                nextKey = nextPageNumber
//            )
//        } catch (e: Exception) {
//            LoadResult.Error(e)
//        }
//    }


}
// the best part of the version 3 library is we can handle error since there is an exception we can pass it and we can handle this exception