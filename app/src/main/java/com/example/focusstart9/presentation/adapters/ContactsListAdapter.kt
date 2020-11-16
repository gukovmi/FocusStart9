package com.example.focusstart9.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.focusstart9.R
import com.example.focusstart9.domain.data_models.Contact
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactsListAdapter(
    private val context: Context,
    private val contactsList: List<Contact>
) : RecyclerView.Adapter<ContactsListAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindContact(contact: Contact) {
            itemView.apply {
                contactNameTextView.text =
                    context.getString(R.string.contact_name_text, contact.name)
                val phoneNumbers =
                    StringBuilder().append(context.getString(R.string.contact_phone_numbers_text))
                for (phoneNumber in contact.phoneNumberList) {
                    if (contact.phoneNumberList.indexOf(phoneNumber)
                        != contact.phoneNumberList.lastIndex
                    ) {
                        phoneNumbers.append("$phoneNumber, ")
                    } else phoneNumbers.append(phoneNumber)
                }
                contactPhoneNumberTextView.text = phoneNumbers
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return contactsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindContact(contactsList[position])
    }
}