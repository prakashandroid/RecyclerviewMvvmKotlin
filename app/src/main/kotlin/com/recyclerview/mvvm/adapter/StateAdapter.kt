package com.recyclerview.mvvm.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.recyclerview.mvvm.databinding.StateAdapterBinding
import com.recyclerview.mvvm.model.StateItem


class StateAdapter : RecyclerView.Adapter<StateAdapter.MyViewHolder>() {
    var arrayList : List<StateItem> = listOf()
    @SuppressLint("NotifyDataSetChanged")
    fun updateAdapter(arrayList: List<StateItem>) {
        this.arrayList = arrayList
        notifyDataSetChanged()
    }

    override fun getItemCount() = arrayList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = StateAdapterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindHolder(position)
    }

    inner class MyViewHolder(val binding: StateAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindHolder(position: Int) {
            val item = arrayList[position]
            binding.item = item
            binding.click.setOnClickListener {

            }

        }
    }


}