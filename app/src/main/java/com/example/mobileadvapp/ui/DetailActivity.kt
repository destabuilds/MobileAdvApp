package com.example.mobileadvapp.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mobileadvapp.R
import com.example.mobileadvapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_NAMA = "extra_nama"
        const val EXTRA_NIM = "extra_nim"
        const val EXTRA_PRODI = "extra_prodi"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)

        // Menampilkan data dari Explicit Intent
        findViewById<TextView>(R.id.tvNama).text = intent.getStringExtra(EXTRA_NAMA)
        findViewById<TextView>(R.id.tvNim).text = intent.getStringExtra(EXTRA_NIM)
        findViewById<TextView>(R.id.tvProdi).text = intent.getStringExtra(EXTRA_PRODI)

        // 1. Dial -> tidak butuh permission CALL_PHONE
        findViewById<Button>(R.id.btnCall).setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:02744984000"))
            launch(intent, "Aplikasi telepon tidak ditemukan")
        }

        // 2. Google Maps -> lokasi AMIKOM Yogyakarta
        findViewById<Button>(R.id.btnMaps).setOnClickListener {
            val uri = Uri.parse("geo:-7.7599,110.4083?q=-7.7599,110.4083(Universitas AMIKOM Yogyakarta)")
            val intent = Intent(Intent.ACTION_VIEW, uri).apply {
                setPackage("com.google.android.apps.maps")
            }
            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                // Fallback: tanpa paksa Google Maps, biar app peta lain/browser yang menangani
                launch(Intent(Intent.ACTION_VIEW, uri), "Aplikasi peta tidak ditemukan")
            }
        }

        // 3. Browser
        findViewById<Button>(R.id.btnWeb).setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://amikom.ac.id"))
            launch(intent, "Browser tidak ditemukan")
        }


        val name = intent.getStringExtra("EXTRA_NAME")
        val score = intent.getIntExtra("EXTRA_SCORE", 0)
        binding.tvDetailInfo.text = "Nama: $name\nSkor: $score"

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun launch(intent: Intent, errorMsg: String) {
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show()
        }
    }
}