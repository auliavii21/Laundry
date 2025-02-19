package com.aulia_vi.laundry.pelanggan

import DataPelangganAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aulia_vi.laundry.R
import com.aulia_vi.laundry.modeldata.ModelPelanggan
import com.google.android.material.animation.AnimatableView.Listener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DataPelanggan : AppCompatActivity() {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("pelanggan")
    lateinit var rvData_Pelanggan: RecyclerView
    lateinit var  fabDATA_PENGGUNA_Tambah: FloatingActionButton
    lateinit var pelangganList: ArrayList<ModelPelanggan>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_data_pelanggan)
        init()
        val layoutManager = LinearLayoutManager(this)
        layoutManager.reverseLayout =true
        layoutManager.stackFromEnd =true
        rvData_Pelanggan.layoutManager = layoutManager
        rvData_Pelanggan.setHasFixedSize(true)
        pelangganList = arrayListOf<ModelPelanggan>()
        getData()

        val fabTPelanggan: FloatingActionButton = findViewById(R.id.fabTambahPengguna)
        fabTPelanggan.setOnClickListener {
            val intent = Intent(this, TambahPelanggan::class.java)
            startActivity(intent)
        }

    }
    fun init(){
        rvData_Pelanggan = findViewById(R.id.rvDATA_PELANGGAN)
        fabDATA_PENGGUNA_Tambah = findViewById(R.id.fabTambahPengguna)
    }
    fun getData(){
        val query = myRef.orderByChild("idPelanggan").limitToLast(100)
        query.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot){
                if (snapshot.exists()){
                    pelangganList.clear()
                    for (dataSnapshot in snapshot.children){
                        val pegawai = dataSnapshot.getValue(ModelPelanggan::class.java)
                        pelangganList.add(pegawai!!)
                    }
                    val adapter = DataPelangganAdapter(pelangganList)
                    rvData_Pelanggan.adapter =adapter
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@DataPelanggan, error.message, Toast.LENGTH_SHORT)
            }
        })
    }
}