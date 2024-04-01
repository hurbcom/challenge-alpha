package br.com.gstoduto.starwars.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "starwars")

object PreferencesKey {
    val TIMESTAMP_MOVIES = stringPreferencesKey("timestamp_movies")
    val TIMESTAMP_VEHICLES = stringPreferencesKey("timestamp_vehicles")
    val TIMESTAMP_SPECIES = stringPreferencesKey("timestamp_species")
}