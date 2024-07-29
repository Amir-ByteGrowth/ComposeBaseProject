package com.example.composebaseproject.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.composebaseproject.constants.AppConstants

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreProvider(val context: Context) {

    //Create some keys
    companion object {
        //Create the dataStore
        private val Context.userPreferencesDataStore: DataStore<Preferences> by preferencesDataStore(
            name = AppConstants.DataStore.DATA_STORE_NAME
        )


        val IS_LOCALIZATION_KEY =
            booleanPreferencesKey(AppConstants.DataStore.LOCALIZATION_KEY_NAME)
        val USER_NAME_KEY = stringPreferencesKey(AppConstants.DataStore.USER_NAME_KEY)
        val SHOW_INTRO_SCREEN = booleanPreferencesKey(AppConstants.DataStore.SHOW_INTRO_SCREEN)
        val API_TOKEN = stringPreferencesKey(AppConstants.DataStore.API_TOKEN)
        val LANGUAGE = stringPreferencesKey(AppConstants.DataStore.LOCALIZATION_KEY_NAME)
        val PASSCODE = stringPreferencesKey(AppConstants.DataStore.PASSCODE)
        val USER_OBJECT = stringPreferencesKey(AppConstants.DataStore.USER_OBJECT)
        val BIOMETRIC_LOGIN = booleanPreferencesKey(AppConstants.DataStore.BIOMETRIC_LOGIN)
        val FIRST_TIME_LOGIN = booleanPreferencesKey(AppConstants.DataStore.FIRST_TIME_LOGIN)

        val APP_THEME = intPreferencesKey(AppConstants.DataStore.APP_THEME)

    }


    // get intro screen variable
    val showIntroScreenFlow: Flow<Boolean> = context.userPreferencesDataStore.data.map {
        it[SHOW_INTRO_SCREEN] ?: false
    }

    // store intro screen variable
    suspend fun saveIntroScreen(isShowIntroScreen: Boolean) {
        context.userPreferencesDataStore.edit {
            it[SHOW_INTRO_SCREEN] = isShowIntroScreen
        }
    }


    //Store data
    suspend fun storeData(isLocalizationKey: Boolean, name: String) {
        context.userPreferencesDataStore.edit {
            it[IS_LOCALIZATION_KEY] = isLocalizationKey
            it[USER_NAME_KEY] = name
        }
    }

    //Create an Localization flow
    val localizationFlow: Flow<Boolean> = context.userPreferencesDataStore.data.map {
        it[IS_LOCALIZATION_KEY] ?: false
    }

    //Create a name flow
    val userNameFlow: Flow<String> = context.userPreferencesDataStore.data.map {
        it[USER_NAME_KEY] ?: ""
    }

    //get first time login

    val getFirstTimeLoginFlag: Flow<Boolean> = context.userPreferencesDataStore.data.map {
        it[FIRST_TIME_LOGIN] ?: false
    }

    //store first time login
    suspend fun saveFirstTimeLoginFlag(isFirstTimeLogin: Boolean) {
        context.userPreferencesDataStore.edit {
            it[FIRST_TIME_LOGIN] = isFirstTimeLogin
        }
    }


    /**--------------------------------------------------------------------- user token ----------------------------------------------------**/
//get token
    val getToken: Flow<String> = context.userPreferencesDataStore.data.map {
        it[API_TOKEN] ?: ""
    }

    //save token
    suspend fun saveToken(apiToken: String) {
        context.userPreferencesDataStore.edit {
            it[API_TOKEN] = apiToken
        }
    }

    /**---------------- save language ------------**/

    //get language
    val getLanguage: Flow<String> = context.userPreferencesDataStore.data.map {
        it[LANGUAGE] ?: "en"
    }

    //save language
    suspend fun saveLanguage(lang: String) {
        context.userPreferencesDataStore.edit {
            it[LANGUAGE] = lang
        }
    }

    /**--------------------------------------------------------------------- pas creds ----------------------------------------------------**/
    //store passcode
    suspend fun savePasscode(passcode: String) {


        context.userPreferencesDataStore.edit {
            it[PASSCODE] = passcode
        }
    }

    //get passcode
    val getPasscode: Flow<String> = context.userPreferencesDataStore.data.map {
        it[PASSCODE] ?: ""
    }

    /**--------------------------------------------------------------------- user obj ----------------------------------------------------**/

    //get  userObject flow
    val userObjFlow: Flow<String?> = context.userPreferencesDataStore.data.map {
        it[USER_OBJECT]
    }

//    //store user object
//    suspend fun saveUserObj(userObject: LoginData) {
//        val gson = Gson()
//        val json = gson.toJson(userObject)
//        val data = encryptionDecryption.encryptString(json)
//        val encryptedDaya = getEncryptedStrIv(data)
//        context.userPreferencesDataStore.edit {
//            it[USER_OBJECT] = encryptedDaya
//        }
//    }

    //clear user object
    suspend fun clearUserObj() {
        context.userPreferencesDataStore.edit {
            it[USER_OBJECT] = ""
        }
    }


    /**--------------------------------------------------------------------- biometric check ----------------------------------------------------**/
    // store is finger print enabled or not
    suspend fun storeBiometricCheck(isBiometricLoginEnabled: Boolean) {
        context.userPreferencesDataStore.edit {
            it[BIOMETRIC_LOGIN] = isBiometricLoginEnabled
        }
    }

    // get is finger print enabled or not
    val getBiometricCheck: Flow<Boolean> = context.userPreferencesDataStore.data.map {
        it[BIOMETRIC_LOGIN] ?: false
    }


    /**--------------------------------------------------------------------- get/set App Theme ----------------------------------------------------**/
    suspend fun storeAppTheme(appTheme: Int) {
        context.userPreferencesDataStore.edit {
            it[APP_THEME] = appTheme
        }
    }

    // get is finger print enabled or not
    val getAppTheme: Flow<Int> = context.userPreferencesDataStore.data.map {
        it[APP_THEME] ?: 0
    }

}