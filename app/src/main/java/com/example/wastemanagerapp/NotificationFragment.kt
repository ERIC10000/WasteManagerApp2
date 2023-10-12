package com.example.wastemanagerapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wastemanagerapp.adapters.NotificationAdapter
import com.example.wastemanagerapp.helpers.ApiHelper
import com.example.wastemanagerapp.helpers.Constant
import com.example.wastemanagerapp.models.Notification
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.GsonBuilder
import org.json.JSONArray
import org.json.JSONObject


class NotificationFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var notificationAdapter: NotificationAdapter
    lateinit var itemList: List<Notification>



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_notification, container, false)
        recyclerView = view.findViewById(R.id.recylerView)
        notificationAdapter = NotificationAdapter()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val searchBar : TextInputEditText = view.findViewById(R.id.searchBar)
        searchBar.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                filter(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })


        getNotifications()

        return view
    }



    private fun getNotifications() {

        val helper = ApiHelper(requireContext())
        val api = Constant.BASE_URL + "/check_notification"
        helper.get(api , object:ApiHelper.CallBack{
            override fun onSuccess(result: JSONArray?) {
                val gson = GsonBuilder().create()
                 itemList = gson.fromJson(
                    result.toString(),
                    Array<Notification>::class.java
                ).toList()
                notificationAdapter.setListItems(itemList)
                recyclerView.adapter = notificationAdapter
                Log.d("meso",itemList.toString())
            }

            override fun onSuccess(result: JSONObject?) {

            }

            override fun onFailure(result: String?) {

            }
        })


    }

    //Filter
    private fun filter(text: String) {



        // creating a new array list to filter our data.
        val filteredlist: ArrayList<Notification> = ArrayList()
        // running a for loop to compare elements.
        for (item in itemList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.title.lowercase().contains(text.lowercase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            //Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show()
            notificationAdapter.filterList(filteredlist)
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            notificationAdapter.filterList(filteredlist)
        }
    }

}