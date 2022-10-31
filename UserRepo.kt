package com.app.myorisapptest.models

import com.app.myorisapptest.models.userDetailsPOJO.UserDetailsPOJO
import com.app.myorisapptest.models.usersPojo.UserPOJO
import com.app.myorisapptest.utilities.network.RetrofitService

class UserRepo(private val retrofitService: RetrofitService) {

    suspend fun getUsers(size : Int) : UserPOJO {

        return retrofitService.getUsers(size)
    }

}