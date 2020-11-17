package com.example.focusstart9.presentation.views

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.focusstart9.App
import com.example.focusstart9.Constants
import com.example.focusstart9.R
import com.example.focusstart9.domain.data_models.Contact
import com.example.focusstart9.presentation.adapters.ContactsListAdapter
import com.example.focusstart9.presentation.base.BaseView
import com.example.focusstart9.presentation.presenters.ContactsListPresenterImpl
import kotlinx.android.synthetic.main.fragment_contacts_list.*
import javax.inject.Inject

interface ContactsListView : BaseView {
    fun showContactsList(contactsList: List<Contact>)
    fun showToast(message: String)
    fun checkPermission(): Boolean
}

class ContactsListFragment : Fragment(),
    ContactsListView {
    @Inject
    lateinit var presenter: ContactsListPresenterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.component.injectsContactsListFragment(this)
        presenter.attachView(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contacts_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadContacts.setOnClickListener {
            presenter.onClickLoadButton()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == Constants.PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                presenter.getContactsList()
            } else {
                showToast(
                    getString(R.string.on_permission_deny)
                )
            }
        }
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showContactsList(contactsList: List<Contact>) {
        val contactsListAdapter =
            this.context?.let {
                ContactsListAdapter(
                    it,
                    contactsList
                )
            }
        contactsListRecyclerView.layoutManager = LinearLayoutManager(this.context)
        contactsListRecyclerView.adapter = contactsListAdapter
    }

    override fun showToast(message: String) {
        Toast.makeText(this.context, message, Toast.LENGTH_LONG).show()
    }

    override fun checkPermission(): Boolean =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && this.context?.let {
                ContextCompat.checkSelfPermission(
                    it,
                    Manifest.permission.READ_CONTACTS
                )
            } != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(Manifest.permission.READ_CONTACTS),
                Constants.PERMISSIONS_REQUEST_READ_CONTACTS
            )
            false
        } else {
            true
        }
}