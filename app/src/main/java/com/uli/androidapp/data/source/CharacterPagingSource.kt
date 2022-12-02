package com.uli.androidapp.data.source

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.uli.androidapp.data.network.api.ApiService
import com.uli.androidapp.domain.network.model.CharacterResult
import retrofit2.HttpException
import javax.inject.Inject

private const val START_PAGE = 1

class CharacterPagingSource @Inject constructor(
    private val api: ApiService,
    private val name: String,
    private val status: String,
    private val gender: String
) : PagingSource<Int, CharacterResult>() {

    override fun getRefreshKey(state: PagingState<Int, CharacterResult>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterResult> {
        val pageNumber = params.key ?: START_PAGE
        return try {
            val response = api.getCharacter(
                page = pageNumber,
                name = name,
                status = status,
                gender = gender
            )
            var nextPage: Int? = null
            if (response.body()?.info?.next != null) {
                val url = Uri.parse(response.body()?.info?.next)
                val nextPageQuery = url.getQueryParameter("page")
                nextPage = nextPageQuery?.toInt()
            }
            LoadResult.Page(
                data = response.body()!!.results,
                prevKey = if (pageNumber == START_PAGE) null else pageNumber - 1,
                nextKey = nextPage
            )
        } catch (e: HttpException) {
            LoadResult.Error(e.fillInStackTrace())
        }
    }
}