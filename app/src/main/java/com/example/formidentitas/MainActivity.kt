package com.example.formidentitas

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Deklarasi semua widget
    private lateinit var etNama: EditText
    private lateinit var rgKelamin: RadioGroup
    private lateinit var cbMembaca: CheckBox
    private lateinit var cbCoding: CheckBox
    private lateinit var cbOlahraga: CheckBox
    private lateinit var btnTampilkan: Button
    private lateinit var tvHasil: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Hubungkan widget dengan ID
        etNama      = findViewById(R.id.etNama)
        rgKelamin   = findViewById(R.id.rgKelamin)
        cbMembaca   = findViewById(R.id.cbMembaca)
        cbCoding    = findViewById(R.id.cbCoding)
        cbOlahraga  = findViewById(R.id.cbOlahraga)
        btnTampilkan= findViewById(R.id.btnTampilkan)
        tvHasil     = findViewById(R.id.tvHasil)

        btnTampilkan.setOnClickListener {
            tampilkanData()
        }
    }

    private fun tampilkanData() {
        val nama = etNama.text.toString().trim()

        // --- VALIDASI ---
        if (nama.isEmpty()) {
            etNama.error = "Nama tidak boleh kosong"
            Toast.makeText(this, "Nama tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            return
        }

        if (rgKelamin.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Jenis kelamin harus dipilih!", Toast.LENGTH_SHORT).show()
            return
        }

        // Ambil jenis kelamin
        val radioTerpilih = findViewById<RadioButton>(rgKelamin.checkedRadioButtonId)
        val kelamin = radioTerpilih.text.toString()

        // Ambil hobi yang dicentang
        val hobiList = mutableListOf<String>()
        if (cbMembaca.isChecked)  hobiList.add("Membaca")
        if (cbCoding.isChecked)   hobiList.add("Coding")
        if (cbOlahraga.isChecked) hobiList.add("Olahraga")

        val hobi = if (hobiList.isEmpty()) "Tidak ada" else hobiList.joinToString(", ")

        // Tampilkan hasil di TextView
        val hasil = """
            Nama    : $nama
            Kelamin : $kelamin
            Hobi    : $hobi
        """.trimIndent()

        tvHasil.text = hasil
    }
}