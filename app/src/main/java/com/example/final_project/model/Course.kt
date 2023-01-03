package com.example.final_project.model

import android.os.Parcel
import android.os.Parcelable

data class Course(
    var Id: Int,
    var name: String?,
    var InstructorName: String?,
    var Language: String?,
    var Description: String?,
    var Duration: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()

    )


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(Id)
        parcel.writeString(name)
        parcel.writeString(InstructorName)
        parcel.writeString(Language)
        parcel.writeString(Description)
        parcel.writeString(Duration)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Course> {
        const val TABLE_NAME = "course"
        const val COL_ID = "id"
        const val COL_NAME = "name"
        const val COL_INSTRUCTOR_NAME = "instructor_name"
        const val COL_LANGUAGE = "language"
        const val COL_DESCRIPTION = "description"
        const val COL_DURATION = "duration"
        const val TABLE_CREATE =
            "create table $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COL_NAME TEXT NOT NULL, $COL_INSTRUCTOR_NAME TEXT NOT NULL, $COL_LANGUAGE TEXT NOT NULL, $COL_DESCRIPTION TEXT NOT NULL," +
                    "$COL_DURATION TEXT)"


        override fun createFromParcel(parcel: Parcel): Course {
            return Course(parcel)


        }

        override fun newArray(size: Int): Array<Course?> {
            return arrayOfNulls(size)
        }
    }
}