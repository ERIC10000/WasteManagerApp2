package com.example.wastemanagerapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wastemanagerapp.R
import com.example.wastemanagerapp.models.Notification

class NotificationAdapter() :
    RecyclerView.Adapter<NotificationAdapter.ViewHolder>(){

    var itemList : List<Notification> = listOf()  //Its empty
    // declare a variable of type List<Model> (Empty)
     // its empty

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    // Adapter classes implements 3 methods
    // 1. onCreateViewHolder -> Used to call the single item file
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // access/inflate the single_lab.xml
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.single_notification,
            parent, false)
        return ViewHolder(view)
    }

    // 2. onBindViewHolder -> Used to bind(attach) data to the view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // find the views from single_bookings.xml and bind data from the model
        val time : TextView = holder.itemView.findViewById(R.id.textTime)
        val title: TextView = holder.itemView.findViewById(R.id.textMessage)
        val sender: TextView = holder.itemView.findViewById(R.id.tvSender)
        val body: TextView = holder.itemView.findViewById(R.id.textMessageBody)


        // Assume single lab from the list of labs(itemList)
        val singleNotification = itemList[position]

        // bind data to the singleBooking
        time.text = singleNotification.time
        title.text = singleNotification.title
        sender.text =  "Sender : " + singleNotification.sender
        body.text =  singleNotification.notifications

        // Clicking on a LabTest and proceeding to SingleLabTest Activity with data from the model
        // holder.itemView.setOnClickListener {
        //
        //        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    // Custom Functions
    // 1. Filter the itemList(contain the list of all Labs)
    // Used for searching.....
    fun filterList(filterList: List<Notification>){
        itemList = filterList
        notifyDataSetChanged()
    }

    // Earlier the itemList is empty
    // The function will get data from the API and pass to the ItemList

    fun setListItems(data: List<Notification>){
        itemList = data
        notifyDataSetChanged()
    }


}