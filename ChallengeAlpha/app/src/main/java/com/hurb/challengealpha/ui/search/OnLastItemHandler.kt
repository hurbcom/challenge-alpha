package com.hurb.challengealpha.ui.search

//Used to provide communication between adapter and activity
interface OnLastItemHandler {
    //Is called when adapter is in last item. Implemented in activity
    fun onLastItem()
}