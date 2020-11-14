package com.example.focusstart9.domain.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.focusstart9.domain.models.Contact
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ContactsDao {
    @Query("SELECT * FROM contacts")
    fun getContacts(): Single<List<Contact>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveContacts(contactsList: List<Contact>): Completable

    @Query("DELETE FROM contacts")
    fun clearContacts(): Completable
}