package com.app.myorisapptest.views.activities.userDetails

import android.content.Intent
import android.os.CountDownTimer
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModelProvider
import com.app.myorisapptest.R
import com.app.myorisapptest.databinding.UserDetailsBinding
import com.app.myorisapptest.models.UserDetailsRepo
import com.app.myorisapptest.utilities.common.createFactory
import com.app.myorisapptest.utilities.common.isNetworkActive
import com.app.myorisapptest.utilities.network.RetrofitService
import com.app.myorisapptest.viewmodels.UsersDetailViewModel
import com.app.myorisapptest.views.activities.users.UserActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class UserDetailsObserver(private val userDetailsActivity: UserDetailsActivity) : LifecycleObserver {

    private lateinit var usersDetailViewModel: UsersDetailViewModel

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreated(){

        val binding : UserDetailsBinding = DataBindingUtil.setContentView(userDetailsActivity,R.layout.user_details)
        val factory = UsersDetailViewModel(UserDetailsRepo(RetrofitService()),userDetailsActivity.application).createFactory()

        usersDetailViewModel = ViewModelProvider(userDetailsActivity,factory).get(UsersDetailViewModel::class.java)

        if(userDetailsActivity.isNetworkActive()) {
            userDetailsActivity.intent.getStringExtra("id")?.let {

                usersDetailViewModel.getUsersDetails(it.toInt())
            }

            usersDetailViewModel.responseUsersDetails?.observe(userDetailsActivity, Observer {

                binding.run {
                    txtvwId.text = it.data.id.toString()
                    txtvwFirstName.text = it.data.first_name
                    txtvwLastName.text = it.data.last_name
                    txtvwEmail.text = it.data.email
                }

                Glide.with(userDetailsActivity)
                    .load(it.data.avatar)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(binding.imgAvatar)
            })
        }else {

            userDetailsActivity.intent.getStringExtra("id")?.let {
                usersDetailViewModel.getUsersDetailsById(it.toInt()).observe(userDetailsActivity,
                    Observer {

                        binding.run {
                            txtvwId.text = it.id.toString()
                            txtvwFirstName.text = it.first_name
                            txtvwLastName.text = it.last_name
                            txtvwEmail.text = it.email
                        }

                    })
            }
        }

        val timer = object: CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.timer.text= (millisUntilFinished/ 1000).toString()
            }

            override fun onFinish() {
                userDetailsActivity.startActivity(Intent(userDetailsActivity, UserActivity::class.java))
            }
        }
        timer.start()
    }
}
