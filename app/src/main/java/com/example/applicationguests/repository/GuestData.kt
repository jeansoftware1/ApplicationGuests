package com.example.applicationguests.repository

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//SALVANDO DADOS NO BANCO DE DADOS, COM O ROOM
@Entity(tableName = "Guest")
class GuestData {

    //O ROOM AUTOGERA O ID
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "name")
    var name: String = ""

    @ColumnInfo(name = "presence")
    var presence: Boolean = false


}