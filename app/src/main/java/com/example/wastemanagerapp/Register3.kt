package com.example.wastemanagerapp

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatCheckBox
import com.example.wastemanagerapp.helpers.ApiHelper
import com.example.wastemanagerapp.helpers.Constant
import com.example.wastemanagerapp.helpers.PrefsHelper
import org.json.JSONArray
import org.json.JSONObject


class Register3 : AppCompatActivity() {
    lateinit var address : EditText
    lateinit var password : EditText
    lateinit var password2 : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register3)

        address  = findViewById(R.id.address)
        password  = findViewById(R.id.password)
        password2  = findViewById(R.id.password2)

        val checkBox : AppCompatCheckBox = findViewById(R.id.agreement)
        val button : AppCompatButton = findViewById(R.id.next3)


        checkBox.setOnCheckedChangeListener { _, isChecked ->
            button.isEnabled = isChecked
        }

        val firstName = PrefsHelper.getPrefs(this,"firstName")
        val lastName = PrefsHelper.getPrefs(this , "lastName")
        val email = PrefsHelper.getPrefs(this , "email")
        val constituency = PrefsHelper.getPrefs(this , "constituency")
        val county = PrefsHelper.getPrefs(this,"county")
        val idNumb = PrefsHelper.getPrefs(this , "idNumber")
        val mobileNumber = PrefsHelper.getPrefs(this,"mobileNumber")

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        val next : TextView =  findViewById(R.id.next3)
        next.setOnClickListener {
            if(address.text.isEmpty() || password.text.isEmpty()){
                Toast.makeText(applicationContext, "Please fill in all the fields", Toast.LENGTH_LONG).show()

            }else{
                if (password.text.toString() != password2.text.toString()){
                    Toast.makeText(applicationContext, "Your Password do not match", Toast.LENGTH_SHORT).show()
                }else{
                    post_data(firstName , lastName , email , constituency , county , idNumb , progressBar , mobileNumber )

                }

            }
        }
    }

    private fun post_data(firstName: String ,lastName: String ,email:String , constituency: String,
                          county:String , idNumb:String , progressBar: ProgressBar , mobileNumber: String){
        progressBar.visibility = View.VISIBLE
        val helper = ApiHelper(this)
        val api = Constant.BASE_URL + "/signup_new_picker"
        val body = JSONObject()
        body.put("firstName",firstName)
        body.put("lastName",lastName)
        body.put("county",county)
        body.put("constituency",constituency)
        body.put("mobileNumber",mobileNumber)
        body.put("email",email)
        body.put("idNumb",idNumb)
        body.put("address",address.text.toString())
        body.put("password",password.text.toString())

        helper.post(api , body , object: ApiHelper.CallBack{
            override fun onSuccess(result: JSONArray?) {

            }

            override fun onSuccess(result: JSONObject?) {
                progressBar.visibility = View.GONE
//                val intent = Intent(applicationContext , HomeActivity::class.java)
//                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                startActivity(intent)
//                finish()
                PrefsHelper.clearPrefs(applicationContext)

                val alertDialog = AlertDialog.Builder(this@Register3).create()
                alertDialog.setTitle("")
                val view =
                    LayoutInflater.from(this@Register3).inflate(R.layout.payment_alert_dialog, null, false)
                alertDialog.setView(view)
                val phone = view.findViewById<EditText>(R.id.emailLogin1)

                view.findViewById<Button>(R.id.pay).setOnClickListener {
                    mpesaPayment(phone.text.toString())

                    alertDialog.dismiss()
                }

                alertDialog.show()
            }

            override fun onFailure(result: String?) {
                Toast.makeText(applicationContext, result.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun mpesaPayment (phone: String ){
        val api = Constant.BASE_URL + "/making_contributions"
        val helper = ApiHelper(this)
        val body = JSONObject()
        body.put("phone",phone)
        body.put("amount","500")
        helper.post(api , body , object:ApiHelper.CallBack{
            override fun onSuccess(result: JSONArray?) {

            }

            override fun onSuccess(result: JSONObject?) {
                Toast.makeText(applicationContext, result.toString(), Toast.LENGTH_SHORT).show()
                val intent = Intent(applicationContext, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }

            override fun onFailure(result: String?) {
                Toast.makeText(applicationContext, result.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }


}


