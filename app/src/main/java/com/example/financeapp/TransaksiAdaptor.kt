package com.example.financeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class TransaksiAdaptor(private val transaksilain: ArrayList<Transaksi>) :
    RecyclerView.Adapter<TransaksiAdaptor.HolderTransaksi> (){

    class HolderTransaksi(view: View) : RecyclerView.ViewHolder(view){
        val label : TextView = view.findViewById(R.id.label)
        val amount : TextView = view.findViewById(R.id.amount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderTransaksi {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.transaksi_layout,parent, false)
        return HolderTransaksi(view)
    }

    override fun onBindViewHolder(holder: HolderTransaksi, position: Int) {
        val transaksi = transaksilain[position]
        val context = holder.amount.context

        if (transaksi.amount >= 0){
            holder.amount.text ="+ Rp%.2f".format(transaksi.amount)
            holder.amount.setTextColor(ContextCompat.getColor(context, R.color.green))
        }else{
            holder.amount.text ="- Rp%.2f".format(Math.abs(transaksi.amount))
            holder.amount.setTextColor(ContextCompat.getColor(context, R.color.red))
        }

        holder.label.text = transaksi.label
    }

    override fun getItemCount(): Int {
        return transaksilain.size
    }
}