package br.com.rvvaranda.hu.Helper

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class RecycleViewPagination
    (var layoutManager: LinearLayoutManager): RecyclerView.OnScrollListener() {

    abstract fun lastPage(): Boolean
    abstract fun loadPage(): Boolean

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItens = layoutManager.childCount
        val totalItens = layoutManager.itemCount
        val firstItemPosition = layoutManager.findFirstVisibleItemPosition()

        if(!loadPage() && !lastPage()){
            if(visibleItens + firstItemPosition >= totalItens && firstItemPosition >= 0) {
                loadMoreHotels()
            }
        }
    }

    abstract fun loadMoreHotels()
}