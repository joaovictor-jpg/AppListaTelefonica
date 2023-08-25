package com.example.listatelefonica.adapter.listener

import com.example.listatelefonica.model.Contato

class ContatoOnClickListener(val clickListener: (contato: Contato) -> Unit) {
    fun onClick(contato: Contato) = clickListener
}