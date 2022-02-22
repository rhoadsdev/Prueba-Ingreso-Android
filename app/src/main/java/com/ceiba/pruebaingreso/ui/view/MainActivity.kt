package com.ceiba.pruebaingreso.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ceiba.pruebaingreso.databinding.ActivityMainBinding
import com.ceiba.pruebaingreso.domain.model.User
import com.ceiba.pruebaingreso.ui.view.adapter.UserAdapter
import com.ceiba.pruebaingreso.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var userAdapter: UserAdapter
    private var users: ArrayList<User> = ArrayList()
    private lateinit var svText: SearchView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initViews()
        loadUsers()
        initObservers()
    }

    private fun initViews() {
        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        svText = binding.svText
        svText.setOnQueryTextListener(this)
    }

    private fun loadUsers() {
        mainViewModel.onLoadUsers()
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun initObservers() {
        mainViewModel.users.observe(this) {
            users = it as ArrayList<User>
            loadAdapter()
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun loadAdapter() {
        userAdapter = UserAdapter(
            users,
            object : UserAdapter.OnUserItemInteractionListener {
                override fun onUserSelected(user: User) {
                    startActivity(
                        Intent(
                            application,
                            PostActivity::class.java
                        ).putExtra("user_id", user.id)
                    )
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                }

                override fun isEmptyList(isEmty: Boolean) {
                    binding.tvListEmpty.visibility = if (isEmty) View.VISIBLE else View.GONE
                }
            })
        binding.rvUsers.adapter = userAdapter
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false;
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        userAdapter.filter.filter(newText)
        return false;
    }
}
