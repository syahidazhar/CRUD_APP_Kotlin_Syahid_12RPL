package com.example.crud_app_syahid_12rpl.helper

import android.app.DownloadManager.Query
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper

class db_helper(context: Context, factory: CursorFactory?):
        SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION){
            companion object{
                //Declare DB Name And DB Version, Table
                private val DATABASE_NAME = "crud_profile"
                private val DATABASE_VERSION = 1
                private val TABLE_NAME = "profile"
                val ID_COL = "id"
                val NAME_COL = "name"
                val AGE_COL = "age"

            }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = ("CREATE TABLE $TABLE_NAME ( $ID_COL INTEGER PRIMARY KEY, $NAME_COL TEXT," +
                " $AGE_COL TEXT)")

        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val DropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db!!.execSQL(DropTableQuery)
        onCreate(db)
    }

    fun addProfile(name: String, age: String){
        val values = ContentValues()
        values.put(NAME_COL,name)
        values.put(AGE_COL,age)

        val db = this.writableDatabase

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getProfile(): Cursor?{
        val db = this.readableDatabase

        return db.rawQuery("Select * FROM $TABLE_NAME", null)
    }

    fun CreateData(){
        val db = this.writableDatabase
        val query = ("CREATE TABLE $TABLE_NAME ( $ID_COL INTEGER PRIMARY KEY, $NAME_COL TEXT," +
                " $AGE_COL TEXT)")

        db!!.execSQL(query)
    }

    fun deleteData(){
        val db = this.writableDatabase
        val DropTableQuery = "DELETE FROM $TABLE_NAME"
        db!!.execSQL(DropTableQuery)
    }
}