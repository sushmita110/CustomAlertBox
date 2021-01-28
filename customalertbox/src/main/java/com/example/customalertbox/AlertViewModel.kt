package com.example.customalertbox

data class AlertViewModel(
    val title: String,
    val message: String)

data class AlertViewDataModel(var onItemClick: () -> Unit)