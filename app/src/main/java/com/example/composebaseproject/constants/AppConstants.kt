package com.example.composebaseproject.constants

import androidx.annotation.IntDef
import androidx.annotation.LongDef
import androidx.annotation.StringDef


object AppConstants {

    @StringDef(ApiConfiguration.BASE_URL)
    annotation class ApiConfiguration {
        companion object {
            const val BASE_URL = "https://jsonplaceholder.typicode.com/"
        }
    }

    @LongDef(
        SystemProperties.VIBRATION_INTERVAL
    )
    annotation class SystemProperties {
        companion object {
            const val VIBRATION_INTERVAL = 20L

        }
    }

    @StringDef(DbConfiguration.DB_NAME)
    annotation class DbConfiguration {
        companion object {
            const val DB_NAME = "BaseProject"
        }
    }


    @StringDef(
        DataStore.DATA_STORE_NAME,
        DataStore.LOCALIZATION_KEY_NAME,
        DataStore.USER_NAME_KEY,
        DataStore.FIRST_TIME_LOGIN,
        DataStore.SHOW_INTRO_SCREEN,
        DataStore.API_TOKEN,
        DataStore.SHOW_INTRO_SCREEN_ENCRYPTED,
        DataStore.PASSCODE,
        DataStore.BIOMETRIC_LOGIN,
        DataStore.FIRST_TIME_LOGIN,
        DataStore.AVATAR,
        DataStore.USER_AVATAR,
        DataStore.USER_NAME,
        DataStore.USER_OBJECT,
        DataStore.APP_THEME,
    )
    annotation class DataStore {
        companion object {
            const val DATA_STORE_NAME = "BMWCarServices"
            const val LOCALIZATION_KEY_NAME = "app_language"
            const val USER_NAME_KEY = "user_name_key"
            const val FIRST_TIME_LOGIN = "isFirstTimeLoggingInUsingThisDevice"
            const val SHOW_INTRO_SCREEN = "isShowIntroScreen"
            const val API_TOKEN = "apiToken"
            const val USER_AVATAR = "userAvatar"
            const val USER_NAME = "userName"
            const val PASSCODE = "passcode"
            const val USER_OBJECT = "userObject"
            const val BIOMETRIC_LOGIN = "biometricLogin"
            const val AVATAR = "Avatar"
            const val SHOW_INTRO_SCREEN_ENCRYPTED = "isShowIntroScreenEncrypted"
            const val APP_THEME = "AppTheme"
        }
    }

    @IntDef(
        WithdrawAddressFieldViewType.TEXT, WithdrawAddressFieldViewType.DROP_DOWN
    )
    annotation class WithdrawAddressFieldViewType {
        companion object {
            const val TEXT = 2
            const val DROP_DOWN = 1
        }
    }


}