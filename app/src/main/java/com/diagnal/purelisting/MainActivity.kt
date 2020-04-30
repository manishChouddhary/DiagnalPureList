package com.diagnal.purelisting

import android.os.Bundle
import android.view.Menu
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.diagnal.purelisting.content.ContentListFragment
import com.diagnal.purelisting.extentions.replace
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
        replace(ContentListFragment(), R.id.container, false, ContentListFragment::class.java.canonicalName)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
    }
}
