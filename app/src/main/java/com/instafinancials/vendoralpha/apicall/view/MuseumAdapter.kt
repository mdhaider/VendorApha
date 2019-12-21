package com.instafinancials.vendoralpha.apicall.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.instafinancials.vendoralpha.R
import com.instafinancials.vendoralpha.apicall.model.Museum

class MuseumAdapter(private var museums:List<Museum>):RecyclerView.Adapter<MuseumAdapter.MViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_museum, parent, false)
        return MViewHolder(view)
    }

    override fun onBindViewHolder(vh: MViewHolder, position: Int) {
        val museum= museums[position]

        //render
        vh.textViewName.text= museum.name
       // Glide.with(vh.imageView.context).load(museum.photo).into(vh.imageView)
    }

    override fun getItemCount(): Int {
        return museums.size
    }

    fun update(data:List<Museum>){
        museums= data
        notifyDataSetChanged()
    }

    class MViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val textViewName: TextView = view.findViewById(R.id.textViewName)
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val textViewLink:TextView= view.findViewById(R.id.textViewLink)
    }
}