package com.adl.mobileroom.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.adl.mobileroom.dao.UserDao
import com.adl.mobileroom.model.User

    @Database(entities = [User::class], version = 1)
abstract class UserDatabases: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object{
        var instance:UserDatabases?=null

        private var INSTANCE: UserDatabases? = null
        @Synchronized
        fun getInstance(context: Context): UserDatabases {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = buildDatabase(context)
                }
            }

            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): UserDatabases {
            return Room.databaseBuilder(
                context.applicationContext,
                UserDatabases::class.java,
                "user_db"
            )
                .build()
        }
    }
}