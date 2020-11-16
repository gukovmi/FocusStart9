package com.example.focusstart9.data.data_sources

import android.provider.ContactsContract
import com.example.focusstart9.App.Companion.appContext
import com.example.focusstart9.App.Companion.resolver
import com.example.focusstart9.R
import com.example.focusstart9.domain.data_models.Contact
import io.reactivex.Single
import javax.inject.Inject

class ContactsAppDataSource @Inject constructor() {
    fun getContacts(): Single<List<Contact>> {

        val cursor = resolver.query(
            ContactsContract.Contacts.CONTENT_URI, null, null, null,
            null
        )

        val contactsList = arrayListOf<Contact>()

        if (cursor != null && cursor.count > 0) {
            while (cursor.moveToNext()) {
                val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                val name =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                val phoneNumber = (cursor.getString(
                    cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)
                )).toInt()

                if (phoneNumber > 0) {
                    val cursorPhone = resolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?",
                        arrayOf(id),
                        null
                    )

                    cursorPhone?.moveToFirst()

                    if (cursorPhone != null && cursorPhone.count > 0) {
                        val phoneNumValue = cursorPhone.getString(
                            cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                        )

                        val currentContact =
                            Contact(
                                name,
                                arrayListOf(phoneNumValue)
                            )
                        contactsList.add(currentContact)

                        while (cursorPhone.moveToNext()) {
                            val nextPhoneNumValue = cursorPhone.getString(
                                cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                            )
                            contactsList.last().phoneNumberList.add(nextPhoneNumValue)
                        }
                    }
                    cursorPhone?.close()
                }
            }
            cursor.close()
            return Single.just(contactsList)
        } else {
            cursor?.close()
            return Single.error(Exception(appContext().getString(R.string.no_contacts_available)))
        }
    }
}