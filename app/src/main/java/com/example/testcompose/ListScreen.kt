package com.example.testcompose

import android.os.StrictMode
import android.widget.Spinner
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ListScreen(nav: NavHostController) {
    StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())
    val items = remember {
        mutableStateListOf<Item>().apply {
            addAll(Api.get<List<Item>>("items"))
        }
    }
    Column {
        LazyColumn(Modifier.weight(1f)) {
            items(items) { item ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Black)
                        .padding(5.dp)
                        .clickable {
                            nav.navigate("detail/${item.id}")
                        }) {
                    Text("ID: ${item.id}  ")
                    Text("Name: ${item.name}  ")
                    Text("Category: ${item.category}")
                }
            }
        }
        Button(
            onClick = {
                nav.navigate("add")
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Add")
        }
    }
}