package br.com.mdr.starwars.domain.usecase

import br.com.mdr.starwars.domain.model.LastSeen
import br.com.mdr.starwars.domain.model.PageState
import br.com.mdr.starwars.domain.repository.LastSeenRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEmpty

class LastSeenUseCase(
    private val repository: LastSeenRepository
) {
    suspend fun getLastSeen(): Flow<PageState<List<LastSeen>>> = flow {
        repository.getLastSeen()
            .onEmpty {
                emit(PageState.Empty)
            }
            .catch { error ->
                emit(PageState.Error(error))
            }
            .collect {
                emit(PageState.Success(it))
            }
    }
}
