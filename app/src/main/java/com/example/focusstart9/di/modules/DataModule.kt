package com.example.focusstart9.di.modules

import com.example.focusstart9.data.data_sources.ContactsAppDataSource
import com.example.focusstart9.data.data_sources.ContactsDbDataSource
import com.example.focusstart9.data.repositories.ContactsRepository
import com.example.focusstart9.data.repositories.ContactsRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class DataModule {
    @Provides
    fun provideContactsAppDataSource(): ContactsAppDataSource =
        ContactsAppDataSource()

    @Provides
    fun provideContactsDbDataSource(): ContactsDbDataSource =
        ContactsDbDataSource()

    @Provides
    fun provideContactsRepository(
        contactsAppDataSource: ContactsAppDataSource,
        contactsDbDataSource: ContactsDbDataSource
    ): ContactsRepository = ContactsRepositoryImpl(
        contactsAppDataSource,
        contactsDbDataSource
    )
}