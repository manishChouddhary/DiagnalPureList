package com.diagnal.purelisting.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diagnal.purelisting.databinding.PureListItemBinding
import com.diagnal.purelisting.model.Content

class PureListAdaptor : RecyclerView.Adapter<PureListAdaptor.ViewHolder>() {
    private var filterFlag: Boolean = false
    var contentItems: List<Content> = ArrayList()
    var filterContentList: ArrayList<Content> = ArrayList()

    lateinit var getImageResource: (String) -> Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PureListItemBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = if (filterFlag) filterContentList.size else contentItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(if (filterFlag) filterContentList[position] else contentItems[position])

    fun setList(contentList: List<Content>) {
        val listSize = contentItems.size
        (contentItems as ArrayList).addAll(contentList)
        notifyItemRangeChanged(if (listSize <= 0) 0 else listSize - 1, contentItems.size)
    }

    fun setFilterList(filterList: List<Content>) {
        filterFlag = true
        filterContentList.apply {
            clear()
            addAll(filterList)
        }
        notifyDataSetChanged()
    }

    fun setListOnClear() {
        filterFlag = false
        filterContentList.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val cItemView: PureListItemBinding) :
        RecyclerView.ViewHolder(cItemView.root) {
        fun bind(content: Content) {
            cItemView.contentItem = content
            cItemView.ivContent.setImageResource(getImageResource.invoke(content.posterImage))
        }
    }
}