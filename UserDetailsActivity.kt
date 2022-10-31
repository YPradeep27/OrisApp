package com.app.myorisapptest.views.activities.userDetails

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.app.myorisapptest.views.activities.users.UserActivity

class UserDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(UserDetailsObserver(this@UserDetailsActivity))

    }
}