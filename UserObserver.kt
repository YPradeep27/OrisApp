package com.app.myorisapptest.views.activities.users

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.myorisapptest.R
import com.app.myorisapptest.databinding.ActivityUsersBinding
import com.app.myorisapptest.models.UserRepo
import com.app.myorisapptest.models.roomDatabase.User
import com.app.myorisapptest.utilities.common.createFactory
import com.app.myorisapptest.utilities.common.isNetworkActive
import com.app.myorisapptest.utilities.network.RetrofitService
import com.app.myorisapptest.viewmodels.UsersViewModel

class UserObserver(private val userActivity: UserActivity) : LifecycleObserver {

    lateinit var usersViewModel: UsersViewModel

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreated(){
        val factory = UsersViewModel(UserRepo(RetrofitService()),userActivity.application).createFactory()
        val binding : ActivityUsersBinding = DataBindingUtil.setContentView(userActivity, R.layout.activity_users)

        usersViewModel = ViewModelProvider(userActivity,factory).get(UsersViewModel::class.java)

        binding.recyclerViewUsers.layoutManager = LinearLayoutManager(userActivity.applicationContext)

        if(userActivity.isNetworkActive()) {

            usersViewModel.getUsers(20)

            usersViewModel.myList.observe(userActivity, Observer { list ->

                if (list.isEmpty()){
                    usersViewModel.response?.observe(userActivity, Observer {

                        for (user in it.data){
                            val users = User(user.id,user.avatar,user.email,user.first_name,user.last_name)
                            usersViewModel.insertUser(users)
                        }

                        binding.recyclerViewUsers.adapter = UsersAdapter(it.data,userActivity)
                    })

                } else {
                    binding.recyclerViewUsers.adapter = UsersAdapter(list,userActivity)
                }
            })
        }
        else {

            usersViewModel.myList.observe(userActivity, Observer {

                binding.recyclerViewUsers.adapter = UsersAdapter(it,userActivity)

            })
        }

    }
}