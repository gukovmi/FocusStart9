package com.example.focusstart9.di.modules

import com.example.focusstart9.domain.models.ContactsListModel
import com.example.focusstart9.presentation.presenters.ContactsListPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class PresenterModule {
    @Provides
    fun provideContactsListPresenterImpl(model: ContactsListModel): ContactsListPresenterImpl
            =
        ContactsListPresenterImpl(
            model
        )
}