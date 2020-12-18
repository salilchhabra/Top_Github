package com.example.topgithub.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.topgithub.view.MainActivity
import com.example.topgithub.R
import com.example.topgithub.databinding.ItemRepositoriesBinding
import com.example.topgithub.model.Items

class MainActivityAdapter(
    private val data: List<Items>, context: MainActivity
) : RecyclerView.Adapter<MainActivityAdapter.ViewHolder>() {
    private val listener: ICommunicator

    init {
        listener = context
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_repositories, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[holder.adapterPosition]
        holder.itemRepositoriesBinding?.apply {
            Glide.with(ivAvatar.context).load(item.avatars?.get(0)).into(ivAvatar)
            tvTitle.text = item.repo
            tvDesc.text = item.desc

            holder.itemView.setOnClickListener {
                listener.openRepoDetailsActivity(item, position, ivAvatar, tvTitle, tvDesc)
            }
        }
    }


    override fun getItemCount(): Int {
        return data.size
    }

    interface ICommunicator {
        fun openRepoDetailsActivity(
            item: Items,
            position: Int,
            ivAvatar: ImageView,
            tvTitle: TextView,
            tvDesc: TextView
        )
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemRepositoriesBinding: ItemRepositoriesBinding? = DataBindingUtil.bind(itemView)
    }

}