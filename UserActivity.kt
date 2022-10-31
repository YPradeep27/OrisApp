package com.app.myorisapptest.views.activities.users

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(UserObserver(this@UserActivity))
    }
}