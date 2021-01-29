package com.example.customalertbox

data class AlertViewModel(
    val title: String,
    val message: String,
    val alertViewDataModel: MutableList<AlertViewDataModel>
)

data class AlertViewDataModel(val actionText: String, var onItemClick: () -> Unit)