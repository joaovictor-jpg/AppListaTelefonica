package com.example.listatelefonica.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.listatelefonica.database.ContatoRepository
import com.example.listatelefonica.model.Contato

class NovoContatoViewModel(application: Application): AndroidViewModel(application) {
    private val novoContato = MutableLiveData<Long>()
    private val repository = ContatoRepository(application)

    fun novoContato(): LiveData<Long> {
        return novoContato
    }

    fun insert(nome: String, email: String, endereco: String, telefone: String, imageID: Int) {
        novoContato.value = repository.insert(
            Contato(
                nome = nome,
                email = email,
                endereco = endereco,
                telefone = telefone,
                imageId = imageID
            )
        )
    }
}