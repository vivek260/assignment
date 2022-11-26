package com.assignment.presentation.ui.repo_listing.repo_adapter

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assignment.R
import com.assignment.databinding.AdapterRepoItemsBinding
import com.assignment.presentation.model.RepoListResponse


class RepoListingAdapter(private var listItems: List<RepoListResponse>,private var mContext: Context) :
    RecyclerView.Adapter<RepoListingAdapter.ListViewHolder?>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = AdapterRepoItemsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.binding.tvTitle.text = listItems[position].name
        holder.binding.tvDescription.text = listItems[position].description
        holder.binding.tvCreatedOn.text = String.format(mContext.getString(R.string.last_updated), listItems[position].updated_at)
 }

    override fun getItemCount(): Int {
        return listItems.size
    }


    inner class ListViewHolder(val binding: AdapterRepoItemsBinding) :
        RecyclerView.ViewHolder(binding.root)

}