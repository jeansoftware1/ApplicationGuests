package com.example.applicationguests.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.applicationguests.repository.GuestData
import com.example.applicationguests.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {
    // Acesso a dados
    private val guestRepository: GuestRepository = GuestRepository(application.applicationContext)

    private var _saveGuest = MutableLiveData<Boolean>()
    val saveGuest: LiveData<Boolean> = _saveGuest

    private var _guest = MutableLiveData<GuestData>()
    val guest: LiveData<GuestData> = _guest


    //SALVAR DADOS NO BANCO DE DADOS
    fun save(id: Int, name: String, presence: Boolean) {
        val guest = GuestData().apply {
            this.id = id
            this.name = name
            this.presence = presence
        }

        if (id == 0) {
            _saveGuest.value = guestRepository.save(guest)
        } else {
            _saveGuest.value = guestRepository.update(guest)
        }
    }
    fun load(id: Int) {
        _guest.value = guestRepository.get(id)
    }
}