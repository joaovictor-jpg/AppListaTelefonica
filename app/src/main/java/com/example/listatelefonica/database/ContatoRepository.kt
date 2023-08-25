package com.example.listatelefonica.database

import android.content.Context
import com.example.listatelefonica.model.Contato

class ContatoRepository(context: Context) {

    private val databaseHelper = DatabaseHelper.getDatabase(context).contatoDao()

    fun insert(contato: Contato): Long {
        return databaseHelper.insert(contato)
    }

    fun update(contato: Contato): Int {
        return databaseHelper.update(contato)
    }

    fun Delete(contato: Contato): Int {
        return databaseHelper.Delete(contato)
    }

    fun selectAll(): List<Contato> {
        return databaseHelper.selectAll()
    }

    fun selectById(id: Int): Contato {
        return databaseHelper.selectById(id)
    }

}