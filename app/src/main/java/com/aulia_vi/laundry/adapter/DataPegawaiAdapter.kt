package com.aulia_vi.laundry.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.aulia_vi.laundry.R
import com.aulia_vi.laundry.modeldata.ModelPegawai

class DataPegawaiAdapter(
    private val listPegawai: ArrayList<ModelPegawai>
) : RecyclerView.Adapter<DataPegawaiAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_data_pegawai, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listPegawai[position]
        holder.tvID.text = item.idPegawai
        holder.tvNama.text = item.namaPegawai
        holder.tvAlamat.text = item.alamatPegawai
        holder.tvNoHP.text = item.noHPPegawai
        holder.tvTerdaftar.text = item.terdaftar
        holder.tvCabang.text = item.cabang
        holder.cvCard.setOnClickListener {
        }

        holder.btHubungi.setOnClickListener {
        }

        holder.btLihat.setOnClickListener {
        }
    }

    override fun getItemCount(): Int {
        return listPegawai.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvCard: CardView = itemView.findViewById(R.id.cvCARD_PEGAWAI)
        val tvID: TextView = itemView.findViewById(R.id.tvCARD_PEGAWAI_Id)
        val tvNama: TextView = itemView.findViewById(R.id.tvCARD_PEGAWAI_Nama)
        val tvAlamat: TextView = itemView.findViewById(R.id.tvCARD_PEGAWAI_Alamat)
        val tvNoHP: TextView = itemView.findViewById(R.id.tvCARD_PEGAWAI_NoHP)
        val tvCabang: TextView =itemView.findViewById(R.id.tvCARD_PEGAWAI_Cabang)
        val tvTerdaftar: TextView = itemView.findViewById(R.id.tvCARD_PEGAWAI_Terdaftar)
        val btHubungi: Button = itemView.findViewById(R.id.btCARD_PEGAWAI_Hubungi)
        val btLihat: Button = itemView.findViewById(R.id.btCARD_PEGAWAI_Lihat)
    }
}
