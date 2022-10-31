package com.app.myorisapptest.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.myapp3.models.roomDatabase.UsersDao
import com.app.myorisapptest.models.UserRepo
import com.app.myorisapptest.models.room.UserDatabase
import com.app.myorisapptest.models.roomDatabase.User
import com.app.myorisapptest.models.usersPojo.UserPOJO
import com.app.myorisapptest.utilities.common.Coroutines

class UsersViewModel(private val userRepo: UserRepo,application: Application) : ViewModel() {

    var response : MutableLiveData<UserPOJO>? = MutableLiveData()
    var user2Dao: UsersDao
    var myList: LiveData<List<User>>

    init {
        val userDatabase: UserDatabase = UserDatabase.getDatabase(application)
        user2Dao = userDatabase.roomDao()
        myList = user2Dao.getAllUsers()
    }

    fun getUsers(size : Int){

        Coroutines.main {
            response?.value = userRepo.getUsers(size)
        }
    }

    fun insertUser(user: User) {

        Coroutines.main {
            user2Dao.insertUser(user)
        }
    }

    fun fetch(): LiveData<List<User>> {
        return myList
    }

}