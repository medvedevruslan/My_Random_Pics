package com.example.cleanarchitecturerandompics.extensions

import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchitecturerandompics.utils.EndlessRecyclerOnScrollListener

fun RecyclerView.onLoadMore(block: () -> Unit) {
    this.clearOnScrollListeners()
    this.addOnScrollListener(object : EndlessRecyclerOnScrollListener() {
        override fun onLoadMore() {
            block.invoke()
        }
    })
}