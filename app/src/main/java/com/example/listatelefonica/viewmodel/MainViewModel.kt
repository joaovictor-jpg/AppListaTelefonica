package com.example.listatelefonica.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.listatelefonica.database.ContatoRepository
import com.example.listatelefonica.model.Contato

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val listaContato: MutableLiveData<List<Contato>> = MutableLiveData(listOf())
    private val contatoRepository = ContatoRepository(application.applicationContext)

    fun listaContato(): LiveData<List<Contato>> {
        return listaContato
    }

    fun getListaContato() {
        listaContato.value = contatoRepository.selectAll()
    }

}