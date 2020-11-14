package com.example.focusstart9.data

import com.example.focusstart9.domain.models.Contact
import io.reactivex.Single

interface ContactsRepository {
    fun getContacts(): Single<List<Contact>>
}

class ContactsRepositoryImpl(
    private val contactsAppDataSource: ContactsAppDataSource,
    private val contactsDbDataSource: ContactsDbDataSource
) : ContactsRepository {
    override fun getContacts(): Single<List<Contact>> =
        contactsAppDataSource.getContacts()
            .flatMap {
                contactsDbDataSource.clearContacts()
                    .andThen(contactsDbDataSource.saveContacts(it))
                    .andThen(contactsDbDataSource.getContacts())
            }
}