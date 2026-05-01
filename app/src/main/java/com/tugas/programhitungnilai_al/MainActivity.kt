package com.tugas.programhitungnilai_al

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi Input
        val etPresensi = findViewById<EditText>(R.id.etPresensi)
        val etTugas = findViewById<EditText>(R.id.etTugas)
        val etResponsi = findViewById<EditText>(R.id.etResponsi)
        val etUTS = findViewById<EditText>(R.id.etUTS)
        val etUAS = findViewById<EditText>(R.id.etUAS)

        // Inisialisasi Output & Button
        val btnHasil = findViewById<Button>(R.id.btnCalculate)
        val btnReset = findViewById<FloatingActionButton>(R.id.btnReset) // Inisialisasi baru
        val cardHasil = findViewById<MaterialCardView>(R.id.cardHasil)
        val tvNilaiAngka = findViewById<TextView>(R.id.tvNilaiAngka)
        val tvGrade = findViewById<TextView>(R.id.tvGrade)
        val scrollView = findViewById<ScrollView>(R.id.main)

        btnHasil.setOnClickListener {
            val sPresensi = etPresensi.text.toString().trim()
            val sTugas = etTugas.text.toString().trim()
            val sResponsi = etResponsi.text.toString().trim()
            val sUTS = etUTS.text.toString().trim()
            val sUAS = etUAS.text.toString().trim()

            if (sPresensi.isEmpty() || sTugas.isEmpty() || sResponsi.isEmpty() || sUTS.isEmpty() || sUAS.isEmpty()) {
                Toast.makeText(this, "Mohon isi semua nilai", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val presensi = sPresensi.toInt()
            val tugas = sTugas.toInt()
            val responsi = sResponsi.toInt()
            val uts = sUTS.toInt()
            val uas = sUAS.toInt()

            if (presensi !in 0..100 || tugas !in 0..100 || responsi !in 0..100 || uts !in 0..100 || uas !in 0..100) {
                Toast.makeText(this, "Nilai harus antara 0-100", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val nilaiAkhir = hitungNilai(presensi, tugas, responsi, uts, uas)
            val grade = tentukanGrade(nilaiAkhir)

            tvNilaiAngka.text = nilaiAkhir.toString()
            tvGrade.text = grade

            // Munculkan Card dan Tombol Reset
            cardHasil.visibility = View.VISIBLE
            btnReset.visibility = View.VISIBLE

            cardHasil.post {
                scrollView.fullScroll(View.FOCUS_DOWN)
            }
        }

        // Logika Reset
        btnReset.setOnClickListener {
            // Bersihkan EditTexts
            etPresensi.text.clear()
            etTugas.text.clear()
            etResponsi.text.clear()
            etUTS.text.clear()
            etUAS.text.clear()

            // Sembunyikan Card dan FAB Reset
            cardHasil.visibility = View.GONE
            btnReset.visibility = View.GONE

            // Scroll kembali ke atas
            scrollView.fullScroll(View.FOCUS_UP)

            Toast.makeText(this, "Data telah direset", Toast.LENGTH_SHORT).show()
        }
    }

    private fun hitungNilai(p: Int, t: Int, r: Int, uts: Int, uas: Int): Int {
        val hasil = (p * 0.10) + (t * 0.15) + (r * 0.20) + (uts * 0.25) + (uas * 0.30)
        return hasil.toInt()
    }

    private fun tentukanGrade(nilai: Int): String {
        return when (nilai) {
            in 81..100 -> "A"
            in 61..80  -> "B"
            in 41..60  -> "C"
            in 21..40  -> "D"
            else       -> "E"
        }
    }
}