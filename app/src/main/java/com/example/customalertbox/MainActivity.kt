package com.example.customalertbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.customalertbox.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private lateinit var customAlertViewActivity: CustomAlertViewActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val alertData = AlertViewModel(
            title = "Test",
            message = "Hello Worls",
        )

        customAlertViewActivity = CustomAlertViewActivity(
            onActionItemClick = {
                Log.e("MainActivity", "postion:$it")
            }
        )
        customAlertViewActivity.showAlertView(this, alertData)
    }
}