package dev.future.room.db

import androidx.lifecycle.LiveData
import dev.future.room.model.User

class UserRepository(private val userDao : UserDao) {

    val readAllData:LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user:User){
        userDao.addUser(user)
    }

    suspend fun updateData(user: User){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUser(){
        userDao.deleteAllUsers()
    }
}