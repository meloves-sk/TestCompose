package com.example.testcompose

import android.os.StrictMode
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun DetailScreen(nav: NavHostController, id: Int) {
    var item by remember {
        mutableStateOf(Api.get<Item>("items/$id"))
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(modifier = Modifier.padding(5.dp)) {
            Text("ID: ${item.id}")
            Text("Name: ${item.name}")
            Text("Category: ${item.category}")
        }
        Button(
            modifier = Modifier.padding(5.dp),
            onClick = {
                nav.popBackStack()
            }
        ) {
            Text("Back")
        }
    }
}