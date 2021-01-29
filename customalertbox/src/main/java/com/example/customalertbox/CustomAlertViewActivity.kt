package com.example.customalertbox

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.customalertbox.databinding.ActivityCustomAlertViewBinding
import com.example.customalertbox.databinding.AlertBoxViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class CustomAlertViewActivity(
    private val onActionItemClick: (type: Int) -> Unit
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
        val bindingSheet = AlertBoxViewBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(context)
        builder.setView(bindingSheet.root)

        bindingSheet.tvTitle.text = actionData.title
        bindingSheet.tvMessage.text = actionData.message
        customAlertViewAdapter = CustomAlertViewAdapter(
            onItemClick = {
                onActionItemClick.invoke(it)
                actionData.alertViewDataModel[it].onItemClick.invoke()
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
        bindingSheet.rvActionView.apply {
            layoutManager = layoutManagerGrid
            adapter = customAlertViewAdapter
        }

        customAlertViewAdapter.items = actionData.alertViewDataModel
        builder.show()
    }
}