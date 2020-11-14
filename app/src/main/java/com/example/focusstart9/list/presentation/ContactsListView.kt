package com.example.focusstart9.list.presentation

import com.example.focusstart9.domain.models.Contact
import com.example.focusstart9.presentation.base.BaseView

interface ContactsListView : BaseView {
    fun showContactsList(contactsList: List<Contact>)
    fun showToast(message: String)
    fun checkPermission(): Boolean
}