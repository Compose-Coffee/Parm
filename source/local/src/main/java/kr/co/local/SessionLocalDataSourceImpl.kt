package kr.co.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kr.co.data.source.local.SessionLocalDataSource
import javax.inject.Inject

internal class SessionLocalDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : SessionLocalDataSource {
    override suspend fun saveUserId(userId: String) {
        dataStore.edit { pref ->
            pref[USER_ID] = userId
        }
    }

    override fun fetchUserId(): Flow<String?> =
        dataStore.data.map { pref ->
            pref[USER_ID]
    }

    override suspend fun updateAccessToken(accessToken: String) {
        dataStore.edit { pref ->
            pref[ACCESS_TOKEN] = accessToken
        }
    }

    override suspend fun updateRefreshToken(refreshToken: String) {
        dataStore.edit { pref ->
            pref[REFRESH_TOKEN] = refreshToken
        }
    }

    override suspend fun getAccessToken(): String? =
        dataStore.data.map { pref ->
            pref[ACCESS_TOKEN]
        }.firstOrNull()

    override suspend fun getRefreshToken(): String? =
        dataStore.data.map { pref ->
            pref[REFRESH_TOKEN]
        }.firstOrNull()

    override suspend fun removeAll() {
        dataStore.edit { pref ->
            pref.clear()
        }
    }

    companion object {
        private val USER_ID = stringPreferencesKey("user_id")
        private val ACCESS_TOKEN = stringPreferencesKey("access_token")
        private val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
    }
}