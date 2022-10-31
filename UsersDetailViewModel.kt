package com.app.myorisapptest.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.myapp3.models.roomDatabase.UsersDao
import com.app.myorisapptest.models.UserDetailsRepo
import com.app.myorisapptest.models.room.UserDatabase
import com.app.myorisapptest.models.roomDatabase.User
import com.app.myorisapptest.models.userDetailsPOJO.UserDetailsPOJO
import com.app.myorisapptest.utilities.common.Coroutines

class UsersDetailViewModel(private val userDetailsRepo: UserDetailsRepo,application: Application) : ViewModel() {

    var responseUsersDetails : MutableLiveData<UserDetailsPOJO>? = MutableLiveData()
    var user2Dao: UsersDao

    init {
        val userDatabase: UserDatabase = UserDatabase.getDatabase(application)
        user2Dao = userDatabase.roomDao()
    }

    fun getUsersDetails(id : Int){

        Coroutines.main {
            responseUsersDetails?.value = userDetailsRepo.getUsersDetails(id)
        }
    }

    fun getUsersDetailsById(id : Int) : LiveData<User> {

        return user2Dao.getUsersDetailsById(id)
    }
}