package com.diagnal.purelisting.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diagnal.purelisting.databinding.PureListItemBinding
import com.diagnal.purelisting.model.Content

class PureListAdaptor: RecyclerView.Adapter<PureListAdaptor.ViewHolder>() {

    lateinit var contentItems : List<Content>

    lateinit var getImageResource : (String) -> Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PureListItemBinding.inflate(inflater)
            return ViewHolder(binding)    }

    override fun getItemCount(): Int = contentItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(contentItems[position])

    inner class ViewHolder(private val cItemView: PureListItemBinding) : RecyclerView.ViewHolder(cItemView.root) {
        fun bind(content: Content) {
            cItemView.contentItem = content
            cItemView.ivContent.setImageResource(getImageResource.invoke(content.posterImage))
        }
    }
}