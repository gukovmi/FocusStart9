package com.example.focusstart9.list.domain

import com.example.focusstart9.data.ContactsRepository
import com.example.focusstart9.domain.models.Contact
import io.reactivex.Single

class GetContactsListUseCase(
    private val contactsRepository: ContactsRepository
) {
    operator fun invoke(): Single<List<Contact>> =
        contactsRepository.getContacts()
}