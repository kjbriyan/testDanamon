package com.example.testdanamon

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.testdanamon.model.UserData

class DatabaseHelper(contex: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(contex, DATABASE_NAME, factory, DATABASE_VERSION) {

    companion object {
        private val DATABASE_NAME = "user"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "gfg_table"
        val ID = "id"
        val EMAIL = "email"
        val PASS = "pass"
        val LEVEL = "level"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE $TABLE_NAME ($ID INTEGER PRIMARY KEY," +
                "$EMAIL TEXT, $PASS TEXT, $LEVEL )"

        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun register(email: String, pass: String, level: String):Boolean {
        val values = ContentValues()
        values.put(EMAIL,email)
        values.put(PASS,pass)
        values.put(LEVEL,level)

        val db = this.writableDatabase
        db.insert(TABLE_NAME,null,values)

        db.close()
        return true
    }

    fun login (email: String,pass: String):Cursor{
        val db = this.readableDatabase
        val query = db.rawQuery("SELECT * FROM $TABLE_NAME " +
                "WHERE $EMAIL = '$email' AND $PASS = '$pass'", null)
        Log.e("Databasehelper",""+query)
        return query
    }

    fun update(id:Int,email: String,pass: String,level: String):Boolean{
        val values = ContentValues()
        val db = this.writableDatabase

        values.put(EMAIL,email)
        values.put(PASS,pass)
        values.put(LEVEL,level)
        db.update(TABLE_NAME,values,"$ID = $id",null)
        return true
    }

    fun delete(id:Int){
        val db = this.writableDatabase
        db.delete(TABLE_NAME,"$ID = $id",null)
    }

    fun showUser(): ArrayList<UserData> {
        val db = this.readableDatabase
        val query = db.rawQuery("SELECT * FROM $TABLE_NAME",null)

        val data: ArrayList<UserData> = ArrayList()

        if (query.moveToFirst()){
            do {
                data.add(
                    UserData(
                        query.getInt(0),
                        query.getString(1),
                        query.getString(2),
                        query.getString(3)
                    )
                )
            }while (query.moveToNext())
        }
        query.close()
        return data
    }
}