package com.example.applicationguests.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.applicationguests.constants.DataBaseConstants
import com.example.applicationguests.repository.GuestData
import com.example.applicationguests.repository.GuestRepository

class GuestsViewModel(application: Application) : AndroidViewModel(application) {
    private val mGuestRepository = GuestRepository(application.applicationContext)

    private val mGuestList = MutableLiveData<List<GuestData>>()
    val guestList: LiveData<List<GuestData>> = mGuestList

    fun load(filter: Int) {

        if (filter == DataBaseConstants.GUEST.FILTER.EMPTY) {
            mGuestList.value = mGuestRepository.getAll()
        } else if (filter == DataBaseConstants.GUEST.FILTER.PRESENT) {
            mGuestList.value = mGuestRepository.getPresent()
        } else {
            mGuestList.value = mGuestRepository.getAbsent()
        }
    }

    fun delete(id: Int) {
        mGuestRepository.delete(mGuestRepository.get(id))
    }

}