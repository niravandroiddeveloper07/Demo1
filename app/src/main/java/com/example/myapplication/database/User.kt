package com.example.myapplication.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName



@Entity(tableName = "user_table")
data class User (

	@PrimaryKey @ColumnInfo(name = "id") val id : Int,
	@ColumnInfo(name= "email") val email : String,
	@ColumnInfo(name="first_name") val first_name : String,
	@ColumnInfo(name="last_name") val last_name : String,
	@ColumnInfo(name="avatar") val avatar : String
)