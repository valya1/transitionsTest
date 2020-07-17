package ru.napoleonit.bottomsheettest.android

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import javax.inject.Inject
import javax.inject.Provider

//class DIFragmentFactory @Inject constructor(
//    private val fragmentProviders: Map<Class<out Fragment>, Provider<Fragment>>
//) : FragmentFactory() {
//
//    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
//        fragmentProviders[classLoader.loadClass(className)]?.get() ?: throw IllegalStateException()
//    }
//}