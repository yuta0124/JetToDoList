package com.example.jettodolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jettodolist.components.EditDialog
import com.example.jettodolist.components.TaskList
import com.example.jettodolist.ui.theme.JetToDoListTheme
import com.example.jettodolist.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetToDoListTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainContent()
                }
            }
        }
    }
}

@Composable
fun MainContent(viewModel: MainViewModel = hiltViewModel()) {
    if (viewModel.isShowDialog) {
        EditDialog()
    }
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = { viewModel.isShowDialog = true }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }) { padding ->
        val tasks by viewModel.taskList.collectAsState(initial = emptyList())
        TaskList(
            padding = padding,
            tasks = tasks,
            onClickRow = {
                viewModel.setEditingTask(it)
                viewModel.isShowDialog = true
            },
            onClickDelete = {
                viewModel.deleteTask(it)
            }
        )
    }
}
