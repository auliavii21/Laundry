package com.aulia_vi.laundry.pegawai

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aulia_vi.laundry.R
import com.aulia_vi.laundry.modeldata.ModelPegawai
import com.aulia_vi.laundry.modeldata.ModelPelanggan
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TambahPegawai : AppCompatActivity() {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("pegawai")

    lateinit var tvJudul: TextView
    lateinit var etNama: EditText
    lateinit var etAlamat: EditText
    lateinit var etNoHP: EditText
    lateinit var etCabang: EditText
    lateinit var btSimpan: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah_pegawai)
        init()
        getData()

        btSimpan.setOnClickListener{
            if (cekValidasi()){
                simpan()
            }
        }

    }
    fun init(){
        tvJudul=findViewById(R.id.tvTambahPegawai_Judul)
        etNama=findViewById(R.id.etTambahPegawai_Nama)
        etAlamat=findViewById(R.id. etTambahPegawai_Alamat)
        etNoHP=findViewById(R.id. etTambahPegawai_NoHP)
        etCabang=findViewById(R.id. etTambahPegawai_Cabang)
        btSimpan=findViewById(R.id. btTambahPegawai_Simpan)
    }
    fun cekValidasi():Boolean {
        val nama = etNama.text.toString()
        val alamat = etAlamat.text.toString()
        val noHP = etNoHP.text.toString()
        val cabang = etCabang.text.toString()

        if (nama.isEmpty()) {
            etNama.error = getString(R.string.erNama)
            Toast.makeText(this, getString(R.string.erNama), Toast.LENGTH_SHORT).show()
            etNama.requestFocus()
            return false
        }
        if (alamat.isEmpty()) {
            etAlamat.error = getString(R.string.erAlamat)
            Toast.makeText(this, getString(R.string.erAlamat), Toast.LENGTH_SHORT).show()
            etAlamat.requestFocus()
            return false
        }
        if (noHP.isEmpty()) {
            etNoHP.error = getString(R.string.erNoHP)
            Toast.makeText(this, getString(R.string.erNoHP), Toast.LENGTH_SHORT).show()
            etNoHP.requestFocus()
            return false
        }
        if (cabang.isEmpty()) {
            etCabang.error = getString(R.string.erCabang)
            Toast.makeText(this, getString(R.string.erCabang), Toast.LENGTH_SHORT).show()
            etCabang.requestFocus()
            return false
        }
        return true
    }
    fun getData() {
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val pegawaiList = mutableListOf<ModelPegawai>()
                    for (dataSnapshot in snapshot.children) {
                        val pegawai = dataSnapshot.getValue(ModelPegawai::class.java)
                        pegawai?.let { pegawaiList.add(it) }
                    }
                    Log.d("Data Pegawai", "Data ditemukan: $pegawaiList")
                } else {
                    Log.d("Data Pegawai", "Tidak ada data pegawai")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Database Error", error.message)
                Toast.makeText(this@TambahPegawai, "Gagal memuat data", Toast.LENGTH_SHORT).show()
            }
        })
    }
    fun simpan(){
        val pegawaiBaru=myRef.push()
        val pegawaiId=pegawaiBaru.key
        val currentTime=SimpleDateFormat("dd-MMMM-yyyy HH:mm:ss", Locale.getDefault()).format(Date())

        val data = ModelPegawai(
            pegawaiId.toString(),
            etNama.text.toString(),
            etAlamat.text.toString(),
            etNoHP.text.toString(),
            etCabang.text.toString(),
            currentTime
        )
        pegawaiBaru.setValue(data)
            .addOnSuccessListener {
            Toast.makeText(this, getString(R.string.sukses_simpan_pelanggan), Toast.LENGTH_SHORT).show()
            finish()
        }
            .addOnFailureListener {
                Toast.makeText(this, getString(R.string.gagal_simpan_pelanggan), Toast.LENGTH_SHORT).show()
            }
    }

}