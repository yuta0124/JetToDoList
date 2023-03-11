package com.example.jettodolist.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jettodolist.viewModels.MainViewModel

@Composable
fun EditDialog(viewModel: MainViewModel = hiltViewModel()) {

    //note: AlertDialogと同じ階層におくことで、ダイアログがディコンポーザブルされたタイミングでロジック処理できる。key1に変数等を割り当てると
    //その変数が変更されるたびに呼び出される。
    DisposableEffect(Unit) {
        onDispose {
            viewModel.resetEditingTask()
        }
    }

    AlertDialog(onDismissRequest = { viewModel.isShowDialog = false },
        title = {
            Text(text = if (viewModel.isEditing) "タスクの編集" else "タスクの新規作成")
        },
        text = {
            Column {
                Text(text = "タイトル")
                TextField(value = viewModel.title, onValueChange = { viewModel.title = it })
                Text(text = "詳細")
                TextField(
                    value = viewModel.description,
                    onValueChange = { viewModel.description = it })
            }
        },
        buttons = {
            Row(modifier = Modifier.padding(8.dp)) {
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    modifier = Modifier.width(120.dp),
                    onClick = {
                        viewModel.isShowDialog = false
                    }) {
                    Text(text = "キャンセル")
                }

                Spacer(modifier = Modifier.width(10.dp))

                Button(modifier = Modifier.width(120.dp), onClick = {
                    if (viewModel.isEditing) {
                        viewModel.updateTask()
                    } else {
                        viewModel.createTask()
                    }
                    viewModel.isShowDialog = false
                }) {
                    Text(text = "OK")
                }
            }
        }
    )
}

//@Preview(widthDp = 320, showBackground = true)
//@Composable
//fun previewDialog() {
//    Surface {
//        EditDialog()
//    }
//}