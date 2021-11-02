package com.philabnb.job_test

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.philabnb.job_test.Model.DataModelItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rv_data_item.view.*

class DataAdapter (private val dataList: MutableList<DataModelItem>, val clickListener: (String) -> Unit):
    RecyclerView.Adapter<DataViewHolder>() {
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        context = parent.context
        return DataViewHolder(LayoutInflater.from(context).inflate(R.layout.rv_data_item,parent,false))
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val data = dataList[position]
        holder.itemView.title.text = data.title
        holder.itemView.description.text = data.description
        holder.itemView.date.text = data.date
        Picasso.get()
            .load(data.url)
            .into(holder.itemView.image)
        holder.itemView.listenClick {
            clickListener.invoke(data.apodSite)
        }
    }

    override fun getItemCount(): Int = dataList.size

}
