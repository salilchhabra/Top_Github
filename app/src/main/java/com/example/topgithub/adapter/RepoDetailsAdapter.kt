package com.example.topgithub.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.topgithub.R
import com.example.topgithub.databinding.ItemAvatarsBinding

class RepoDetailsAdapter(
    private val data: List<String>
) : RecyclerView.Adapter<RepoDetailsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_avatars, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data.get(holder.adapterPosition)
        holder.itemAvatarsBinding?.apply {
            Glide.with(ivAvatar.context).load(item).into(ivAvatar)
        }
    }


    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemAvatarsBinding: ItemAvatarsBinding? = DataBindingUtil.bind(itemView)
    }

}