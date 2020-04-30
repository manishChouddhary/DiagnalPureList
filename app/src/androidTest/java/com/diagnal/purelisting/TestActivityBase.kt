package com.diagnal.purelisting

import android.app.Instrumentation
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.diagnal.purelisting.communication.CommunicationService
import com.diagnal.purelisting.content.ContentListContract
import com.diagnal.purelisting.content.ContentListFragment
import com.diagnal.purelisting.content.ContentListPresenter
import io.mockk.impl.annotations.MockK

open class TestActivityBase {

    @JvmField
    val activityRule = ActivityTestRule(TestActivity::class.java,false,false)

    @MockK
    lateinit var communicationService: CommunicationService

    lateinit var presenter: ContentListContract.Presenter

    private lateinit var instrumentation : Instrumentation

    fun setUpContentFragment() {
        presenter = ContentListPresenter(communicationService)
        instrumentation = InstrumentationRegistry.getInstrumentation()
        activityRule.launchActivity(null)
        val contentFragment = ContentListFragment()
        val fragmentManager = activityRule.activity.supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.container,contentFragment)
            .commit()
        presenter.attachView(contentFragment)
        instrumentation.waitForIdleSync()
    }
}