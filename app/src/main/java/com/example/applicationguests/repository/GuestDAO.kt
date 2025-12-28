package com.example.applicationguests.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface GuestDAO {
    @Insert
    fun save(guest: GuestData): Long

    @Update
    fun update(guest: GuestData): Int

    @Delete
    fun delete(guest: GuestData)

    @Query("SELECT * FROM Guest WHERE id = :id")
    fun load(id: Int): GuestData

    @Query("SELECT * FROM Guest")
    fun getInvited(): List<GuestData>

    @Query("SELECT * FROM Guest WHERE presence = 1")
    fun getPresent(): List<GuestData>

    @Query("SELECT * FROM Guest WHERE presence = 0")
    fun getAbsent(): List<GuestData>
}