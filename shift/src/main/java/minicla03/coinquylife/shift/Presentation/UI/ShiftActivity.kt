package minicla03.coinquylife.shift.Presentation.UI

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import minicla03.coinquylife.shift.R
import java.util.Calendar
import java.util.Locale


class ShiftActivity : AppCompatActivity()
{
    private lateinit var etStartDate: EditText
    private lateinit var etEndDate: EditText
    private lateinit var etDescrizioneCompito: EditText
    private lateinit var etTaskDate: EditText

    private lateinit var ivStartDatePicker: ImageView
    private lateinit var ivEndDatePicker: ImageView
    private lateinit var ivTaskDatePicker: ImageView

    private lateinit var btnSalvaIndisponibilita: Button
    private lateinit var btnVisualizzaPianificazione: Button
    private lateinit var btnRicevute: Button
    private lateinit var btnInviate: Button
    private lateinit var btnEffettuaScambio: Button
    private lateinit var btnAggiungiCompito: Button

    private lateinit var spinnerCoinquilino: Spinner
    private lateinit var spinnerTipoCompito: Spinner

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shift_layout)
        intializeViews()

        btnSalvaIndisponibilita.setOnClickListener {
            // Logic for saving unavailability
            Toast.makeText(this, "Salva indisponibilità clicked", Toast.LENGTH_SHORT).show()
        }

        btnVisualizzaPianificazione.setOnClickListener {
            // Logic for viewing planning (e.g., navigate to another activity or show data)
            Toast.makeText(this, "Visualizza pianificazione clicked", Toast.LENGTH_SHORT).show()
        }

        btnRicevute.setOnClickListener {
            // Logic for showing received requests
            Toast.makeText(this, "Ricevute clicked", Toast.LENGTH_SHORT).show()
        }

        btnInviate.setOnClickListener {
            // Logic for showing sent requests
            Toast.makeText(this, "Inviate clicked", Toast.LENGTH_SHORT).show()
        }

        btnEffettuaScambio.setOnClickListener {
            showEffettuaScambioPopup()
        }

        btnAggiungiCompito.setOnClickListener {
            // Logic for adding a task
            Toast.makeText(this, "Aggiungi Compito clicked", Toast.LENGTH_SHORT).show()
        }
    }

    private fun intializeViews() {
        etStartDate = findViewById(R.id.etStartDate)
        etEndDate = findViewById(R.id.etEndDate)
        etDescrizioneCompito = findViewById(R.id.etDescrizioneCompito)
        etTaskDate = findViewById(R.id.etTaskDate)

        ivStartDatePicker = findViewById(R.id.ivStartDatePicker)
        ivEndDatePicker = findViewById(R.id.ivEndDatePicker)
        ivTaskDatePicker = findViewById(R.id.ivTaskDatePicker)

        btnSalvaIndisponibilita = findViewById(R.id.btnSalvaIndisponibilita)
        btnVisualizzaPianificazione = findViewById(R.id.btnVisualizzaPianificazione)
        btnRicevute = findViewById(R.id.btnRicevute)
        btnInviate = findViewById(R.id.btnInviate)
        btnEffettuaScambio = findViewById(R.id.btnEffettuaScambio)
        btnAggiungiCompito = findViewById(R.id.btnAggiungiCompito)

        spinnerCoinquilino = findViewById(R.id.spinnerCoinquilino)
        spinnerTipoCompito = findViewById(R.id.spinnerTipoCompito)

        setupDatePicker(etStartDate, ivStartDatePicker)
        setupDatePicker(etEndDate, ivEndDatePicker)
        setupDatePicker(etTaskDate, ivTaskDatePicker)
    }

    private fun setupDatePicker(editText: EditText, datePickerIcon: ImageView) {
        datePickerIcon.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this,
                { _, selectedYear, selectedMonth, selectedDay ->
                    // Format date as gg/mm/aaaa
                    val formattedDate = String.format(Locale.getDefault(), "%02d/%02d/%d", selectedDay, selectedMonth + 1, selectedYear)
                    editText.setText(formattedDate)
                }, year, month, day)
            datePickerDialog.show()
        }

        // Also make the EditText clickable for the date picker
        editText.setOnClickListener { datePickerIcon.performClick() }
    }

    private fun showEffettuaScambioPopup()
    {
        val popupView: View = getLayoutInflater().inflate(R.layout.poupop_swap, null)
        builder.setView(popupView)

        val spinnerTurnoDaScambiare = popupView.findViewById<Spinner?>(R.id.spinnerTurnoDaScambiare)
        val spinnerCoinquilinoScambio =
            popupView.findViewById<Spinner?>(R.id.spinnerCoinquilinoScambio)
        val etNuovoTurnoDate = popupView.findViewById<EditText?>(R.id.etNuovoTurnoDate)
        val ivNuovoTurnoDatePicker = popupView.findViewById<ImageView?>(R.id.ivNuovoTurnoDatePicker)

        setupDatePicker(etNuovoTurnoDate, ivNuovoTurnoDatePicker!!)

        builder.setPositiveButton(
            "Invia Richiesta",
            DialogInterface.OnClickListener { dialog: DialogInterface?, which: Int ->
                val selectedTurno = spinnerTurnoDaScambiare.getSelectedItem().toString()
                val selectedCoinquilino = spinnerCoinquilinoScambio.getSelectedItem().toString()
                val newTurnoDate = etNuovoTurnoDate.getText().toString()

                // Perform validation and send request
                Toast.makeText(
                    this@ShiftActivity,
                    "Richiesta inviata per " + selectedTurno + " con " + selectedCoinquilino + " il " + newTurnoDate,
                    Toast.LENGTH_LONG
                ).show()
                dialog!!.dismiss()
            })
        builder.setNegativeButton(
            "Annulla",
            DialogInterface.OnClickListener { dialog: DialogInterface?, which: Int ->
                dialog!!.dismiss()
            })

        val dialog = builder.create()
        dialog.show()
    }
}