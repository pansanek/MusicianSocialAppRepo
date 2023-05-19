package com.potemkin.musiciansocialmobileapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.potemkin.musiciansocialmobileapp.models.MusicianModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.musician_card.view.*
import java.util.*

class MusicianAdapter (
    val context: Context,
    val item: ArrayList<MusicianModel>,
    val listener: OnItemClickListener
) :
    RecyclerView.Adapter<MusicianAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.musician_card,
            parent, false
        )

        return ViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = item.get(position)
        holder.name.text = item.name
        holder.genres.text = item.genres
        holder.instruments.text = item.instruments
        Picasso.get().load(R.drawable.ic_profile_bold).into(holder.imV);
    }


    override fun getItemCount(): Int {
        return item.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val name = itemView.name
        val genres = itemView.genres
        val instruments = itemView.instruments
        val imV = itemView.imV

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
        fun onItemClick(item: ArrayList<MusicianModel>, position: Int)
    }
}