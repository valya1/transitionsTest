package ru.napoleonit.bottomsheettest.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.napoleonit.bottomsheettest.MainFragment

@Module
interface FragmensInjectionModule {

    @ContributesAndroidInjector
    fun dispatchMainFragmentInjection(): MainFragment

}
