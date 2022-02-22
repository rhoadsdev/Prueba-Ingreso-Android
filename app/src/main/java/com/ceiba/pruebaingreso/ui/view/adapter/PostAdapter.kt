package com.ceiba.pruebaingreso.ui.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ceiba.pruebaingreso.R
import com.ceiba.pruebaingreso.domain.model.Post

class PostAdapter(
    private val dataSet: ArrayList<Post>,
) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView
        val tvBody: TextView
        val cardView: CardView

        init {
            tvTitle = view.findViewById(R.id.tvTitle)
            tvBody = view.findViewById(R.id.tvBody)
            cardView = view.findViewById(R.id.cardview)
        }

        fun bind(transaction: Post) {
            //
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_post, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.tvTitle.text = dataSet[position].title
        viewHolder.tvBody.text = dataSet[position].body
        viewHolder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

}