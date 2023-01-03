package com.example.final_project.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.final_project.model.Course


class DatabaseInfo(context: Context) :
    SQLiteOpenHelper(context,"course",null,1) {


    private var db: SQLiteDatabase = this.writableDatabase

    override fun onCreate(p0: SQLiteDatabase?) {
        p0!!.execSQL(Course.TABLE_CREATE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE IF EXISTS ${Course.TABLE_NAME}")
        onCreate(p0)
    }

    fun insertCourse(name:String , instructor_name:String, language:String, description: String, duration: String) : Boolean{
        val cv = ContentValues()
        cv.put(Course.COL_NAME,name)
        cv.put(Course.COL_INSTRUCTOR_NAME,instructor_name)
        cv.put(Course.COL_LANGUAGE,language)
        cv.put(Course.COL_DESCRIPTION,description)
        cv.put(Course.COL_DURATION,duration)

        return db.insert(Course.TABLE_NAME,null,cv)>0
    }

    fun getAllCourses() : ArrayList<Course>{
        val course = ArrayList<Course>()
        val c = db.rawQuery("select * from ${Course.TABLE_NAME} ORDER BY ${Course.COL_ID} desc",null)
        c.moveToFirst()
        while (!c.isAfterLast){
            val f = Course(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5))
            course.add(f)
            c.moveToNext()
        }
        c.close()
        return course
    }

    fun deleteCourse(id:Int) : Boolean{
        return db.delete(Course.TABLE_NAME,"${Course.COL_ID} = $id",null)>0
    }

    fun updateCourse(oldId:Int, name:String , instructor_name:String, language:String, description: String, duration: String) : Boolean{
        val cv = ContentValues()
        cv.put(Course.COL_ID,oldId)
        cv.put(Course.COL_NAME,name)
        cv.put(Course.COL_INSTRUCTOR_NAME,instructor_name)
        cv.put(Course.COL_LANGUAGE,language)
        cv.put(Course.COL_DESCRIPTION,description)
        cv.put(Course.COL_DURATION,duration)


        return db.update(Course.TABLE_NAME,cv,"${Course.COL_ID} == $oldId",null) >0
    }

    companion object{
        const val DATABSAE_NAME = "course"
        const val DATABASE_VERSION = 1
    }
}