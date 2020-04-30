package com.diagnal.purelisting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.diagnal.purelisting.content.ContentListFragment
import com.diagnal.purelisting.extentions.replace
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCenter.start(
            application, "4c1f2231-a557-43db-be75-5157d068696f",
            Analytics::class.java, Crashes::class.java
        )
        setContentView(R.layout.activity_main)
        setupToolbar()
        replace(ContentListFragment(), R.id.container, false, ContentListFragment::class.java.canonicalName)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
    }
}
