package com.example.focusstart9.list.domain

import com.example.focusstart9.domain.models.Contact
import io.reactivex.Single

interface ContactsListModel {
    fun getContactsList(): Single<List<Contact>>
}

class ContactsListModelImpl(
    private val getContactsUseCase: GetContactsListUseCase
) : ContactsListModel {
    override fun getContactsList(): Single<List<Contact>> =
        getContactsUseCase()
}