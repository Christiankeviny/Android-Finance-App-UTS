package com.example.financeapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.financeapp.databinding.ActivityMainBinding
import kotlinx.coroutines.DelicateCoroutinesApi


@OptIn(DelicateCoroutinesApi::class)
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var transaksilain: ArrayList<Transaksi>
    private lateinit var transactionAdapter: TransaksiAdaptor
    private lateinit var linearlayoutManager: LinearLayoutManager
    //private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        transaksilain = arrayListOf(
            Transaksi("Weekend budget", 400000.00),
            Transaksi("Makan", -30000.00),
            Transaksi("Bensin", -45000.00),
            Transaksi("Jajan minum", -25000.00),
            Transaksi("Uang tambahan", 75000.00),
        )

        transactionAdapter = TransaksiAdaptor(transaksilain)
        linearlayoutManager = LinearLayoutManager(this)

        updateDasboard()

        binding.recyclerview.apply {
            adapter = transactionAdapter
            layoutManager = linearlayoutManager
        }

        binding.addBtn.setOnClickListener {
            val intent = Intent(this, TambahTransaksi::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun updateDasboard() {
      val totalAmount = transaksilain.map { it.amount }.sum()
      val budgetAmount = transaksilain.filter { it.amount > 0 }.map { it.amount }.sum()
      val expenseAmount = totalAmount - budgetAmount

      binding.balance.text = "Rp %.2f".format(totalAmount)
      binding.budget.text = "Rp %.2f".format(budgetAmount)
      binding.expense.text = "Rp %.2f".format(expenseAmount)
    }
}
