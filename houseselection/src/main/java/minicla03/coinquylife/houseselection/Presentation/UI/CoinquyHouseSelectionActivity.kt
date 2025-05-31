package minicla03.coinquylife.houseselection.Presentation.UI

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import minicla03.coinquylife.houseselection.R
import minicla03.coinquylife.houseselection.Presentation.ViewModel.SelectHouseViewModel

@AndroidEntryPoint
class CoinquyHouseSelectionActivity : AppCompatActivity()
{
    private val selectHouseViewModel: SelectHouseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.house_selection_layout)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        supportActionBar?.hide()

        val btnCreateGroup: View = findViewById(R.id.btnCreateGroup)
        val btnJoinGroup: View = findViewById(R.id.btnJoinGroup)

        btnCreateGroup.setOnClickListener() {
            navigateToFragment(NewCoinquyHouseFragment())
        }

        btnJoinGroup.setOnClickListener() {
            navigateToFragment(JoinCoinquyHouseFragment())
        }

        if (savedInstanceState == null)
        {
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.house_selection_fragment_container, NewCoinquyHouseFragment())
                .commit()
        }
    }

    private fun navigateToFragment(fragment: Fragment)
    {
        val tag = fragment::class.java.simpleName
        val existingFragment = supportFragmentManager.findFragmentByTag(tag)

        supportFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.house_selection_fragment_container, existingFragment ?: fragment, tag)
            .commit()
    }
}
