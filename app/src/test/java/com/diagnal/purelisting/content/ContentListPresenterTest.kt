package com.diagnal.purelisting.content

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.diagnal.purelisting.MockDataProvider
import com.diagnal.purelisting.RxUnitTestTool
import com.diagnal.purelisting.communication.CommunicationService
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ContentListPresenterTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    lateinit var presenter: ContentListPresenter

    lateinit var view: ContentListContract.View

    @MockK
    lateinit var communicationService: CommunicationService

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        RxUnitTestTool.asyncToSync()
        view = mockk()
        presenter = ContentListPresenter(communicationService).apply{
            attachView(view)
            pageCount = 1
            current_page = 1
        }
        setupMocks()
    }

    @Test
    fun test_getContentPage() {
        presenter.getContentPage(1)
        verify(exactly = 1) { view.showTitle(MockDataProvider.title) }
        verify(exactly = 1) { view.showContentList(MockDataProvider.list) }
    }

    @Test
    fun test_applyFilter_with_empty_query_text() {
        presenter.applyFilter("",MockDataProvider.list)
        verify(exactly = 1) { view.setListOnClear() }
    }

    @Test
    fun test_applyFilter_with_matching_query_text() {
        presenter.applyFilter("The",MockDataProvider.list)
        verify(exactly = 1) { view.setFilterList(MockDataProvider.list) }
    }

    @Test
    fun test_applyFilter_with_non_matching_query_text() {
        presenter.applyFilter("123",MockDataProvider.list)
        verify(exactly = 1) { view.setFilterList(mutableListOf()) }
    }

    @Test
    fun test_applyFilter_with_query_text_short_length() {
        presenter.applyFilter("Th",MockDataProvider.list)
        verify(exactly = 0) { view.setFilterList(mutableListOf()) }
    }

    private fun setupMocks() {
        every { view.showTitle(any()) } just runs
        every { view.showContentList(any()) } just runs
        every { view.setFilterList(any()) } just runs
        every { view.setListOnClear() } just runs
        every { communicationService.getPageData(1) } returns Single.just(MockDataProvider.getMockContentList())
    }
}