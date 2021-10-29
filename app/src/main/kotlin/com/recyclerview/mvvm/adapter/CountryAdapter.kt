package com.recyclerview.mvvm.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


import com.recyclerview.mvvm.databinding.CountryAdapterBinding
import com.recyclerview.mvvm.model.CountryResult
import com.recyclerview.mvvm.utils.CountryItemClickListener


class CountryAdapter :
    RecyclerView.Adapter<CountryAdapter.MyViewHolder>() {
    private var arrayList = CountryResult()
    private lateinit var clickItemListener: CountryItemClickListener

    @SuppressLint("NotifyDataSetChanged")
    fun updateAdapter(arrayList: CountryResult) {
        this.arrayList = arrayList
        notifyDataSetChanged()
    }

    fun setClickItemListener(clickItemListener: CountryItemClickListener) {
        this.clickItemListener = clickItemListener
    }

    override fun getItemCount() = arrayList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CountryAdapterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindHolder(position)
    }

    inner class MyViewHolder(val binding: CountryAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindHolder(position: Int) {
            val item = arrayList[position]
            binding.item = item
            binding.click.setOnClickListener {
                clickItemListener.onItemClick(binding.click, item)
            }

        }
    }


}