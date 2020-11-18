package com.example.focusstart9.data.repositories

import com.example.focusstart9.data.data_sources.ContactsAppDataSource
import com.example.focusstart9.data.data_sources.ContactsDbDataSource
import com.example.focusstart9.domain.entity.data_models.Contact
import io.reactivex.Single
import javax.inject.Inject

interface ContactsRepository {
    fun getContacts(): Single<List<Contact>>
}

class ContactsRepositoryImpl @Inject constructor(
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