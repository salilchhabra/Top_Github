package com.example.topgithub

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topgithub.adapter.MainActivityAdapter
import com.example.topgithub.helper.CustomProgressDialog
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
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()

            }
        )
    }

    override fun openRepoDetailsActivity() {
        TODO("Not yet implemented")
    }
}