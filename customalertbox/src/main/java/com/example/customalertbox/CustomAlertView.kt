package com.example.customalertbox

import android.app.AlertDialog
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.customalertbox.databinding.AlertBoxViewBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CustomAlertView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var bindingAlert: AlertBoxViewBinding

    lateinit var customAlertViewAdapter: CustomAlertViewAdapter

    init {
        bindingAlert = AlertBoxViewBinding.inflate(LayoutInflater.from(context), null, true)
       addView(bindingAlert.root)
    }

    fun setUpData(
        onActionItemClick: (type: MutableList<AlertViewDataModel>) -> Unit,
        context: Context,
        actionData: AlertViewModel,
        style: Int? = null
    ) {

        val builder = AlertDialog.Builder(context)
            .setView(bindingAlert.root)

        val dialog = builder.create()
        dialog.show()

        bindingAlert.tvTitle.text = actionData.title
        bindingAlert.tvMessage.text = actionData.message

        bindingAlert.tvMessage.setTextAppearance(style ?: 0)
        bindingAlert.tvTitle.setTextAppearance(style ?: 0)

        bindingAlert.clAlertDialog.animateView(400, 0, R.anim.anim_zoom_in)

        customAlertViewAdapter = CustomAlertViewAdapter(
            onItemClick = { it, position ->

                //bindingAlert.clAlertDialog.animateView(400, 0, R.anim.anime_test)

                onActionItemClick.invoke(it)
                actionData.alertViewDataModel[position].onItemClick.invoke()
                GlobalScope.launch {
                    delay(300)
                    dialog.dismiss()

                }
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