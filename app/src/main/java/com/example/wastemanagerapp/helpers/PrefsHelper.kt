package com.example.wastemanagerapp.helpers

import android.content.Context
import android.content.SharedPreferences

class PrefsHelper {
    companion object{
        fun savePrefs(context: Context, key: String, value:String){
            val pref: SharedPreferences = context.getSharedPreferences("store",
                Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = pref.edit()
            editor.putString(key,value)
            editor.apply()

        }// end save



        fun getPrefs(context: Context,key: String) : String{
            val pref:SharedPreferences = context.getSharedPreferences("store",Context.MODE_PRIVATE)
            val value = pref.getString(key, "") // if key is empty or does not exist return empty
            return value.toString()

        }// end get

        fun clearPrefsByKey(context: Context,key: String){
            val pref: SharedPreferences = context.getSharedPreferences("store",Context.MODE_PRIVATE)
            val editor = pref.edit()
            editor.remove(key)
            editor.apply()

        }//end
        fun clearPrefs(context: Context){
            val pref:SharedPreferences = context.getSharedPreferences("store",Context.MODE_PRIVATE)
            val editor = pref.edit()
            editor.clear()
            editor.apply()
        }

    }//end companion
}