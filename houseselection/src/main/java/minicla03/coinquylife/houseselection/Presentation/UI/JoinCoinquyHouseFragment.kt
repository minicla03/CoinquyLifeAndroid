package minicla03.coinquylife.houseselection.Presentation.UI

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.dashboard.Presentation.UI.DashboardActivity
import minicla03.coinquylife.houseselection.Data.Response.HouseStatus
import minicla03.coinquylife.houseselection.Presentation.ViewModel.SelectHouseViewModel
import minicla03.coinquylife.houseselection.R


class JoinCoinquyHouseFragment : Fragment() {

    private lateinit var etHouseID: EditText
    private lateinit var btnConfirm: Button
    private val selectHouseViewModel: SelectHouseViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_joinhouse_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etHouseID = view.findViewById(R.id.etHouseID)
        btnConfirm = view.findViewById(R.id.btnConfirmHouseID)

        btnConfirm.setOnClickListener {
            val houseId = etHouseID.text.toString().trim()

            if (houseId.isEmpty()) {
                Toast.makeText(requireContext(), "Insert a valid id", Toast.LENGTH_SHORT).show()
            } else {
                selectHouseViewModel.joinHouse(houseId)
            }
        }

        selectHouseViewModel.joinHouseResult.observe(viewLifecycleOwner) { result ->
            when (result)
            {
                is HouseStatus.LINKED_SUCCES -> {
                    val intent = Intent(requireActivity(), DashboardActivity::class.java).apply {
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    }
                    startActivity(intent)
                }
                is HouseStatus.HOUSE_NOT_FOUND -> {
                    Toast.makeText(requireContext(), "House not found", Toast.LENGTH_SHORT).show()
                }
                is HouseStatus.USER_NOT_FOUND -> {
                    Toast.makeText(requireContext(), "User is not in a house", Toast.LENGTH_SHORT).show()
                }
                is HouseStatus.USER_ALREADY_LINKED -> {
                    Toast.makeText(requireContext(), "User already linked to a house", Toast.LENGTH_SHORT).show()
                }
                is HouseStatus.HOUSE_NOT_CREATED -> {
                    Toast.makeText(requireContext(), "Error joining house", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(requireContext(), "Error joining house", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
