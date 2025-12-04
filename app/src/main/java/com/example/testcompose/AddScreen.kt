package com.example.testcompose

import android.os.StrictMode
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(nav: NavHostController) {
    val context = LocalContext.current
    val categories = remember {
        mutableStateListOf<Category>().apply {
            addAll(Api.get<List<Category>>("categories"))
        }
    }
    var name by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var select by remember { mutableStateOf(categories.first()) }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            ExposedDropdownMenuBox(
                modifier = Modifier.padding(5.dp),
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                TextField(
                    value = select.name,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Category") },
                    modifier = Modifier.menuAnchor(),
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) }
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    categories.forEach {
                        DropdownMenuItem(
                            text = { Text(it.name) },
                            onClick = { select = it; expanded = false }
                        )
                    }
                }
            }

            TextField(
                modifier = Modifier.padding(5.dp),
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") }
            )
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Button(
                    modifier = Modifier.padding(5.dp),
                    onClick = {
                        if (name.isEmpty()) {
                            Toast.makeText(context, "Input error", Toast.LENGTH_SHORT).show()
                        } else {
                            val item = ItemRequest(0, name, select.id)
                            Api.post<Item>("items", toJson(item))
                            nav.popBackStack()
                        }
                    }
                ) {
                    Text("Add")
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
    }
}