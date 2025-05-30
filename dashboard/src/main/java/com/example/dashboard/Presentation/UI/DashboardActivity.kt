package com.example.dashboard.Presentation.UI

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import minicla03.coinquylifek.R

class DashboardActivity : AppCompatActivity()
{
    private lateinit var imgProfile: ImageView
    private lateinit var tvHouseName: TextView
    private lateinit var btnExpenses: ImageButton
    private lateinit var btnRank: ImageButton
    private lateinit var btnBoard: ImageButton
    private lateinit var btnShifts: ImageButton

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_layout)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        supportActionBar?.hide()

        imgProfile = findViewById(R.id.imgProfile)
        tvHouseName = findViewById(R.id.tvHouseName)
        btnExpenses = findViewById(R.id.btnExpenses)
        btnRank = findViewById(R.id.btnRank)
        btnBoard = findViewById(R.id.btnBoard)
        btnShifts = findViewById(R.id.btnShifts)

        tvHouseName.text = "${h.house_name}${h.id_house}"

        tvHouseName.setOnClickListener {
            Toast.makeText(this, "HouseActivity name not implemented yet", Toast.LENGTH_SHORT).show()
        }

        btnExpenses.setOnClickListener {
            Intent(this, ExpenseActivity::class.java).apply {

                startActivity(this)
            }
        }

        btnRank.setOnClickListener {
            Toast.makeText(this, "Rank not implemented yet", Toast.LENGTH_SHORT).show()
        }

        btnBoard.setOnClickListener {
            Intent(this, BoardActivity::class.java).apply {

                startActivity(this)
            }
        }

        btnShifts.setOnClickListener {
            Toast.makeText(this, "Shifts not implemented yet", Toast.LENGTH_SHORT).show()
        }

        imgProfile.setOnClickListener {
            Intent(this, ProfileActivity::class.java).apply {

                startActivity(this)
            }
        }
    }
}
