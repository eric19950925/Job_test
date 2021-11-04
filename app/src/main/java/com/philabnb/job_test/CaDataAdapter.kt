package com.philabnb.job_test

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rv_cadata_item.view.*

class CaDataAdapter (private val dataList: MutableList<String>):
    RecyclerView.Adapter<CaDataViewHelper>() {
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaDataViewHelper {
        context = parent.context
        return CaDataViewHelper(LayoutInflater.from(context).inflate(R.layout.rv_cadata_item,parent,false))
    }

    override fun onBindViewHolder(holder: CaDataViewHelper, position: Int) {
        val data = dataList[position]
        holder.itemView.tv_itemNum.text = "項目${position+1}"
        holder.itemView.tv_itemDate.text = data
    }

    override fun getItemCount(): Int = dataList.size

}
