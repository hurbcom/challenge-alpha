package br.com.hurbandroidchallenge.data.local.data_source

import br.com.hurbandroidchallenge.commom.contants.Constants
import br.com.hurbandroidchallenge.commom.util.date.DateUtils
import br.com.hurbandroidchallenge.data.local.dao.PlanetsDao
import br.com.hurbandroidchallenge.data.local.model.PlanetEntity
import br.com.hurbandroidchallenge.data.local.model.UpdateEntity
import br.com.hurbandroidchallenge.data.local.preferences.PreferencesWrapper

class PlanetsLocalDataSource(
    private val dao: PlanetsDao
) : StarWarsBookLocalDataSource<PlanetEntity> {
    override suspend fun getEntities() = dao.getPlanets().sortedBy { it.id }

    override suspend fun getEntityById(id: Int) = dao.getPlanetById(id)

    override suspend fun containsEntity(id: Int) = dao.containsPlanets(id) == 1

    override suspend fun clearEntities() {
        PreferencesWrapper.getInstance().clearPlanets()
        dao.clearPlanets()
    }

    override suspend fun getFavoriteEntities() = getEntities().filter { it.favorite }

    override suspend fun getLastSeenEntities(): List<PlanetEntity> {
        return getEntities().filter { it.lastSeen != null }.filter {
            val days = DateUtils.daysUntilToday(it.lastSeen ?: return@filter false)
            days <= Constants.LAST_SEEN_DAYS_INTERVAL
        }
    }

    override suspend fun updateEntity(entity: UpdateEntity): PlanetEntity {
        dao.updatePlanet(entity)
        return dao.getPlanetById(entity.id)
    }

    override suspend fun insertEntities(characters: List<PlanetEntity>) {
        val currentCharacters = getEntities().map { it.id }
        val newCharacters = characters.filter { !currentCharacters.contains(it.id) }
        dao.insertNewPlanets(newCharacters)
    }

}