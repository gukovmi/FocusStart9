package com.example.focusstart9.presentation.views

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.focusstart9.App
import com.example.focusstart9.R
import com.example.focusstart9.Constants.PERMISSIONS_REQUEST_READ_CONTACTS
import com.example.focusstart9.domain.data_models.Contact
import com.example.focusstart9.presentation.adapters.ContactsListAdapter
import com.example.focusstart9.presentation.base.BaseView
import com.example.focusstart9.presentation.presenters.ContactsListPresenterImpl
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

interface ContactsListView : BaseView {
    fun showContactsList(contactsList: List<Contact>)
    fun showToast(message: String)
    fun checkPermission(): Boolean
}

class ContactsListActivity : AppCompatActivity(),
    ContactsListView {
    @Inject
    lateinit var presenter : ContactsListPresenterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.component.injectsContactsListActivity(this)
        presenter.attachView(this)
        loadContacts.setOnClickListener {
            presenter.onClickLoadButton()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                presenter.getContactsList()
            } else {
                Toast.makeText(
                    this,
                    getString(R.string.on_permission_deny),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showContactsList(contactsList: List<Contact>) {
        val contactsListAdapter =
            ContactsListAdapter(
                this,
                contactsList
            )
        contactsListRecyclerView.layoutManager = LinearLayoutManager(this)
        contactsListRecyclerView.adapter = contactsListAdapter
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun checkPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_CONTACTS),
                PERMISSIONS_REQUEST_READ_CONTACTS
            )
            false
        } else {
            true
        }
    }
}