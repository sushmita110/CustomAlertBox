package com.example.customalertbox

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.customalertbox.databinding.ActivityCustomAlertViewBinding
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
        context: Context,
        alertView: AlertViewModel
    ) {
        val dialog = AlertDialog.Builder(context)

        binding.tvTitle.text = alertView.title
        binding.tvMessage.text = alertView.message
        customAlertViewAdapter = CustomAlertViewAdapter(
            onItemClick = {
                onActionItemClick.invoke(it)
                //actionData[it].onItemClick.invoke()
            }
        )
        binding.rvActionView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = customAlertViewAdapter
        }

       // customAlertViewAdapter.items = alertView
    }
}