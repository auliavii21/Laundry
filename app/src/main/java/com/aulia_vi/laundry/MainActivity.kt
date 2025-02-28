package com.aulia_vi.laundry

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aulia_vi.laundry.pegawai.DataPegawai
import com.aulia_vi.laundry.pelanggan.DataPelanggan
import com.aulia_vi.laundry.pelanggan.TambahPelanggan
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    lateinit var tanggal: TextView
    lateinit var hallo: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        tanggal = findViewById(R.id.tanggal)
        hallo = findViewById(R.id.hallo)

        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        val currentDate = dateFormat.format(Date())
        tanggal.text = currentDate

        val calendar = Calendar.getInstance()
        val hourOfDay = calendar[Calendar.HOUR_OF_DAY]
        var greeting = ""
        greeting = if (hourOfDay >= 5 && hourOfDay < 10) {
            "Selamat Pagi, Aulia!"
        } else if (hourOfDay >= 10 && hourOfDay < 15) {
            "Selamat Siang, Aulia!"
        } else if (hourOfDay >= 15 && hourOfDay < 18) {
            "Selamat Sore, Aulia!"
        } else {
            "Selamat Malam, Aulia!"
        }
        hallo.text = greeting

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val pelanggan: CardView = findViewById(R.id.pelanggan)
        pelanggan.setOnClickListener {
            val intent = Intent(this, DataPelanggan::class.java)
            startActivity(intent)
        }
        val pegawai: CardView = findViewById(R.id.pegawai)
        pegawai.setOnClickListener {
            val intent = Intent(this, DataPegawai::class.java)
            startActivity(intent)
        }
    }
}