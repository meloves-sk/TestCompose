package com.example.testcompose

data class Category(
    val id: Int,
    val name: String
)

data class Item(
    val id: Int,
    val name: String,
    val category: String
)

data class ItemRequest(
    val id: Int,
    val name: String,
    val categoryId: Int
)