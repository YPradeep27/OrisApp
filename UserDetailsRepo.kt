package com.app.myorisapptest.models

import com.app.myorisapptest.models.userDetailsPOJO.UserDetailsPOJO
import com.app.myorisapptest.utilities.network.RetrofitService

class UserDetailsRepo(private val retrofitService: RetrofitService) {

    suspend fun getUsersDetails(id : Int) : UserDetailsPOJO {

        return retrofitService.getUsersDetails(id)
    }
}