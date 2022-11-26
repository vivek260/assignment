package com.assignment.presentation.ui.repo_listing.repo_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assignment.databinding.AdapterRepoItemsBinding
import com.assignment.presentation.model.RepoListResponse


class RepoListingAdapter(private var listItems: List<RepoListResponse>) :
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
//        holder.binding.tvName.text = listItems[position].name
//        holder.binding.tvEmail.text = listItems[position].email
//        if (listItems[position].gender == "male") holder.binding.ivMan.makeVisible() else holder.binding.ivWoman.makeVisible()
//        if(listItems[position].status == "active") holder.binding.ivOnline.makeVisible()
    }

    override fun getItemCount(): Int {
        return listItems.size
    }


    inner class ListViewHolder(val binding: AdapterRepoItemsBinding) :
        RecyclerView.ViewHolder(binding.root)

}