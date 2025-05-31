package minicla03.coinquylife.houseselection.Presentation.UI

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.dashboard.Presentation.UI.DashboardActivity
import minicla03.coinquylife.houseselection.Data.Response.HouseStatus
import minicla03.coinquylife.houseselection.Presentation.ViewModel.SelectHouseViewModel

import minicla03.coinquylife.houseselection.R

class NewCoinquyHouseFragment : Fragment() {

    private lateinit var houseName: EditText
    private lateinit var address: EditText
    private val selectHouseViewModel: SelectHouseViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_newhouse_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textViewID: TextView = view.findViewById(R.id.textViewID)
        val btnProceed: Button = view.findViewById(R.id.btnProceed)
        val btnCopyID: ImageButton = view.findViewById(R.id.btnCopyID)
        houseName = view.findViewById(R.id.etHouseName)
        address = view.findViewById(R.id.etHouseAddress)

        btnProceed.setOnClickListener {
            val nameHouse = houseName.text.toString().trim()
            val address = address.text.toString().trim()
            selectHouseViewModel.createHouse(nameHouse, address)
        }

        btnCopyID.setOnClickListener {
            val textToCopy = textViewID.text.toString()
            val clipboard =
                requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("House ID", textToCopy)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(requireContext(), "ID copiato negli appunti", Toast.LENGTH_SHORT).show()
        }

        selectHouseViewModel.houseID.observe(viewLifecycleOwner) { houseCode ->
            if (houseCode!=null) {
                textViewID.text = houseCode
            }
        }

        selectHouseViewModel.houseCreationResult.observe(viewLifecycleOwner) { result ->
            when (result)
            {
                is HouseStatus.LINKED_SUCCES -> {
                    val intent = Intent(requireActivity(), DashboardActivity::class.java).apply {
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    }
                    startActivity(intent)
                }
                is HouseStatus.INVALID_INPUT -> {
                    Toast.makeText(requireContext(), "Insert a valid input", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(requireContext(), "Error creating house", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
