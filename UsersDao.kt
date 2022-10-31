package com.app.myapp3.models.roomDatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.myorisapptest.models.roomDatabase.User

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM userDetails")
    fun getAllUsers() : LiveData<List<User>>

    @Query("SELECT * FROM userDetails WHERE id=:Id")
    fun getUsersDetailsById(Id: Int) : LiveData<User>

}