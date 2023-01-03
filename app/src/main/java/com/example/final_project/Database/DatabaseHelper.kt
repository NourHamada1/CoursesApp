package com.example.final_project.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.final_project.model.Accounts

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context,"accountsdb",null,1) {


    private var db: SQLiteDatabase = this.writableDatabase

    override fun onCreate(p0: SQLiteDatabase?) {
        p0!!.execSQL(Accounts.TABLE_CREATE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE IF EXISTS ${Accounts.TABLE_NAME}")
        onCreate(p0)
    }

    fun insertAccount(name:String , password:String, email: String, date:String) : Boolean{
        val cv = ContentValues()
        cv.put(Accounts.COL_NAME,name)
        cv.put(Accounts.COL_EMAIL,email)
        cv.put(Accounts.COL_PASSWORD,password)
        cv.put(Accounts.COL_DATE,date)
        return db.insert(Accounts.TABLE_NAME,null,cv)>0
    }

    fun getAllAccounts() : ArrayList<Accounts>{
        val account = ArrayList<Accounts>()
        val c = db.rawQuery("select * from ${Accounts.TABLE_NAME} ORDER BY ${Accounts.COL_ID} desc",null)
        c.moveToFirst()
        while (!c.isAfterLast){
            val a = Accounts(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4))
            account.add(a)
            c.moveToNext()
        }
        c.close()
        return account
    }

    fun deleteAccount(id:Int) : Boolean{
        return db.delete(Accounts.TABLE_NAME,"${Accounts.COL_ID} = $id",null)>0
    }

    fun updateAccount(oldId:Int, name:String,password: String, email:String, date: String) : Boolean{
        val cv = ContentValues()
        cv.put(Accounts.COL_NAME,name)
        cv.put(Accounts.COL_EMAIL,email)
        cv.put(Accounts.COL_PASSWORD,password)
        cv.put(Accounts.COL_DATE,date)

        return db.update(Accounts.TABLE_NAME,cv,"${Accounts.COL_ID} == $oldId",null) >0
    }



    companion object{
        const val DATABSAE_NAME = "accountsdb"
        const val DATABASE_VERSION = 1
    }
}