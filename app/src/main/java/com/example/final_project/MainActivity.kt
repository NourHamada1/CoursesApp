package com.example.final_project

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.final_project.Database.DatabaseHelper
import com.example.final_project.databinding.ActivityMainBinding
import com.example.final_project.model.Accounts
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }




        binding.btnLogin.setOnClickListener {
            if (binding.tvEmail.text.isEmpty() || binding.tvPassword.text.isEmpty() || !binding.checkBox.isChecked) {
                Toast.makeText(applicationContext, "Fill The Fields", Toast.LENGTH_SHORT).show()
            } else {


                val i = Intent(this,HomeActivity::class.java)
                startActivity(i)
                finish()
                Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show()
            }

        }
    }
}