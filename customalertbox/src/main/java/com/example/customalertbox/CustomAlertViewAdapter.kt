package com.example.customalertbox

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.customalertbox.databinding.CustomAlertViewBinding

class CustomAlertViewAdapter(
    private val onItemClick: (type: Int) -> Unit
) :
    RecyclerView.Adapter<CustomAlertViewAdapter.RecycleViewHolder>() {

    lateinit var binding: CustomAlertViewBinding
    var items: MutableList<AlertViewDataModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleViewHolder {
        binding =
            CustomAlertViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecycleViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecycleViewHolder, position: Int) {
        holder.apply {
            itemView.apply {
                binding.btnAction.text = items[position].actionText
                binding.btnAction.setOnClickListener {
                    onItemClick.invoke(position)
                }
            }
        }
    }

    class RecycleViewHolder(binding: CustomAlertViewBinding) :
        RecyclerView.ViewHolder(binding.root)
}
