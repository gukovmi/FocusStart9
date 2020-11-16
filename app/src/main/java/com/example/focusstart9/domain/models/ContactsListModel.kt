package com.example.focusstart9.domain.models

import com.example.focusstart9.domain.data_models.Contact
import com.example.focusstart9.domain.use_cases.GetContactsListUseCase
import io.reactivex.Single
import javax.inject.Inject

interface ContactsListModel {
    fun getContactsList(): Single<List<Contact>>
}

class ContactsListModelImpl @Inject constructor(
    private val getContactsUseCase: GetContactsListUseCase
) : ContactsListModel {
    override fun getContactsList(): Single<List<Contact>> =
        getContactsUseCase()
}