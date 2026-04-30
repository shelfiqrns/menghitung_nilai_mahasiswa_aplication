package com.tugas.programhitungnilai_al

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Inisialisasi View berdasarkan ID di XML
        val etPresensi = findViewById<EditText>(R.id.etPresensi)
        val etTugas = findViewById<EditText>(R.id.etTugas)
        val etResponsi = findViewById<EditText>(R.id.etResponsi)
        val etUTS = findViewById<EditText>(R.id.etUTS)
        val etUAS = findViewById<EditText>(R.id.etUAS)
        val btnHitung = findViewById<Button>(R.id.btnHitung)
        val btnReset = findViewById<Button>(R.id.btnReset)
        val tvHasil = findViewById<TextView>(R.id.tvHasil)

        btnHitung.setOnClickListener {
            // Mengambil input dan mengubah ke Double (jika kosong makan akan otomatis 0.0)
            val nPresensi = etPresensi.text.toString().toDoubleOrNull() ?: 0.0
            val nTugas = etTugas.text.toString().toDoubleOrNull() ?: 0.0
            val nResponsi = etResponsi.text.toString().toDoubleOrNull() ?: 0.0
            val nUTS = etUTS.text.toString().toDoubleOrNull() ?: 0.0
            val nUAS = etUAS.text.toString().toDoubleOrNull() ?: 0.0

            // Validasi sederhana agar nilai tidak lebih dari 100
            if (nPresensi > 100 || nTugas > 100 || nResponsi > 100 || nUTS > 100 || nUAS > 100) {
                Toast.makeText(this, "Nilai tidak boleh lebih dari 100!", Toast.LENGTH_SHORT).show()
            } else {
                //Function 1 (Hitung Nilai Akhir)
                val nilaiAkhir = hitungNilaiAngka(nPresensi, nTugas, nResponsi, nUTS, nUAS)

                //Function 2 (Konversi nilai ke Huruf)
                val grade = konversiNilaiHuruf(nilaiAkhir)

                // Menampilkan Hasil di TextView abu-abu
                tvHasil.text = "Nilai Akhir: ${String.format("%.2f", nilaiAkhir)}\nGrade: $grade"
            }
        }

        //Logika Tombol Reset
        btnReset.setOnClickListener {
            etPresensi.text.clear()
            etTugas.text.clear()
            etResponsi.text.clear()
            etUTS.text.clear()
            etUAS.text.clear()
            tvHasil.text = "Hasil akan muncul di sini"
        }
    }

    private fun hitungNilaiAngka(p: Double, t: Double, r: Double, uts: Double, uas: Double): Double {
        return (p * 0.10) + (t * 0.15) + (r * 0.20) + (uts * 0.25) + (uas * 0.30)
    }

    private fun konversiNilaiHuruf(nilai: Double): String {
        return when {
            nilai >= 81 -> "A"
            nilai >= 61 -> "B"
            nilai >= 41 -> "C"
            nilai >= 21 -> "D"
            else -> "E"
        }
    }
}