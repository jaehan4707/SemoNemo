package com.semonemo.data.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthDataSource
    @Inject
    constructor(
        private val dataStore: DataStore<Preferences>,
    ) {
        suspend fun saveWalletAddress(address: String) {
            dataStore.edit { prefs ->
                prefs[WALLET_ADDRESS_KEY] = address
            }
        }

        suspend fun getWalletAddress(): String? =
            dataStore.data
                .map { prefs ->
                    prefs[WALLET_ADDRESS_KEY]
                }.first()

        suspend fun savePassword(password: String) {
            dataStore.edit { prefs ->
                prefs[PASSWORD_KEY] = password
            }
        }

        suspend fun getPassword(): String? =
            dataStore.data
                .map { prefs ->
                    prefs[PASSWORD_KEY]
                }.first()

        suspend fun saveNickname(nickname: String) {
            dataStore.edit { prefs ->
                prefs[NICKNAME_KEY] = nickname
            }
        }

        suspend fun getNickname(): String? =
            dataStore.data
                .map { prefs ->
                    prefs[NICKNAME_KEY]
                }.first()

        suspend fun saveProfileImage(profileImage: String) {
            dataStore.edit { prefs ->
                prefs[PROFILE_IMAGE_KEY] = profileImage
            }
        }

        suspend fun getProfileImage(): String? =
            dataStore.data
                .map { prefs ->
                    prefs[PROFILE_IMAGE_KEY]
                }.first()

        suspend fun deleteAuthData() {
            dataStore.edit { prefs ->
                prefs.remove(WALLET_ADDRESS_KEY)
                prefs.remove(PASSWORD_KEY)
                prefs.remove(USER_ID_KEY)
                prefs.remove(PROFILE_IMAGE_KEY)
            }
        }

        suspend fun saveUserId(userId: Long) {
            dataStore.edit { prefs ->
                prefs[USER_ID_KEY] = userId.toString()
            }
        }

        suspend fun getUserId(): String? =
            dataStore.data
                .map { prefs ->
                    prefs[USER_ID_KEY]
                }.first()

        companion object {
            val WALLET_ADDRESS_KEY = stringPreferencesKey("WALLET_ADDRESS_KEY")
            val PASSWORD_KEY = stringPreferencesKey("PASSWORD_KEY")
            val NICKNAME_KEY = stringPreferencesKey("NICKNAME_KEY")
            val USER_ID_KEY = stringPreferencesKey("USER_ID_KEY")
            val PROFILE_IMAGE_KEY = stringPreferencesKey("PROFILE_IMAGE_KEY")
        }
    }
