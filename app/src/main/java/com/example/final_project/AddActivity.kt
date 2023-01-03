package com.example.final_project

import android.Manifest
import android.app.Activity
import android.app.TimePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.example.final_project.Database.DatabaseInfo
import com.example.final_project.databinding.ActivityAddBinding
import kotlinx.android.synthetic.main.activity_add.*
import java.util.*

class AddActivity : AppCompatActivity() {
    var imageURI:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnAddCourse.setOnClickListener {

            if (binding.txtAddCourseName.text.isEmpty() || binding.txtAddCourseInstructorName.text.isEmpty()
                || binding.txtAddCourseOrigin.text.isEmpty() || binding.txtAddCourseIngredients.text.isEmpty() ||  binding.txtAddDuration.text.isEmpty()){
                Toast.makeText(applicationContext, "Fill The Fields", Toast.LENGTH_SHORT).show()
            }else{
                val dba = DatabaseInfo(this)
                if (dba.insertCourse(binding.txtAddCourseName.text.toString(), binding.txtAddCourseInstructorName.text.toString(),
                        binding.txtAddCourseOrigin.text.toString(),binding.txtAddCourseIngredients.text.toString(), binding.txtAddDuration.text.toString())){
                    Toast.makeText(applicationContext, "Course Added Successfully", Toast.LENGTH_SHORT).show()
                    txtAddCourseName.text.clear()
                    txtAddCourseInstructorName.text.clear()
                    txtAddCourseOrigin.text.clear()
                    txtAddCourseIngredients.text.clear()
                    txtAddDuration.text.clear()


                }else{
                    Toast.makeText(applicationContext, "Failed to Add", Toast.LENGTH_SHORT).show()
                }
            }


        }

        binding.imgPick.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(gallery, 100)
        }

        binding.btnCamera.setOnClickListener {


            if (Build.VERSION.SDK_INT >= 23) {

                if (checkSelfPermission(Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                    requestPermissions(arrayOf(Manifest.permission.CAMERA),300)
                    return@setOnClickListener
                }else{
                    val camera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(camera,200)
                }
            }else{
                val camera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(camera,200)
            }
        }

        binding.btnViewCourses.setOnClickListener {
            val i = Intent(applicationContext, HomeActivity::class.java)
            startActivity(i)

        }

        binding.txtAddDuration.setOnClickListener {
            val currentTime = Calendar.getInstance()
            val hour = currentTime.get(Calendar.HOUR_OF_DAY)
            val minute = currentTime.get(Calendar.MINUTE)

            val picker = TimePickerDialog(
                this, { _, h, m -> txtAddDuration.setText("$h : $m") }, hour, minute, false
            )
            picker.show()
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 100)
        {
            imgPick.setImageURI(data!!.data)
            imageURI=data.data.toString()
        }else if(resultCode == Activity.RESULT_OK && requestCode == 200)
        {
            val bitmap = data!!.extras!!.get("data")
            imgPick.setImageBitmap(bitmap as Bitmap)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){
            300 ->{
                if (grantResults[0]== PackageManager.PERMISSION_GRANTED)
                {
                    val camera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(camera,200)
                }else{
                    finish()
                }
            }
        }
    }



}