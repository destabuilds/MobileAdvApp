package com.example.mobileadvapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mobileadvapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Tangkap Data dari Explicit Intent
        val nama = intent.getStringExtra("EXTRA_NAMA")
        val nim = intent.getStringExtra("EXTRA_NIM")
        val prodi = intent.getStringExtra("EXTRA_PRODI")
        val score = intent.getIntExtra("EXTRA_SCORE", 0)

        binding.tvDetailInfo.text = "Nama: $nama\nNIM: $nim\nProgram Studi: $prodi\nSkor: $score"

        // 2. Implicit Intent - Call Center (Dialer)
        binding.btnCallCenter.setOnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:0274884201")
            }
            startActivity(dialIntent)
        }

        // 3. Implicit Intent - Lokasi Kampus (Google Maps)
        binding.btnLokasiKampus.setOnClickListener {
            val gmmIntentUri = Uri.parse("geo:-7.7599,110.4083?q=Universitas+AMIKOM+Yogyakarta")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            startActivity(mapIntent)
        }

        // 4. Implicit Intent - Website Kampus (Browser)
        binding.btnWebsiteKampus.setOnClickListener {
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://amikom.ac.id"))
            startActivity(webIntent)
        }
    }
}