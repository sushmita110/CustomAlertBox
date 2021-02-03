package com.example.customalertbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.customalertbox.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private lateinit var customAlertViewActivity: CustomAlertViewActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnClick.setOnClickListener {
            val actionText = mutableListOf<AlertViewDataModel>()

            val actionOk = AlertViewDataModel(
                "Ok",
                onItemClick = {
                    Toast.makeText(this, "Ok", Toast.LENGTH_LONG).show()
                }
            )
            val actionCancel = AlertViewDataModel(
                "Cancel",
                onItemClick = {
                    Toast.makeText(this, "Cancel", Toast.LENGTH_LONG).show()
                }
            )
            val actionNotNow = AlertViewDataModel(
                "Not Now",
                onItemClick = {
                    Toast.makeText(this, "Not Now", Toast.LENGTH_LONG).show()
                }
            )

            actionText.add(actionOk)
            actionText.add(actionCancel)
            actionText.add(actionNotNow)

            val alertData = AlertViewModel(
                title = "Test",
                message = "Hello World",
                actionText
            )

            customAlertViewActivity = CustomAlertViewActivity(
                onActionItemClick = {
                }
            )
            customAlertViewActivity.showAlertView(this, alertData)
        }
    }
}