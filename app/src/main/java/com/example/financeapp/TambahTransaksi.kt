package com.example.financeapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.financeapp.databinding.ActivityTambahTransaksiBinding
import kotlinx.coroutines.DelicateCoroutinesApi


@OptIn(DelicateCoroutinesApi::class)
class TambahTransaksi : AppCompatActivity() {
    private lateinit var binding: ActivityTambahTransaksiBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityTambahTransaksiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //installSplashScreen()

        binding.labelInput.addTextChangedListener {
            if(it!!.count()>0)
                binding.labelLayout.error = null
        }

        binding.jumlahInput.addTextChangedListener {
            if(it!!.count()>0)
                binding.jumlahLayout.error = null
        }

        binding.tambahTransaksi.setOnClickListener{
            val label= binding.labelInput.text.toString()
            val amount= binding.jumlahInput.text.toString().toDoubleOrNull()

            if(label.isEmpty())
                binding.labelLayout.error ="Masukkan input yang sesuai"

            if(amount == null)
                binding.jumlahLayout.error="Masukkan input yang sesuai"
        }
        binding.tutupInput.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java )
            startActivity(intent)
            finish()
        }
    }
}