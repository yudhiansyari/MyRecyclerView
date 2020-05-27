package com.myrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.text.FieldPosition

class GridHeroAdapter(val listHeroes: ArrayList<Hero>) : RecyclerView.Adapter<GridHeroAdapter.GridViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnClickCallback(onItemClickCallback: OnItemClickCallback){
       this.onItemClickCallback = onItemClickCallback
    }

    inner class GridViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
    }

    override fun onCreateViewHolder(viewGorup: ViewGroup, i: Int): GridViewHolder {
        val view: View = LayoutInflater.from(viewGorup.context).inflate(R.layout.item_grid_hero, viewGorup, false)
        return  GridViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listHeroes.size
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
       Glide.with(holder.itemView.context)
           .load(listHeroes[position].photo)
           .apply(RequestOptions().override(350, 550))
           .into(holder.imgPhoto)

            holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listHeroes[holder.adapterPosition])}
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: Hero)
    }
}