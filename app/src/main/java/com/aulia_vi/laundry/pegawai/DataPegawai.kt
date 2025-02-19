package com.aulia_vi.laundry.pegawai

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aulia_vi.laundry.R
import com.aulia_vi.laundry.adapter.DataPegawaiAdapter
import com.aulia_vi.laundry.modeldata.ModelPegawai
import com.aulia_vi.laundry.pegawai.TambahPegawai
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

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
        init()
        val layoutManager=LinearLayoutManager(this)
        layoutManager.reverseLayout=true
        layoutManager.stackFromEnd=true
        rvData_Pegawai.layoutManager=layoutManager
        rvData_Pegawai.setHasFixedSize(true)
        pegawaiList= arrayListOf<ModelPegawai>()
        getData()

        val fabTPegawai: FloatingActionButton = findViewById(R.id.fabTambahPegawai)
        fabTPegawai.setOnClickListener {
            val intent = Intent(this, TambahPegawai::class.java)
            startActivity(intent)
        }
    }
    fun init(){
        rvData_Pegawai=findViewById(R.id.rvDATA_PEGAWAI)
        fabDATA_PEGAWAI_Tambah=findViewById(R.id.fabTambahPegawai)
    }
    fun getData(){
        val query =myRef.orderByChild("idPegawai").limitToLast(100)
        query.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot){
                if (snapshot.exists()){
                    pegawaiList.clear()
                    for (dataSnapshot in snapshot.children){
                        val pegawai = dataSnapshot.getValue(ModelPegawai::class.java)
                        pegawaiList.add(pegawai!!)
                    }
                    val adapter = DataPegawaiAdapter(pegawaiList)
                    rvData_Pegawai.adapter =adapter
                    adapter.notifyDataSetChanged()
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@DataPegawai,error.message,Toast.LENGTH_SHORT)
            }
        })
    }
}