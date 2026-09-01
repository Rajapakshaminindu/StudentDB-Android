package com.example.androiddbconnectivity

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "UserDB"
        private const val DATABASE_VERSION = 1
        private const val TABLE_USERS = "users"

        private const val COL_ID = "id"
        private const val COL_NAME = "name"
        private const val COL_REG_NO = "regNo"
        private const val COL_DEPT = "department"
        private const val COL_EMAIL = "email"
        private const val COL_PASSWORD = "password"
        private const val COL_PHONE = "phone"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = ("CREATE TABLE " + TABLE_USERS + "("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_NAME + " TEXT,"
                + COL_REG_NO + " TEXT,"
                + COL_DEPT + " TEXT,"
                + COL_EMAIL + " TEXT,"
                + COL_PASSWORD + " TEXT,"
                + COL_PHONE + " TEXT" + ")")
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    fun insertUser(name: String, regNo: String, dept: String, email: String, pass: String, phone: String): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_NAME, name)
        contentValues.put(COL_REG_NO, regNo)
        contentValues.put(COL_DEPT, dept)
        contentValues.put(COL_EMAIL, email)
        contentValues.put(COL_PASSWORD, pass)
        contentValues.put(COL_PHONE, phone)
        
        val success = db.insert(TABLE_USERS, null, contentValues)
        db.close()
        return success
    }

    fun checkUserLogin(email: String, pass: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_USERS WHERE $COL_EMAIL = ? AND $COL_PASSWORD = ?", arrayOf(email, pass))
        val exists = cursor.count > 0
        cursor.close()
        db.close()
        return exists
    }

    fun getUserDetails(email: String): User? {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_USERS WHERE $COL_EMAIL = ?", arrayOf(email))
        var user: User? = null
        if (cursor.moveToFirst()) {
            user = User(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID)),
                name = cursor.getString(cursor.getColumnIndexOrThrow(COL_NAME)),
                regNo = cursor.getString(cursor.getColumnIndexOrThrow(COL_REG_NO)),
                department = cursor.getString(cursor.getColumnIndexOrThrow(COL_DEPT)),
                email = cursor.getString(cursor.getColumnIndexOrThrow(COL_EMAIL)),
                phone = cursor.getString(cursor.getColumnIndexOrThrow(COL_PHONE))
            )
        }
        cursor.close()
        db.close()
        return user
    }
}