package com.assignment.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assignment.databinding.AdapterUserItemsBinding
import com.assignment.presentation.model.RepoListResponse
import com.assignment.presentation.utilities.Utils.Companion.makeVisible


class UserAdapter(private var listItems: List<RepoListResponse>) :
    RecyclerView.Adapter<UserAdapter.ListViewHolder?>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = AdapterUserItemsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.binding.tvName.text = listItems[position].name
//        holder.binding.tvEmail.text = listItems[position].email
//        if (listItems[position].gender == "male") holder.binding.ivMan.makeVisible() else holder.binding.ivWoman.makeVisible()
//        if(listItems[position].status == "active") holder.binding.ivOnline.makeVisible()
    }

    override fun getItemCount(): Int {
        return listItems.size
    }


    inner class ListViewHolder(val binding: AdapterUserItemsBinding) :
        RecyclerView.ViewHolder(binding.root)

}