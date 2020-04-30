package com.diagnal.purelisting.content

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.diagnal.purelisting.MockProvider
import com.diagnal.purelisting.R
import com.diagnal.purelisting.TestActivityBase
import com.diagnal.purelisting.extentions.RecyclerViewMatcher
import io.mockk.MockKAnnotations
import io.mockk.every
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ContentListFragmentTest: TestActivityBase() {
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        setUpMocks()
    }

    private fun setUpMocks() {
        every { communicationService.getPageData(1) } returns Single.just(MockProvider.getMockContentList())
    }

    @Test
    fun test_for_list_is_showing() {
        setUpContentFragment()
        onView(withText(MockProvider.title)).check(matches(isDisplayed()))
        onView(RecyclerViewMatcher(R.id.rv).atPositionOnView(0,R.id.tvContentTitle))
            .check(matches(isDisplayed()))
            .check(matches(withText(MockProvider.content_title)))
        onView(RecyclerViewMatcher(R.id.rv).atPositionOnView(0,R.id.ivContent))
            .check(matches(isDisplayed()))
            .check(matches(withContentDescription(MockProvider.content_title)))
    }

    @Test
    fun test_for_search() {
        setUpContentFragment()
        onView(withId(R.id.search)).perform(click())
        onView(withId(androidx.appcompat.R.id.search_src_text)).perform(typeText("Rear"))
        onView(RecyclerViewMatcher(R.id.rv).atPositionOnView(0,R.id.tvContentTitle))
            .check(matches(isDisplayed()))
            .check(matches(withText("Rear Window")))
        onView(RecyclerViewMatcher(R.id.rv).atPositionOnView(0,R.id.ivContent))
            .check(matches(isDisplayed()))
            .check(matches(withContentDescription("Rear Window")))
    }
}