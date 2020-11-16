package com.example.focusstart9.domain.use_cases

import com.example.focusstart9.data.repositories.ContactsRepository
import com.example.focusstart9.domain.data_models.Contact
import io.reactivex.Single
import javax.inject.Inject

class GetContactsListUseCase @Inject constructor(
    private val contactsRepository: ContactsRepository
) {
    operator fun invoke(): Single<List<Contact>> =
        contactsRepository.getContacts()
}