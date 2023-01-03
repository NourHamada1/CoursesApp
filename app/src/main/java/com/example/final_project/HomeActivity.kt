package com.example.final_project

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.final_project.Database.DatabaseInfo
import com.example.final_project.adapter.CourseAdapter
import com.example.final_project.databinding.ActivityHomeBinding
import com.example.final_project.model.Accounts
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val dba = DatabaseInfo(this)


        val data = dba.getAllCourses()

        rv_course.layoutManager = LinearLayoutManager(this)

        val courseAdapter = CourseAdapter(this, data)
        rv_course.adapter = courseAdapter

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val add = arrayOf(Intent(this, AddActivity::class.java))
        val information = arrayOf(Intent(this, InformationActivity::class.java))
        when (item.itemId) {
            R.id.btnAdd -> startActivities(add)
        }

        when (item.itemId) {
            R.id.info -> startActivities(information)
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }



}

