package com.example.listatelefonica.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.listatelefonica.R
import com.example.listatelefonica.databinding.ActivityDetalhesContatoBinding
import com.example.listatelefonica.databinding.ActivityNovoContatoBinding
import com.example.listatelefonica.model.Contato
import com.example.listatelefonica.viewmodel.DetalheContatoViewModel
import java.lang.Exception

class DetalhesContatoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetalhesContatoBinding
    private lateinit var viewModel: DetalheContatoViewModel
    private lateinit var launcher: ActivityResultLauncher<Intent>
    private lateinit var i: Intent
    private var imagemId: Int = -1
    private lateinit var contato: Contato

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalhesContatoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        i = intent

        val id = i.getIntExtra("id", 0)
        if (id <= 0) {
            setResult(0, i)
            finish()
        }

        viewModel = ViewModelProvider(this)[DetalheContatoViewModel::class.java]
        observe()

        viewModel.getContato(id)

        binding.buttonCancelar.setOnClickListener {
            chengeEditar(false)
            binding.layoutEditar.visibility = View.VISIBLE
            binding.layoutEditarEliminar.visibility = View.GONE
        }

        binding.buttonEditar.setOnClickListener {
            chengeEditar(true)
            binding.layoutEditar.visibility = View.GONE
            binding.layoutEditarEliminar.visibility = View.VISIBLE
        }

        binding.imagemFoto.setOnClickListener {
            if (binding.editNome.isEnabled) {
                val i = Intent(applicationContext, SelecionarImagemActivity::class.java)
                launcher.launch(i)
            }
        }

        binding.buttonEliminar.setOnClickListener {
            viewModel.delete(contato)
            setResult(1, i)
            finish()
        }

        binding.buttonGravar.setOnClickListener {
            viewModel.update(
                Contato(
                    id = contato.id,
                    nome = binding.editNome.text.toString(),
                    endereco = binding.editEndereco.text.toString(),
                    telefone = binding.editTelefone.text.toString(),
                    email = binding.editEmail.text.toString(),
                    imageId = imagemId
                )
            )
            setResult(1, i)
            finish()
        }

        binding.buttonVoltar.setOnClickListener { finish() }

        binding.imagemTelefone.setOnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + contato.telefone)
            startActivity(dialIntent)
        }

        binding.imageEmail.setOnClickListener {
            val destinatario = contato.email
            val assunto = "Contato"
            val mensagem = "Enviado da Lista de Telefone APP"

            sendEmail(destinatario, assunto, mensagem)
        }

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.data != null && it.resultCode == 1) {
                imagemId = it.data?.getIntExtra("id", 0)!!
                if (imagemId > 0) {
                    binding.imagemFoto.setImageResource(imagemId)
                }
            }
        }
        chengeEditar(false)
    }

    private fun sendEmail(destinatario: String, assunto: String, mensagem: String) {
        val i = Intent(Intent.ACTION_SEND)
        i.data = Uri.parse("mailto:")
        i.type = "text/plain"
        i.putExtra(Intent.EXTRA_EMAIL, arrayOf(destinatario))
        i.putExtra(Intent.EXTRA_SUBJECT, assunto)
        i.putExtra(Intent.EXTRA_TEXT, mensagem)

        try {
            startActivity(Intent.createChooser(i, "Escolha o cliente de email"))
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun observe() {
        viewModel.contato().observe(this, {
            contato = it
            populate()
        })

        viewModel.delete().observe(this, {
            Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.update().observe(this, {
            Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun populate() {
        binding.editNome.setText(contato.nome)
        binding.editEndereco.setText(contato.endereco)
        binding.editTelefone.setText(contato.telefone)
        binding.editEmail.setText(contato.email)
        if (contato.imageId > 0) {
            imagemId = contato.imageId
            binding.imagemFoto.setImageResource(contato.imageId)
        } else {
            binding.imagemFoto.setImageResource(R.drawable.profiledefault)
        }
    }

    private fun chengeEditar(status: Boolean) {
        binding.imagemFoto.isEnabled = status
        binding.editNome.isEnabled = status
        binding.editEndereco.isEnabled = status
        binding.editTelefone.isEnabled = status
        binding.editEmail.isEnabled = status
    }
}