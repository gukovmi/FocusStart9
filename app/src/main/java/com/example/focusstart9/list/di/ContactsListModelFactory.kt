package com.example.focusstart9.list.di

import com.example.focusstart9.App.Companion.db
import com.example.focusstart9.data.ContactsAppDataSource
import com.example.focusstart9.data.ContactsDbDataSource
import com.example.focusstart9.data.ContactsRepositoryImpl
import com.example.focusstart9.list.domain.ContactsListModelImpl
import com.example.focusstart9.list.domain.GetContactsListUseCase

class ContactsListModelFactory {
    fun create(): ContactsListModelImpl {
        val contactsAppDataSource = ContactsAppDataSource()
        val contactsDbDataSource = ContactsDbDataSource(db)
        val contactsRepository = ContactsRepositoryImpl(
            contactsAppDataSource,
            contactsDbDataSource
        )

        val getContactsListUseCase = GetContactsListUseCase(contactsRepository)
        return ContactsListModelImpl(
            getContactsListUseCase
        )
    }
}