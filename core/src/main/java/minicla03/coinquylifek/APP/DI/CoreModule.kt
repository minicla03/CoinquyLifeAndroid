package minicla03.coinquylifek.APP.DI

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import minicla03.coinquylifek.APP.network.ApiService
import javax.inject.Singleton
import minicla03.coinquylifek.APP.security.TokenManager

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    @Singleton
    fun provideTokenManager(@ApplicationContext context: Context): TokenManager {
        return TokenManager(context)
    }

    @Provides
    @Singleton
    fun provideTokenProvider(tokenManager: TokenManager): suspend () -> String? = {
        tokenManager.getToken()
    }

    @Provides
    @Singleton
    fun provideApiService(tokenProvider: suspend () -> String?): ApiService {
        return ApiService(tokenProvider)
    }

    @Provides
    @Singleton
    fun provideAppPreferences(prefs: SharedPreferences): AppPreferences {
        return AppPreferences(prefs)
    }
}
