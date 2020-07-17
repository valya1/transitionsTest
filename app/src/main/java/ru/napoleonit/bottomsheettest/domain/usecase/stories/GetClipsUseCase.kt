package ru.napoleonit.bottomsheettest.domain.usecase.stories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import ru.napoleonit.bottomsheettest.domain.entities.Clip
import ru.napoleonit.bottomsheettest.domain.repository.ClipsRepository
import ru.napoleonit.bottomsheettest.domain.usecase.UseCase
import javax.inject.Inject

class GetClipsUseCase @Inject constructor(private val clipsRepo: ClipsRepository) : UseCase<List<Clip>, Unit> {

    override suspend fun invoke(params: Unit): List<Clip> = withContext(Dispatchers.IO) {
        delay(1000)
        clipsRepo.getClips()
    }
}
