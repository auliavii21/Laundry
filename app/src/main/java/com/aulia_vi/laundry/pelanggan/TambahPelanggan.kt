package com.aulia_vi.laundry.pelanggan

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.aulia_vi.laundry.R
import com.aulia_vi.laundry.modeldata.ModelPelanggan
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class TambahPelanggan : AppCompatActivity() {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("pelanggan")

    lateinit var tvJudul: TextView
    lateinit var etNama: EditText
    lateinit var etAlamat: EditText
    lateinit var etNoHP: EditText
    lateinit var etCabang: EditText
    lateinit var btSimpan: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah_pelanggan)
        init()
        getData()

        btSimpan.setOnClickListener{
            if ( cekValidasi() ){
                simpan()
            }
        }
    }

    fun init() {
        tvJudul = findViewById(R.id.tvTambahPelanggan_Judul)
        etNama = findViewById(R.id.etTambahPelanggan_Nama)
        etAlamat = findViewById(R.id.etTambahPelanggan_Alamat)
        etNoHP = findViewById(R.id.etTambahPelanggan_HoHP)
        etCabang = findViewById(R.id.etTambahPelanggan_Cabang)
        btSimpan = findViewById(R.id.btTambahPelanggan_Simpan)
    }

    fun cekValidasi():Boolean {
        val nama = etNama.text.toString()
        val alamat = etAlamat.text.toString()
        val noHP = etNoHP.text.toString()
        val cabang = etCabang.text.toString()

        // Validasi data
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
                    val pelangganList = mutableListOf<ModelPelanggan>()
                    for (dataSnapshot in snapshot.children) {
                        val pelanggan = dataSnapshot.getValue(ModelPelanggan::class.java)
                        pelanggan?.let { pelangganList.add(it) }
                    }
                    Log.d("Data Pelanggan", "Data ditemukan: $pelangganList")
                } else {
                    Log.d("Data Pelanggan", "Tidak ada data pelanggan")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Database Error", error.message)
                Toast.makeText(this@TambahPelanggan, "Gagal memuat data", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun simpan() {
        val pelangganBaru = myRef.push()
        val pelangganId = pelangganBaru.key
        val currentTime = SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.getDefault()).format(Date())

        val data = ModelPelanggan(
            pelangganId.toString(),
            etNama.text.toString(),
            etAlamat.text.toString(),
            etNoHP.text.toString(),
            etCabang.text.toString(),
            currentTime
        )

        pelangganBaru.setValue(data)
            .addOnSuccessListener {
                Toast.makeText(this, getString(R.string.sukses_simpan_pelanggan), Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, getString(R.string.gagal_simpan_pelanggan), Toast.LENGTH_SHORT).show()
            }
    }
}
