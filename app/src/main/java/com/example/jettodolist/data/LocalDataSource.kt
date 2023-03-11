package com.example.jettodolist.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val taskDao: TaskDao) {

    fun readTasks(): Flow<List<Task>> = taskDao.loadAllTasks()

    suspend fun insertTask(task: Task) {
        taskDao.insertTask(task)
    }

    suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }

    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }
}