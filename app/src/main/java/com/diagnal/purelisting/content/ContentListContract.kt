package com.diagnal.purelisting.content

import com.diagnal.purelisting.model.Content

//Contract class between view and presenter
interface ContentListContract {
    interface Presenter{
        fun attachView(view: View)
        fun detachView()
        fun getContentPage(page: Int)
        fun applyFilter(filterText: String?, contentItems: List<Content>)
    }

    interface View {
        fun showContentList(contentList: List<Content>)
        fun showTitle(title: String)
        fun setFilterList(filterList: List<Content>)
        fun setListOnClear()
    }
}