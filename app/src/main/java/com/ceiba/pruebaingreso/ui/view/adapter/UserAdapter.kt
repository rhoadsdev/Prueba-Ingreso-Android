package com.ceiba.pruebaingreso.ui.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ceiba.pruebaingreso.R
import com.ceiba.pruebaingreso.domain.model.User

class UserAdapter(
    private val data: ArrayList<User>,
    private val listener: OnUserItemInteractionListener
) : RecyclerView.Adapter<UserAdapter.ViewHolder>(), Filterable {

    private var dataSet: ArrayList<User> = ArrayList()
    var dataSetFilter: ArrayList<User> = ArrayList()

    init {
        dataSet.addAll(data)
        dataSetFilter.addAll(dataSet)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView
        val tvPhone: TextView
        val tvEmail: TextView
        val tvPosts: TextView
        val cardView: CardView

        init {
            tvName = view.findViewById(R.id.tvName)
            tvPhone = view.findViewById(R.id.tvPhone)
            tvEmail = view.findViewById(R.id.tvEmail)
            tvPosts = view.findViewById(R.id.tvPosts)
            cardView = view.findViewById(R.id.cardview)
        }

        fun bind(transaction: User) {
            tvPosts.setOnClickListener {
                transaction?.let { it1 -> listener.onUserSelected(it1) }
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_user, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.tvName.text = dataSetFilter[position].name
        viewHolder.tvPhone.text = dataSetFilter[position].phone
        viewHolder.tvEmail.text = dataSetFilter[position].email
        viewHolder.bind(dataSetFilter[position])
    }

    override fun getItemCount() = dataSetFilter.size

    interface OnUserItemInteractionListener {
        fun onUserSelected(user: User)
        fun isEmptyList(isEmpty: Boolean)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    dataSetFilter = dataSet
                } else {
                    val resultList = ArrayList<User>()
                    resultList.addAll(dataSet.filter {
                        it.name.lowercase().contains(charSearch.lowercase(), true)
                    })
                    dataSetFilter = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = dataSetFilter
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                dataSetFilter = results?.values as ArrayList<User>
                listener.isEmptyList(dataSetFilter.isNullOrEmpty())
                notifyDataSetChanged()
            }

        }
    }
}