package com.example.listatelefonica.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.listatelefonica.R
import com.example.listatelefonica.databinding.ActivityDetalhesContatoBinding
import com.example.listatelefonica.databinding.ActivitySelecionarImagemBinding

class SelecionarImagemActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelecionarImagemBinding
    private lateinit var i: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelecionarImagemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        i = intent

        binding.imageProfile1.setOnClickListener { senID(R.drawable.profile1) }
        binding.imageProfile2.setOnClickListener { senID(R.drawable.profile2) }
        binding.imageProfile3.setOnClickListener { senID(R.drawable.profile3) }
        binding.imageProfile4.setOnClickListener { senID(R.drawable.profile4) }
        binding.imageProfile5.setOnClickListener { senID(R.drawable.profile5) }
        binding.imageProfile6.setOnClickListener { senID(R.drawable.profile6) }
        binding.buttonRemoverImagem.setOnClickListener { senID(R.drawable.profiledefault) }
    }

    private fun senID(imagemId: Int) {
        i.putExtra("id", imagemId)
        setResult(1, i)
        finish()
    }
}