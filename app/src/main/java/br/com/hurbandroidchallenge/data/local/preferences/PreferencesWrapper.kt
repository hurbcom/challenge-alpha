package br.com.hurbandroidchallenge.data.local.preferences

import android.content.Context
import android.content.SharedPreferences

class PreferencesWrapper private constructor(context: Context) {

    init {
        sharedPreferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    fun clearPreferences() {
        clearCharacters()
        clearFilms()
        clearPlanets()
    }

    fun clearCharacters() {
        putBoolean(PreferencesKeys.IS_CHARACTERS_UP_TO_DATE, false)
    }

    fun filmsIsUpToDate() {
        putBoolean(PreferencesKeys.IS_FILMS_UP_TO_DATE, true)
    }

    fun clearFilms() {
        putBoolean(PreferencesKeys.IS_FILMS_UP_TO_DATE, false)
    }

    fun planetsIsUpToDate() {
        putBoolean(PreferencesKeys.IS_PLANETS_UP_TO_DATE, true)
    }

    fun clearPlanets() {
        putBoolean(PreferencesKeys.IS_PLANETS_UP_TO_DATE, false)
    }

    fun charactersIsUpToDate() {
        putBoolean(PreferencesKeys.IS_CHARACTERS_UP_TO_DATE, true)
    }

    fun isCharactersUpToDate(): Boolean {
        return getBoolean(PreferencesKeys.IS_CHARACTERS_UP_TO_DATE, false)
    }

    fun isFilmsUpToDate(): Boolean {
        return getBoolean(PreferencesKeys.IS_FILMS_UP_TO_DATE, false)
    }

    fun isPlanetsUpToDate(): Boolean {
        return getBoolean(PreferencesKeys.IS_PLANETS_UP_TO_DATE, false)
    }

    fun putString(key: String, value: String) {
        save(key, value)
    }

    fun putInt(key: String, value: Int) {
        save(key, value)
    }

    fun putBoolean(key: String, value: Boolean) {
        save(key, value)
    }

    fun putLong(key: String, value: Long) {
        save(key, value)
    }

    fun getString(key: String, defaultValue: String): String {
        return sharedPreferences?.getString(key, defaultValue) ?: defaultValue
    }

    fun getBoolean(key: String?, defaultValue: Boolean): Boolean {
        return sharedPreferences?.getBoolean(key, defaultValue) ?: defaultValue
    }

    fun getInt(key: String?, defaultValue: Int): Int {
        return sharedPreferences?.getInt(key, defaultValue) ?: defaultValue
    }

    fun getLong(key: String?, defaultValue: Long): Long {
        return sharedPreferences?.getLong(key, defaultValue) ?: defaultValue
    }

    private fun save(key: String, value: String) {
        val editor: SharedPreferences.Editor? = sharedPreferences?.edit()
        editor?.putString(key, value)?.apply()
    }

    private fun save(key: String, value: Boolean) {
        val editor: SharedPreferences.Editor? = sharedPreferences?.edit()
        editor?.putBoolean(key, value)?.apply()
    }

    private fun save(key: String, value: Int) {
        val editor: SharedPreferences.Editor? = sharedPreferences?.edit()
        editor?.putInt(key, value)?.apply()
    }

    private fun save(key: String, value: Long) {
        val editor: SharedPreferences.Editor? = sharedPreferences?.edit()
        editor?.putLong(key, value)?.apply()
    }

    companion object {

        private var preferencesWrapper: PreferencesWrapper? = null
        private var sharedPreferences: SharedPreferences? = null

        fun getInstance(): PreferencesWrapper {
            if (preferencesWrapper == null) {
                throw IllegalStateException(
                    "Preferences Wrapper wasn't initialized. " +
                            "Call initPreferences(Context context) to initialize this."
                )
            }
            return preferencesWrapper!!
        }

        fun initPreferences(context: Context) {
            if (preferencesWrapper == null) {
                preferencesWrapper = PreferencesWrapper(context)
            }
        }
    }

}