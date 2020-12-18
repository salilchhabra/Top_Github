package com.example.topgithub.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topgithub.R
import com.example.topgithub.adapter.MainActivityAdapter
import com.example.topgithub.helper.CustomProgressDialog
import com.example.topgithub.model.Items
import com.example.topgithub.viewModel.GithubViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityAdapter.ICommunicator {
    private var viewModel: GithubViewModel? = null
    private var mainActivityAdapter: MainActivityAdapter? = null
    private var mProgressDialog: CustomProgressDialog? = null

    private lateinit var language: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mProgressDialog = CustomProgressDialog(this)
        viewModel = ViewModelProvider(this).get(GithubViewModel::class.java)
        btn_search.setOnClickListener {
            et_language.text?.let {
                language = et_language.text.toString()
                mProgressDialog?.show("Please wait")
                viewModel?.getGithubDetails(language.trim())
            }
        }
        observe()

    }

    private fun observe() {
        viewModel?.successLiveData?.observe(this,
            Observer {
                mProgressDialog?.hide()
                rv_repos.apply {
                    layoutManager = LinearLayoutManager(context)
                    itemAnimator = DefaultItemAnimator()
                    mainActivityAdapter = MainActivityAdapter(it, this@MainActivity)
                    adapter = mainActivityAdapter
                }

            }
        )
        viewModel?.errorLiveData?.observe(this,
            Observer {
                mProgressDialog?.hide()
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()

            }
        )
    }

    override fun openRepoDetailsActivity(
        item: Items,
        position: Int,
        ivAvatar: ImageView,
        tvTitle: TextView,
        tvDesc: TextView
    ) {
        val intent: Intent = Intent(this, RepoDetailsActivity::class.java)
        intent.putExtra("itemObject", item);

        val p1: Pair<View, String> = Pair(ivAvatar, "imageTN")
        val p2: Pair<View, String> = Pair(tvTitle, "titleTN")
        val p3: Pair<View, String> = Pair(tvDesc, "descTN")

        val optionsCompat: ActivityOptionsCompat =
            ActivityOptionsCompat.makeSceneTransitionAnimation(this, p1, p2, p3)
        startActivity(intent, optionsCompat.toBundle());

    }
}