package com.example.dashboard.Presentation.UI

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.dashboard.Presentation.ViewModel.DashboardViewModel
import com.example.dashboard.R
import minicla03.coinquylife.expense.Presentation.UI.ExpenseActivity
import minicla03.coinquylife.profilesetting.Presentation.ProfileActivity
import minicla03.coinquylife.shift.Presentation.UI.ShiftActivity

class DashboardActivity : AppCompatActivity()
{
    private lateinit var imgProfile: ImageView
    private lateinit var tvHouseName: TextView
    private lateinit var btnExpenses: ImageButton
    private lateinit var btnRank: ImageButton
    private lateinit var btnBoard: ImageButton
    private lateinit var btnShifts: ImageButton

    private val dashboardViewModel: DashboardViewModel by viewModels()

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

        val intent = getIntent()
        var houseId = intent.getStringExtra("coinquyHouseId")

        if(houseId != null)
        {
            dashboardViewModel.setHouseId(houseId)
        }

        tvHouseName.text = "$houseId"

        tvHouseName.setOnClickListener {
            Toast.makeText(this, "HouseActivity name not implemented yet", Toast.LENGTH_SHORT).show()
        }

        btnExpenses.setOnClickListener {
            Intent(this, ExpenseActivity::class.java).apply {
                putExtra("houseId", houseId)
                startActivity(this)
            }
        }

        btnRank.setOnClickListener {
            Toast.makeText(this, "Rank not implemented yet", Toast.LENGTH_SHORT).show()
        }

        btnBoard.setOnClickListener {
            Toast.makeText(this, "Bord not implemented yet", Toast.LENGTH_SHORT).show()

        }

        btnShifts.setOnClickListener {
            Intent(this, ShiftActivity::class.java).apply {
                putExtra("houseId", houseId)
                startActivity(this)
            }
        }

        imgProfile.setOnClickListener {
            Intent(this, ProfileActivity::class.java).apply {
                putExtra("houseId", houseId)
                startActivity(this)
            }
        }
    }
}
