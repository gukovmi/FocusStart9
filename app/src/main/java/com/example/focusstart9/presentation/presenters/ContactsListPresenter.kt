package com.example.focusstart9.presentation.presenters

import com.example.focusstart9.domain.data_models.Contact
import com.example.focusstart9.domain.models.ContactsListModel
import com.example.focusstart9.presentation.base.BasePresenter
import com.example.focusstart9.presentation.views.ContactsListView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface ContactsListPresenter {
    fun showContactsList(contactsList: List<Contact>)
    fun makeToast(message: String)
    fun getContactsList()
    fun onClickLoadButton()
    fun checkPermission()
}

class ContactsListPresenterImpl @Inject constructor(
    private val model: ContactsListModel
) : ContactsListPresenter,
    BasePresenter<ContactsListView>() {

    override fun showContactsList(contactsList: List<Contact>) {
        view?.showContactsList(contactsList)
    }

    override fun makeToast(message: String) {
        view?.showToast(message)
    }

    override fun getContactsList() {
        model.getContactsList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                showContactsList(it)
            }, {
                makeToast(it.localizedMessage)
            }).untilDestroy()
    }

    override fun onClickLoadButton() {
        checkPermission()
    }

    override fun checkPermission() {
        if (view?.checkPermission() == true)
            getContactsList()
    }
}