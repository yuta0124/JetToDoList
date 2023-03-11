package com.example.jettodolist.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.jettodolist.data.Task

@Composable
fun TaskList(
    padding: PaddingValues,
    tasks: List<Task>,
    onClickRow: (Task) -> Unit,
    onClickDelete: (Task) -> Unit
) {
    LazyColumn(modifier = androidx.compose.ui.Modifier.padding(padding)) {
        items(items = tasks) { task ->
            TaskRow(
                task,
                onClickRow = onClickRow,
                onClickDelete = onClickDelete
            )
        }
    }
}