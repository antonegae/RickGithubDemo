package com.rick.githubtour.view.main.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rick.githubtour.databinding.ActivityGithubUserMainBinding
import com.rick.githubtour.view.main.viewModel.GitHubUserMainActivityViewModel
import com.rick.githubtour.view.adapter.GithubUserAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GithubUserMainActivity : AppCompatActivity() {

    private val viewModel: GitHubUserMainActivityViewModel by viewModels()
    private lateinit var binding: ActivityGithubUserMainBinding
    lateinit var githubUserAdapter: GithubUserAdapter
    val llm = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGithubUserMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupRecyclerView()
        initScroll()

        viewModel.userList.observe(this) { response ->
            githubUserAdapter.submitList(response)
        }
    }
    private fun setupRecyclerView() {
        githubUserAdapter = GithubUserAdapter(){}
        binding.rvUserList.apply {
            adapter = githubUserAdapter
        }
    }

    private fun initScroll() {

        binding.rvUserList.apply {

            llm.orientation = LinearLayoutManager.VERTICAL
            layoutManager = llm

            //分批顯示
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                @SuppressLint("StringFormatMatches")
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                    val totalItemCount = layoutManager.itemCount

                    if (lastVisibleItemPosition == totalItemCount - 1) {
                        // 已經滾動到底部，顯示底部提示視圖
                        adapter?.notifyItemInserted(totalItemCount)
                        //viewModel.getUserList(20,100)
                        val text = "滑到底部囉!"
                        val duration = Toast.LENGTH_SHORT
                        val toast = Toast.makeText(applicationContext, text, duration)
                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
                        toast.show()

                    }
                }
                /*
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        // 已經滑到底部

                        recyclerView.stopScroll() // 停止滾動

                    }
                } */
            })
        }

    }
    /*
    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
        isLoading = true
    }*/
}



