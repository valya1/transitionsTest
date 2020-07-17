package ru.napoleonit.bottomsheettest.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import ru.napoleonit.bottomsheettest.android.BaseApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AndroidSupportInjectionModule::class, FragmensInjectionModule::class, RepositoriesModule::class])
interface AppComponent {

    fun inject(baseApplication: BaseApplication)

    companion object {

        @Volatile
        private var appComponent: AppComponent? = null

        @Synchronized
        fun getOrCreate(): AppComponent {
            return appComponent ?: DaggerAppComponent.builder().build().also { appComponent = it }
        }
    }
}