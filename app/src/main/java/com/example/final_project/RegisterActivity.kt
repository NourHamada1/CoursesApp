package com.example.final_project

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.final_project.Database.DatabaseHelper
import com.example.final_project.databinding.ActivityRegisterBinding
import com.example.final_project.model.Accounts
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*
import kotlin.collections.ArrayList

class RegisterActivity : AppCompatActivity() {

    companion object{
        val Account = ArrayList<Accounts>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val RegisterName = RegName.text
        val RegisterEmail = RegEmail.text
        val RegisterPassword = RegPassword.text
        val RegisterDate = RegDate.text




        binding.btnReg.setOnClickListener {
            if (binding.RegName.text.isEmpty() || binding.RegEmail.text.isEmpty()
                || binding.RegPassword.text.isEmpty() ||  binding.RegDate.text.isEmpty() ){
                Toast.makeText(this, "Fill the Fields", Toast.LENGTH_SHORT).show()
            }else{
                val db = DatabaseHelper(this)
                if (db.insertAccount(
                        binding.RegName.text.toString(),
                        binding.RegEmail.text.toString(),
                        binding.RegPassword.text.toString(),
                        binding.RegDate.text.toString()
                    )
                ) {

                    val i = Intent(this, InformationActivity::class.java)
                    Account.add(Accounts(1,"$RegisterName", "$RegisterEmail", "$RegisterPassword", "$RegisterDate"))

                }
                val i = Intent(this, HomeActivity::class.java)
                startActivity(i)
                finish()
                Toast.makeText(applicationContext, "Registered Successfully", Toast.LENGTH_SHORT).show()
            }
        }
        binding.RegDate.setOnClickListener {
            val currentDate = Calendar.getInstance()
            val d = currentDate.get(Calendar.DAY_OF_MONTH)
            val mm = currentDate.get(Calendar.MONTH)
            val yy = currentDate.get(Calendar.YEAR)
            val picker = DatePickerDialog(
                this,
                { _, year, month, day -> RegDate.setText("$year : ${month + 1} : $day") },
                yy,
                mm,
                d
            )
            picker.show()
        }
    }
}