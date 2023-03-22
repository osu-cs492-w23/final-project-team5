package com.example.myapplication_final

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ActivityListAdapter(

) : RecyclerView.Adapter<ActivityListAdapter.ActivityListViewHolder>(){
    private var activityList = mutableListOf<BoredData>()

    fun addActivity(newActivity: BoredData){
        activityList.add(newActivity)
        notifyDataSetChanged()
    }

    override fun getItemCount() = activityList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityListViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_list_item, parent, false)
        return ActivityListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ActivityListViewHolder, position: Int) {
        holder.bind(activityList[position])
    }

    class ActivityListViewHolder (
        itemView: View,
    ): RecyclerView.ViewHolder(itemView) {
        private var currentActivity:BoredData? = null
        private val activityName: TextView = itemView.findViewById(R.id.activity_name)

        fun bind(activity:BoredData){
            currentActivity=activity
            activityName.text = activity.activity
        }
    }


}