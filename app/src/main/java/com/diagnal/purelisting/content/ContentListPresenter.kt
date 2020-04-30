package com.diagnal.purelisting.content

import com.diagnal.purelisting.communication.CommunicationService
import com.diagnal.purelisting.model.Content
import com.diagnal.purelisting.model.ContentListing
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlin.properties.Delegates

class ContentListPresenter(private val communicationService: CommunicationService) :
    ContentListContract.Presenter {
    private var view: ContentListContract.View? = null
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    var current_page = 0
    var pageCount: Int = 3
    lateinit var contentList: List<Content>

    override fun attachView(view: ContentListContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
        compositeDisposable.dispose()
    }

    override fun getContentPage(page: Int) {
        if (current_page > pageCount)
            return
        val disposable = communicationService.getPageData(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                handleSuccessResponse(it)
            }, {
                handleError()
            })
        compositeDisposable.add(disposable)
    }

    override fun applyFilter(filterText: String?, contentItems: List<Content>) {
        if (filterText?.length ?: 0 >= 3) {
            val filterList =
                contentItems.filter { it.name.contains(filterText ?: "", ignoreCase = true) }
            view?.setFilterList(filterList)
        }
        if ((filterText?.length) == 0) {
            view?.setListOnClear()
        }
    }

    private fun handleError() {
        //handle if error occurs
    }

    private fun handleSuccessResponse(contentListing: ContentListing) {
        view?.showTitle(contentListing.page.title)
        view?.showContentList(contentListing.page.contentItems.content)
    }
}