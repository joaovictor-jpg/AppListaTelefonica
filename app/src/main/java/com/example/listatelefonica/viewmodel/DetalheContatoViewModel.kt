package com.example.listatelefonica.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.listatelefonica.database.ContatoRepository
import com.example.listatelefonica.model.Contato

class DetalheContatoViewModel(application: Application) : AndroidViewModel(application) {

    private var contato = MutableLiveData<Contato>()
    private var update = MutableLiveData<String>()
    private var delete = MutableLiveData<String>()
    private var contatoRepository = ContatoRepository(application.applicationContext)

    fun contato(): LiveData<Contato> {
        return contato
    }

    fun update(): LiveData<String> {
        return update
    }

    fun delete(): LiveData<String> {
        return delete
    }

    fun getContato(id: Int) {
        contato.value = contatoRepository.selectById(id)
    }

    fun update(contato: Contato) {
        if (contatoRepository.update(contato) > 0) {
            update.value = "Update ok"
        } else {
            update.value = "Erro na update"
        }
    }

    fun delete(contato: Contato) {
        if (contatoRepository.Delete(contato) > 0) {
            delete.value = "Delete ok"
        } else {
            delete.value = "Erro no delete"
        }
    }
}