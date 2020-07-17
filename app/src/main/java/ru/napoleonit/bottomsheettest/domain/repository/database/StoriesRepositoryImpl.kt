package ru.napoleonit.bottomsheettest.domain.repository.database

import kotlinx.coroutines.delay
import ru.napoleonit.bottomsheettest.domain.entities.Clip
import ru.napoleonit.bottomsheettest.domain.repository.ClipsRepository
import javax.inject.Inject

class StoriesRepositoryImpl @Inject constructor() : ClipsRepository {
    override suspend fun getClips(): List<Clip> {
        delay(500)
        return listOf(Clip("Текст поменьше", true), Clip("Супер большой текст, и еще текст", false))
    }

    override suspend fun saveStories(stories: List<Clip>) {
        delay(500)
    }
}