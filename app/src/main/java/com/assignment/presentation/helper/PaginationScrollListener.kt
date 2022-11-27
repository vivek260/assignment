package com.assignment.presentation.helper

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationScrollListener(
        private val layoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener() {

    private var isLoading = false

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val lastVisibleItemIndex = layoutManager.findLastVisibleItemPosition()
        val count = recyclerView.adapter?.itemCount ?: 0
        if (count > 0 && lastVisibleItemIndex > count - 3
                && !isLoading && !isAllLoaded()) {
            if (!isLoading) {
                setLoadInProgress()
                loadMore()               //load next set of data
            }
        }
    }

    fun loadCompleted() {
        isLoading = false
    }

    private fun setLoadInProgress() {
        isLoading = true
    }

    abstract fun loadMore()

    abstract fun isAllLoaded(): Boolean
}