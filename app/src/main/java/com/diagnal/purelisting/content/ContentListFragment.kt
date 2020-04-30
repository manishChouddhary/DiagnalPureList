package com.diagnal.purelisting.content

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diagnal.purelisting.AppApplication
import com.diagnal.purelisting.R
import com.diagnal.purelisting.common.PureListAdaptor
import com.diagnal.purelisting.common.SpacesItemDecoration
import com.diagnal.purelisting.extentions.getColumnCountOnOrientation
import com.diagnal.purelisting.extentions.getDimen
import com.diagnal.purelisting.extentions.removeExt
import com.diagnal.purelisting.model.Content
import kotlinx.android.synthetic.main.fragment_content_list.*
import javax.inject.Inject

class ContentListFragment : Fragment(), ContentListContract.View {

    private lateinit var rvAdapter: PureListAdaptor

    @Inject
    lateinit var presenter: ContentListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppApplication.diComponent.inject(this)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_content_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        presenter.getContentPage(++presenter.current_page)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        with(rv) {
            rvAdapter = PureListAdaptor().apply {
                getImageResource = { posterName ->
                    resources.getIdentifier(
                        posterName.removeExt(),
                        "drawable",
                        requireContext().packageName
                    )
                }
            }
            adapter = rvAdapter
            layoutManager = GridLayoutManager(
                requireContext(),
                this.getColumnCountOnOrientation(requireContext()),
                GridLayoutManager.VERTICAL,
                false
            )
            addItemDecoration(
                SpacesItemDecoration(
                    sideSpace = requireContext().getDimen(R.dimen.item_side_spacing),
                    bottomSpace = requireContext().getDimen(R.dimen.item_bottom_spacing),
                    topSpace = requireContext().getDimen(R.dimen.item_top_spacing),
                    orentationSpan = getColumnCountOnOrientation(requireContext())
                )
            )
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (dy > 0) {
                        val layoutManager = this@with.layoutManager as GridLayoutManager
                        val visibleItemCount =
                            layoutManager.findLastCompletelyVisibleItemPosition() + 1 - getColumnCountOnOrientation(
                                requireContext()
                            )
                        if (visibleItemCount == layoutManager.itemCount - getColumnCountOnOrientation(
                                requireContext()
                            )
                        ) {
                            presenter.getContentPage(presenter.current_page++)
                        }
                    }
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showContentList(contentList: List<Content>) {
        rvAdapter.setList(contentList)
    }

    override fun showTitle(title: String) {
        (requireActivity() as AppCompatActivity).apply {
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setHomeAsUpIndicator(R.drawable.back)
                setTitle(title)
            }
        }
    }

    override fun setFilterList(filterList: List<Content>) {
        rvAdapter.setFilterList(filterList)
    }

    override fun setListOnClear() {
        rvAdapter.setListOnClear()
    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        menu?.clear()
        requireActivity().menuInflater.inflate(R.menu.menu_content_list,menu)
        val search = menu?.findItem(R.id.search)
        val searchView: SearchView = search?.actionView as SearchView
        val searchClose: ImageView = searchView.findViewById(androidx.appcompat.R.id.search_close_btn)
        searchClose.setImageResource(R.drawable.search_cancel)
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(filterText: String?): Boolean {
                presenter.applyFilter(filterText, rvAdapter.contentItems)
                return false
            }
        })
    }
}
