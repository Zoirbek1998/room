package dev.future.room.db

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.future.room.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDataBase : RoomDatabase(){

    abstract fun userDao():UserDao

    companion object{
        @Volatile
        private var INSTANCE  :UserDataBase? = null
        fun getDataBase(context: Context):UserDataBase{
            val tempInstanse = INSTANCE
            if (tempInstanse!=null){
                return tempInstanse
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDataBase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return  instance
            }
        }
    }
}