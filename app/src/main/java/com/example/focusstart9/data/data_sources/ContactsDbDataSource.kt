package com.example.focusstart9.data.data_sources


import com.example.focusstart9.App
import com.example.focusstart9.domain.data_models.Contact
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class ContactsDbDataSource @Inject constructor() {
    private val db = App.db
    fun getContacts(): Single<List<Contact>> =
        db.contactsDao().getContacts()

    fun saveContacts(contactsList: List<Contact>): Completable =
        db.contactsDao().saveContacts(contactsList)

    fun clearContacts(): Completable =
        db.contactsDao().clearContacts()
}