package com.example.jettodolist.di

import android.content.Context
import androidx.room.Room
import com.example.jettodolist.data.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, TaskDatabase::class.java, "Task").build()

    @Singleton
    @Provides
    fun provideDao(database: TaskDatabase) = database.taskDao()

}