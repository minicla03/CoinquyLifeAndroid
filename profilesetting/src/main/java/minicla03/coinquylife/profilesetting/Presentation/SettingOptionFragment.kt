package minicla03.coinquylife.ProfileSetting.UI

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.CheckBoxPreference
import android.preference.Preference
import android.preference.Preference.OnPreferenceChangeListener
import androidx.preference.PreferenceManager
import minicla03.coinquylife.ProfileSetting.R
import androidx.preference.PreferenceFragmentCompat

class SettingOptionFragment : PreferenceFragmentCompat()
{
    private var sharedPreferences: SharedPreferences? = null
    private val sharedPrefFile = "minicla03.coinquylife.settings"

    public override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        sharedPreferences = getActivity().getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val checkbox: CheckBoxPreference? = findPreference(PREF_KEY)

        if (checkbox != null) {
            // Imposta il summary in base al valore salvato
            val currentValue = sharedPreferences!!.getBoolean(PREF_KEY, true)
            checkbox.setSummary(
                if (currentValue)
                    getString(R.string.option_on)
                else
                    getString(R.string.option_off)
            )

            // Ascolta i cambiamenti
            checkbox.setOnPreferenceChangeListener(OnPreferenceChangeListener { preference: Preference?, newValue: Any? ->
                val enabled = newValue as Boolean
                // Aggiorna summary
                checkbox.setSummary(
                    if (enabled)
                        getString(R.string.option_on)
                    else
                        getString(R.string.option_off)
                )

                // Salva anche un valore di supporto se vuoi
                sharedPreferences!!.edit()
                    .putBoolean(PREF_KEY, enabled)
                    .putString(
                        SUMMARY_KEY, if (enabled)
                            getString(R.string.option_on)
                        else
                            getString(R.string.option_off)
                    )
                    .apply()
                true
            })
        }
    }

    companion object {
        private const val PREF_KEY = "notifications_enabled"
        private const val SUMMARY_KEY = "summary"
    }
}