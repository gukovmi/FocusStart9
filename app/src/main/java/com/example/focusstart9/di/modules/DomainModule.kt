package com.example.focusstart9.di.modules

import com.example.focusstart9.data.repositories.ContactsRepository
import com.example.focusstart9.domain.models.ContactsListModel
import com.example.focusstart9.domain.models.ContactsListModelImpl
import com.example.focusstart9.domain.use_cases.GetContactsListUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun provideGetContactsListUseCase(contactsRepository: ContactsRepository): GetContactsListUseCase =
        GetContactsListUseCase(
            contactsRepository
        )

    @Provides
    fun provideContactsListModel(getContactsUseCase: GetContactsListUseCase): ContactsListModel =
        ContactsListModelImpl(
            getContactsUseCase
        )
}