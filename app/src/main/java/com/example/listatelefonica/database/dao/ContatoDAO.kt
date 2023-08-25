package com.example.listatelefonica.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.listatelefonica.model.Contato

@Dao
interface ContatoDAO {
    @Insert
    fun insert(contato: Contato): Long
    @Update
    fun update(contato: Contato): Int
    @Delete
    fun Delete(contato: Contato): Int
    @Query("SELECT * FROM Contato")
    fun selectAll(): List<Contato>
    @Query("SELECT * FROM Contato WHERE id=:id")
    fun selectById(id: Int): Contato
}