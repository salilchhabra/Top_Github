package com.example.topgithub

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.topgithub.adapter.RepoDetailsAdapter
import com.example.topgithub.model.Items
import kotlinx.android.synthetic.main.activity_repo_details.*


class RepoDetailsActivity : AppCompatActivity() {
    private var item: Items? = null
    private var repoDetailsAdapter: RepoDetailsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_details)
        item = intent.extras?.getSerializable("itemObject") as Items?
        populateData()
        setRecyclerView()
    }

    private fun setRecyclerView() {
        if (item?.avatars?.size!! > 1) {
            rv_avatars.apply {
                layoutManager = GridLayoutManager(context, 3)
                itemAnimator = DefaultItemAnimator()
                repoDetailsAdapter = item?.avatars?.let { RepoDetailsAdapter(it) }
                adapter = repoDetailsAdapter
            }
        } else {
            tv_images.visibility = View.GONE
        }
    }

    private fun populateData() {
        Glide.with(this).load(item?.avatars?.get(0)).into(iv_avatar);
        item?.apply {
            tv_title.text = repo
            tv_link.text = repo_link
            tv_desc.text = desc?.let {
                String.format(
                    resources.getString(R.string.desc), desc
                )
            }
            tv_lang.text = lang?.let {
                String.format(
                    resources.getString(R.string.language), lang
                )
            }
            tv_stars.text = stars?.let {
                String.format(
                    resources.getString(R.string.stars), stars
                )
            }
            tv_forks.text = forks?.let {
                String.format(
                    resources.getString(R.string.forks), forks
                )
            }
            tv_added_stars.text = added_stars?.let {
                String.format(
                    resources.getString(R.string.added_stars), added_stars
                )
            }

        }


    }
}