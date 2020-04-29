package com.diagnal.purelisting.content

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.diagnal.purelisting.AppApplication
import com.diagnal.purelisting.common.PureListAdaptor
import com.diagnal.purelisting.R
import com.diagnal.purelisting.common.SpacesItemDecoration
import com.diagnal.purelisting.extentions.getColumnCountOnOrientation
import com.diagnal.purelisting.extentions.getDimen
import com.diagnal.purelisting.extentions.removeExt
import com.diagnal.purelisting.model.Content
import kotlinx.android.synthetic.main.fragment_content_list.*
import javax.inject.Inject

class ContentListFragment: Fragment(),ContentListContract.View {

    @Inject
    lateinit var presenter: ContentListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppApplication.diComponent.inject(this)
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? =
        inflater.inflate(R.layout.fragment_content_list,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        presenter.getContentPage(1)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
    override fun showContentList(contentList: List<Content>) {
        with(rv) {
            adapter = PureListAdaptor().apply {
                contentItems = contentList
                getImageResource = { posterName -> resources.getIdentifier(
                    posterName.removeExt(),
                    "drawable",
                    requireContext().packageName
                )}
            }
            layoutManager = GridLayoutManager(
                requireContext(),
                this.getColumnCountOnOrientation(requireContext()),
                GridLayoutManager.VERTICAL,
                false)
            addItemDecoration(SpacesItemDecoration(
                sideSpace = requireContext().getDimen(R.dimen.item_side_spacing),
                bottomSpace = requireContext().getDimen(R.dimen.item_bottom_spacing),
                topSpace = requireContext().getDimen(R.dimen.item_top_spacing)))
        }
    }

    override fun showTitle(title: String) {
        (requireActivity() as AppCompatActivity).apply{
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setHomeAsUpIndicator(R.drawable.back)
                setTitle(title)
            }
        }
    }
}
