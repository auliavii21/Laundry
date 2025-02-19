package com.aulia_vi.laundry.pegawai

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.aulia_vi.laundry.R
import com.aulia_vi.laundry.modeldata.ModelPegawai
import com.aulia_vi.laundry.modeldata.ModelPelanggan
import com.aulia_vi.laundry.pelanggan.TambahPelanggan
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.FirebaseDatabase

class DataPegawai : AppCompatActivity() {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("pegawai")
    lateinit var rvData_Pegawai: RecyclerView
    lateinit var  fabDATA_PEGAWAI_Tambah: FloatingActionButton
    lateinit var pegawaiList: ArrayList<ModelPegawai>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_data_pegawai)


        val fabTPegawai: FloatingActionButton = findViewById(R.id.fabTambahPegawai)
        fabTPegawai.setOnClickListener {
            val intent = Intent(this, TambahPegawai::class.java)
            startActivity(intent)
        }
    }
}