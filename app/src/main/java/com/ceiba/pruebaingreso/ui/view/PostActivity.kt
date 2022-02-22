package com.ceiba.pruebaingreso.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ceiba.pruebaingreso.databinding.ActivityPostBinding
import com.ceiba.pruebaingreso.domain.model.Post
import com.ceiba.pruebaingreso.ui.view.adapter.PostAdapter
import com.ceiba.pruebaingreso.ui.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostBinding
    private val postViewModel: PostViewModel by viewModels()

    private var userId = 0
    private var posts: ArrayList<Post> = ArrayList()
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Publicaciones"
        init()
    }

    private fun init() {
        getExtras()
        initViews()
        initObservers()
        loadPosts()
    }

    private fun getExtras() {
        val extras = intent.extras ?: return
        if (extras.containsKey("user_id"))
            userId = intent.getIntExtra("user_id", 0)
    }

    private fun initViews() {
        binding.rvPosts.layoutManager = LinearLayoutManager(this)
    }

    private fun loadPosts() {
        if (userId > 0) {
            binding.progressBar.visibility = View.VISIBLE
            postViewModel.onLoadPosts(userId)
        }
    }

    private fun initObservers() {
        postViewModel.posts.observe(this) {
            posts = it as ArrayList<Post>
            loadAdapter()
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun loadAdapter() {
        postAdapter = PostAdapter(posts)
        binding.rvPosts.adapter = postAdapter
    }
}
