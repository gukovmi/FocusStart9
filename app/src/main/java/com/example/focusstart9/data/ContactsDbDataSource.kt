package com.example.focusstart9.data


import com.example.focusstart9.domain.db.AppDatabase
import com.example.focusstart9.domain.models.Contact
import io.reactivex.Completable
import io.reactivex.Single

class ContactsDbDataSource(private val db: AppDatabase) {
    fun getContacts(): Single<List<Contact>> =
        db.contactsDao().getContacts()

    fun saveContacts(contactsList: List<Contact>): Completable =
        db.contactsDao().saveContacts(contactsList)

    fun clearContacts(): Completable =
        db.contactsDao().clearContacts()
}