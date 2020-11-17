package com.example.focusstart9.di

import com.example.focusstart9.di.modules.DataModule
import com.example.focusstart9.di.modules.DomainModule
import com.example.focusstart9.di.modules.PresenterModule
import com.example.focusstart9.presentation.views.ContactsListFragment
import dagger.Component

@Component(modules = [DataModule::class, DomainModule::class, PresenterModule::class])
interface AppComponent {
    fun injectsContactsListFragment(contactsListFragment: ContactsListFragment)
}