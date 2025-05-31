package minicla03.coinquylifek.APP.security

import android.content.Context
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class TokenManager(context: Context)
{
    companion object {
        private const val PREFS_NAME = "secure_prefs"
        private const val TOKEN_KEY = "auth_token"
    }

    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    private val sharedPrefs = EncryptedSharedPreferences.create(
        PREFS_NAME,
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveToken(token: String)
    {
        sharedPrefs.edit() { putString(TOKEN_KEY, token) }
    }

    fun getToken(): String?
    {
        return sharedPrefs.getString(TOKEN_KEY, null)
    }

    fun clearToken()
    {
        sharedPrefs.edit() { remove(TOKEN_KEY) }
    }
}