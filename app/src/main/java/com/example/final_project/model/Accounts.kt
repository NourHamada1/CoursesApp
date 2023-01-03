package com.example.final_project.model


data class Accounts(var id:Int, var name:String?, var email:String?, var password:String?, var date:String?) {

    companion object  {
        const val TABLE_NAME = "Accounts"
        const val COL_ID = "id"
        const val COL_EMAIL = "email"
        const val COL_NAME = "name"
        const val COL_PASSWORD = "password"
        const val COL_DATE = "date"
        const val TABLE_CREATE =
            "create table $TABLE_NAME($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COL_NAME TEXT NOT NULL, $COL_PASSWORD TEXT NOT NULL, $COL_EMAIL TEXT NOT NULL, $COL_DATE TEXT NOT NULL)"


    }
}