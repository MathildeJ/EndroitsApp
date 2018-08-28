package com.test.endroits.home.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.test.endroits.R
import com.test.endroits.home.data.model.Venue

class VenueListAdapter(val list: List<Venue>, private val listener: OnVenueSelectionListener) : RecyclerView.Adapter<VenueListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.create(LayoutInflater.from(parent.context), parent)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], listener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val venueNameTextView = itemView.findViewById(R.id.venue_name) as TextView
        private val venueAddressTextView = itemView.findViewById(R.id.venue_address) as TextView
        private val venueDistanceTextView = itemView.findViewById(R.id.venue_distance) as TextView


        companion object {

            fun create(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
                val view = inflater.inflate(R.layout.view_venue_list_item, parent, false)
                return ViewHolder(view)
            }
        }

        fun bind(item: Venue, listener: OnVenueSelectionListener) {
            itemView.setOnClickListener { listener.onVenueSelected(item.id) }

            venueNameTextView.text = item.name
            venueAddressTextView.text = item.location.address
            val distance = item.location.distance
            venueDistanceTextView.text = if(distance.toFloat() < 1000f)
                String.format(itemView.context.getString(R.string.distance_m, distance.toInt()))
            else
                String.format(itemView.context.getString(R.string.distance_km, distance.toFloat() / 1000f))
        }
    }
}