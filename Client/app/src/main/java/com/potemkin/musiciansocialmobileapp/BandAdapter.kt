package com.potemkin.musiciansocialmobileapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.potemkin.musiciansocialmobileapp.models.BandModel
import com.potemkin.musiciansocialmobileapp.models.MusicianModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.musician_card.view.*
import java.util.*

class BandAdapter (
    val context: Context,
    val item: ArrayList<BandModel>,
    val listener: OnItemClickListener
) :
    RecyclerView.Adapter<BandAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.band_card,
            parent, false
        )

        return ViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = item.get(position)
        holder.name.text = item.name
    }


    override fun getItemCount(): Int {
        return item.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val name = itemView.name

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != -1) {
                listener.onItemClick(item,position)
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClick(item: ArrayList<BandModel>, position: Int)
    }
}