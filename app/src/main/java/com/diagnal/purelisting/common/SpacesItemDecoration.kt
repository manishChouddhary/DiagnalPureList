package com.diagnal.purelisting.common

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacesItemDecoration(val sideSpace : Int, val bottomSpace: Int, val topSpace: Int, val orentationSpan: Int): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent:RecyclerView ,  state:RecyclerView.State){
        outRect.left = sideSpace
        outRect.right = sideSpace
        outRect.bottom = bottomSpace

        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildLayoutPosition(view) in 0 until orentationSpan) {
            outRect.top = topSpace
        } else {
            outRect.top = 0
        }
    }
}

