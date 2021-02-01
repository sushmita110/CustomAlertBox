package com.example.customalertbox

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.customalertbox.databinding.ActivityCustomAlertViewBinding
import com.example.customalertbox.databinding.AlertBoxViewBinding


class CustomAlertViewActivity(
    private val onActionItemClick: (type: MutableList<AlertViewDataModel>) -> Unit
) : AppCompatActivity() {

    lateinit var binding: ActivityCustomAlertViewBinding
    lateinit var customAlertViewAdapter: CustomAlertViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomAlertViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun showAlertView(
        context: Context, actionData: AlertViewModel
    ) {
        val bindingAlert = AlertBoxViewBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(context)
            .setView(bindingAlert.root)
            .show()

        bindingAlert.tvTitle.text = actionData.title
        bindingAlert.tvMessage.text = actionData.message

        customAlertViewAdapter = CustomAlertViewAdapter(
            onItemClick = { it, position ->
                onActionItemClick.invoke(it)
                actionData.alertViewDataModel[position].onItemClick.invoke()
                builder.dismiss()
            }
        )

        val layoutManagerGrid =
            GridLayoutManager(context, 2)
        layoutManagerGrid.spanSizeLookup =
            object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if ((actionData.alertViewDataModel.size) % 2 != 0 && position == (actionData.alertViewDataModel.size - 1)) {
                        2
                    } else {
                        1
                    }
                }
            }
        bindingAlert.rvActionView.apply {
            layoutManager = if ((actionData.alertViewDataModel.size) >= 3) {
                LinearLayoutManager(context)
            } else {
                layoutManagerGrid
            }
            adapter = customAlertViewAdapter
        }

        customAlertViewAdapter.items = actionData.alertViewDataModel
    }
}