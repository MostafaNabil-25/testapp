package com.example.testapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CityAdapter(private var cityList: List<City>) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    private var filteredCityList: List<City> = cityList

    class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val countryTextView: TextView = itemView.findViewById(R.id.countryTextView)
        val latTextView: TextView = itemView.findViewById(R.id.lat)
        val lonTextView: TextView = itemView.findViewById(R.id.lon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rowlayout, parent, false)
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = filteredCityList[position]
        holder.nameTextView.text = city.name
        holder.countryTextView.text = city.country
        holder.latTextView.text = city.coord.lat.toString()
        holder.lonTextView.text = city.coord.lon.toString()
    }

    override fun getItemCount() = filteredCityList.size

    fun filter(query: String) {
        filteredCityList = if (query.isEmpty()) {
            cityList
        } else {
            cityList.filter { it.name.startsWith(query, ignoreCase = true) }
        }
        notifyDataSetChanged()
    }
}
