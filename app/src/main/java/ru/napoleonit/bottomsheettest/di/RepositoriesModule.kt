package ru.napoleonit.bottomsheettest.di

import dagger.Binds
import dagger.Module
import ru.napoleonit.bottomsheettest.domain.repository.ClipsRepository
import ru.napoleonit.bottomsheettest.domain.repository.database.StoriesRepositoryImpl
import javax.inject.Singleton

@Module
interface RepositoriesModule {

    @Binds
    @Singleton
    fun bindStoriesRepository(storiesRepositoryImpl: StoriesRepositoryImpl): ClipsRepository

}
