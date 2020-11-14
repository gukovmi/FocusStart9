package com.example.focusstart9.list.presentation

import com.example.focusstart9.domain.models.Contact
import com.example.focusstart9.list.di.ContactsListModelFactory
import com.example.focusstart9.list.domain.ContactsListModel
import com.example.focusstart9.presentation.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface ContactsListPresenter {
    fun onViewAttached()
    fun showContactsList(contactsList: List<Contact>)
    fun makeToast(message: String)
    fun getContactsList()
    fun onClickLoadButton()
    fun checkPermission()
}

class ContactsListPresenterImpl : ContactsListPresenter,
    BasePresenter<ContactsListView>() {

    private lateinit var model: ContactsListModel

    override fun onViewAttached() {
        model = ContactsListModelFactory().create()
    }

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