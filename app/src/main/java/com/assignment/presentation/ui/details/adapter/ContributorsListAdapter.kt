package com.assignment.presentation.ui.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assignment.databinding.AdapterContributorsItemsBinding
import com.assignment.presentation.model.ContributorsResponse
import com.assignment.presentation.utilities.Imagify


class ContributorsListAdapter(private val list: List<ContributorsResponse>) :
    RecyclerView.Adapter<ContributorsListAdapter.ListViewHolder?>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = AdapterContributorsItemsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.binding.tvName.text = list[position].login
        holder.binding.tvCount.text = list[position].contributions
        Imagify.loadImage(holder.binding.ivProfile, list[position].avatar_url)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    inner class ListViewHolder(val binding: AdapterContributorsItemsBinding) :
        RecyclerView.ViewHolder(binding.root)

}