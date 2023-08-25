package com.example.listatelefonica.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listatelefonica.R
import com.example.listatelefonica.adapter.ContatoListAdapter
import com.example.listatelefonica.adapter.listener.ContatoOnClickListener
import com.example.listatelefonica.databinding.ActivityMainBinding
import com.example.listatelefonica.model.Contato
import com.example.listatelefonica.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var launcher: ActivityResultLauncher<Intent>
    private lateinit var adapter: ContatoListAdapter
    private var listaContatos: List<Contato> = listOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.recyclerViewContatos.layoutManager = LinearLayoutManager(this)

        binding.recyclerViewContatos.layoutManager = LinearLayoutManager(this)
        viewModel.getListaContato()
        observe()

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if(it.data != null && it.resultCode == 1) {
                viewModel.getListaContato()
            }
        }

        binding.buttonNovoContato.setOnClickListener {
            launcher.launch(Intent(applicationContext, NovoContatoActivity::class.java))
        }
    }

    private fun observe() {
        viewModel.listaContato().observe(this, {
            listaContatos = it
            placeAdapter()
        })
    }

    private fun placeAdapter() {
        adapter = ContatoListAdapter(listaContatos, ContatoOnClickListener {
            val i = Intent(this, DetalhesContatoActivity:: class.java)
            i.putExtra("id", it.id)
            launcher.launch(i)
        })
        binding.recyclerViewContatos.adapter = adapter
    }
}