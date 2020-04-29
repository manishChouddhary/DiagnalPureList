package com.diagnal.purelisting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.diagnal.purelisting.content.ContentListFragment
import com.diagnal.purelisting.extentions.getColumnCountOnOrientation
import com.diagnal.purelisting.extentions.removeExt
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
