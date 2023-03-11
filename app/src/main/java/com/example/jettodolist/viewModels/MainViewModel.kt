package com.example.jettodolist.viewModels

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jettodolist.data.LocalDataSource
import com.example.jettodolist.data.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val local: LocalDataSource): ViewModel() {

    var title by mutableStateOf("")
    var description by mutableStateOf("")

    //note: distinctUntilChanged() →　https://developer.android.com/training/data-storage/room/async-queries?hl=ja
    var taskList = local.readTasks().distinctUntilChanged()

    var isShowDialog: Boolean by mutableStateOf(false)

    private var editingTask: Task? = null
    val isEditing: Boolean
    get() = editingTask != null

    fun setEditingTask(task: Task) {
        editingTask = task
        title = task.title
        description = task.description
    }

    fun resetEditingTask() {
        editingTask = null
        title = ""
        description = ""
    }
    fun createTask() {
        val newTask = Task(title = title, description = description)
        viewModelScope.launch {
            local.insertTask(newTask)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            local.deleteTask(task)
        }
    }

    fun updateTask() {
        //todo taskの値が変わっていない
        editingTask?.let { task ->
            viewModelScope.launch {
                local.updateTask(task)
            }
        }
    }
}