package com.example.final_project.adapter

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.CourseDetailsActivity
import com.example.final_project.Database.DatabaseInfo
import com.example.final_project.R
import com.example.final_project.model.Course
import kotlinx.android.synthetic.main.rv_item.view.*


class CourseAdapter(var activity: Activity, var data: ArrayList<Course>) :
    RecyclerView.Adapter<CourseAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvID = itemView.tvID
        val tvName = itemView.tvName
        val tvInstructorName = itemView.tvInstructorName
        val btnDetails = itemView.btnDetails
        val btnDelete = itemView.btnDelete

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val root = LayoutInflater.from(activity).inflate(R.layout.rv_item, parent, false)
        return MyViewHolder(root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvID.text = data[position].Id.toString()
        holder.tvName.text = data[position].name
        holder.tvInstructorName.text = data[position].InstructorName

        holder.btnDetails.setOnClickListener {
            val i = Intent(activity, CourseDetailsActivity::class.java)
            i.putExtra("data", data[position])

            activity.startActivity(i)
        }


        holder.btnDelete.setOnClickListener {

            val builder = AlertDialog.Builder(activity)
            builder.setTitle("Delete Course !")
            builder.setMessage("Are you Sure to Delete ?")

            builder.setPositiveButton("Yes") { _, _ ->

                val dba = DatabaseInfo(activity)
                if (dba.deleteCourse((data[position].Id))) {
                    data.removeAt(position)
                    notifyDataSetChanged()
                } else {
                    Toast.makeText(activity, "Delete Failed", Toast.LENGTH_SHORT).show()
                }
            }

            builder.setNegativeButton("No") { d, _ ->
                d.dismiss()
            }
            builder.create().show()
        }



    }

    override fun getItemCount(): Int {
        return data.size
    }
}