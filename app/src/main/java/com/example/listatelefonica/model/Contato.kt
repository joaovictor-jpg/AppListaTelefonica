package com.example.listatelefonica.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Contato")
data class Contato(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nome: String = "",
    val endereco: String = "",
    val telefone: String = "",
    val email: String = "",
    val imageId: Int = 0
)