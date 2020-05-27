import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.R
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.myrecyclerview.Hero


class ListHeroAdapter(private val listhero:ArrayList<Hero>) : RecyclerView.Adapter<ListHeroAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(com.myrecyclerview.R.layout.item_row_hero, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listhero.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val hero = listhero[position]

        Glide.with(holder.itemView.context)
            .load(hero.photo)
            .apply(RequestOptions().override(55,55))
            .into(holder.imgPhoto)

        holder.tvName.text = hero.name
        holder.tvDetail.text = hero.detail
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listhero[holder.adapterPosition])}

    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(com.myrecyclerview.R.id.tv_item_name)
        var tvDetail: TextView = itemView.findViewById(com.myrecyclerview.R.id.tv_item_detail)
        var imgPhoto: ImageView = itemView.findViewById(com.myrecyclerview.R.id.img_item_photo)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Hero)
    }
}

