package com.example.final_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.final_project.model.Course
import kotlinx.android.synthetic.main.activity_course_details.*
class CourseDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_details)

        val details = intent.getParcelableExtra<Course>("data")
        tvDetails.text = details.toString()
    }
}