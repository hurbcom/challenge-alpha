package br.com.hurbandroidchallenge.data.local.data_source

import br.com.hurbandroidchallenge.commom.contants.Constants
import br.com.hurbandroidchallenge.commom.util.date.DateUtils
import br.com.hurbandroidchallenge.data.local.dao.CharactersDao
import br.com.hurbandroidchallenge.data.local.model.PeopleEntity
import br.com.hurbandroidchallenge.data.local.model.UpdateEntity
import br.com.hurbandroidchallenge.data.local.preferences.PreferencesWrapper

class CharactersLocalDataSource(
    private val dao: CharactersDao
) : StarWarsBookLocalDataSource<PeopleEntity> {
    override suspend fun getEntities() = dao.getCharacters().sortedBy { it.id }

    override suspend fun getEntityById(id: Int) = dao.getCharacterById(id)

    override suspend fun containsEntity(id: Int) = dao.containsCharacter(id) == 1

    override suspend fun clearEntities() {
        PreferencesWrapper.getInstance().clearCharacters()
        dao.clearCharacters()
    }

    override suspend fun getFavoriteEntities() = getEntities().filter { it.favorite }

    override suspend fun getLastSeenEntities(): List<PeopleEntity> {
        return getEntities().filter { it.lastSeen != null }.filter {
            val days = DateUtils.daysUntilToday(it.lastSeen ?: return@filter false)
            days <= Constants.LAST_SEEN_DAYS_INTERVAL
        }
    }

    override suspend fun updateEntity(entity: UpdateEntity): PeopleEntity {
        dao.updateCharacter(entity)
        return dao.getCharacterById(entity.id)
    }

    override suspend fun insertEntities(characters: List<PeopleEntity>) {
        val currentCharacters = getEntities().map { it.id }
        val newCharacters = characters.filter { !currentCharacters.contains(it.id) }
        dao.insertNewCharacters(newCharacters)
    }

}