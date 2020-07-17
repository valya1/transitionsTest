package ru.napoleonit.bottomsheettest.domain.repository

import ru.napoleonit.bottomsheettest.domain.entities.Clip

interface ClipsRepository {
    suspend fun saveStories(stories: List<Clip>)
    suspend fun getClips(): List<Clip>

}
