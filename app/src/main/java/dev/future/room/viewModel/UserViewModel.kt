package dev.future.room.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import dev.future.room.db.UserDataBase
import dev.future.room.db.UserRepository
import dev.future.room.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(applicstion:Application) : AndroidViewModel(applicstion){

     val readAllData: LiveData<List<User>>
    private val userRepository:UserRepository

    init {
        val userDao  = UserDataBase.getDataBase(applicstion).userDao()
        userRepository =  UserRepository(userDao)
        readAllData = userRepository.readAllData
    }


    fun userAdd(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.addUser(user)
        }
    }
    fun updateDate(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.updateData(user)
        }
    }

    fun deleteUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.deleteUser(user)
        }
    }

    fun deleteAllUser(){
        viewModelScope.launch(Dispatchers.IO){
            userRepository.deleteAllUser()
        }
    }
}