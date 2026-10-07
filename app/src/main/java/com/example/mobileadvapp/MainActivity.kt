package com.example.mobileadvapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mobileadvapp.databinding.ActivityMainBinding
import com.example.mobileadvapp.ui.DetailActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var counter = 0
    private val TAG = "LifecycleApp"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "onCreate Dipanggil")

        val etNama = findViewById<EditText>(R.id.etNama)
        val etNim = findViewById<EditText>(R.id.etNim)
        val etProdi = findViewById<EditText>(R.id.etProdi)
        val btnKirim = findViewById<Button>(R.id.btnKirim)

        btnKirim.setOnClickListener {
            val nama = etNama.text.toString().trim()
            val nim = etNim.text.toString().trim()
            val prodi = etProdi.text.toString().trim()

            if (nama.isEmpty() || nim.isEmpty() || prodi.isEmpty()) {
                Toast.makeText(this, "Semua kolom wajib diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra(DetailActivity.EXTRA_NAMA, nama)
                putExtra(DetailActivity.EXTRA_NIM, nim)
                putExtra(DetailActivity.EXTRA_PRODI, prodi)
            }
            startActivity(intent)

        // Memulihkan data counter jika terjadi rotasi layar
        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt("KEY_COUNTER", 0)
        }

        binding.tvCounter.text = counter.toString()

        binding.btnIncrement.setOnClickListener {
            counter++
            binding.tvCounter.text = counter.toString()
        }

        binding.btnOpenDetail.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("EXTRA_NAME", "Mahasiswa AMIKOM")
                putExtra("EXTRA_SCORE", counter)
            }
            startActivity(intent)
        }

        // Panggilan Telepon (Dialer)
        binding.btnDialPhone.setOnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:0274884201")
            }
            startActivity(dialIntent)
        }

        // Membuka Google Maps
        binding.btnOpenMap.setOnClickListener {
            val gmmIntentUri =
                Uri.parse("geo:-7.7599,110.4083?q=Universitas+AMIKOM+Yogyakarta")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            startActivity(mapIntent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart Dipanggil")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume Dipanggil")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause Dipanggil")
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop Dipanggil")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy Dipanggil")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("KEY_COUNTER", counter)
        Log.d(TAG, "onSaveInstanceState Dipanggil - Counter Disimpan: $counter")
    }
}