package com.example.customalertbox

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.customalertbox.databinding.ActivityCustomAlertViewBinding
import com.example.customalertbox.databinding.AlertBoxViewBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


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
        context: Context, actionData: AlertViewModel, configModel: ConfigModel
    ) {
        val bindingAlert = AlertBoxViewBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(context)
            .setView(bindingAlert.root)

        val dialog = builder.create()
        bindingAlert.clAlertDialog.animateView(400, 0, R.anim.anim_zoom_in)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()


        bindingAlert.tvTitle.text = actionData.title
        bindingAlert.tvMessage.text = actionData.message

        customAlertViewAdapter = CustomAlertViewAdapter(
            configModel.tintColor,
            onItemClick = { it, position ->
                bindingAlert.clAlertDialog.animateView(300, 100, R.anim.anim_zoom_out)

                onActionItemClick.invoke(it)
                actionData.alertViewDataModel[position].onItemClick.invoke()
                GlobalScope.launch {
                    delay(200)
                    dialog.dismiss()
                }
            })

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