package com.example.applicationguests.repository
//MANIPULAÇÃO DE DADOS

import android.content.Context

class GuestRepository(context: Context) {

    //ACESSO AO BANCO DE DADOS
    private val guestDataBase = GuestDataBase.getDataBase(context).guestDAO()


    fun get(id: Int): GuestData {
        return guestDataBase.load(id)
    }

    /**
     * Insere convidado
     */
    fun save(guest: GuestData): Boolean {
        return guestDataBase.save(guest) > 0
    }

    /**
     * Faz a listagem de todos os convidados
     */
    fun getAll(): List<GuestData> {
        return guestDataBase.getInvited()
    }

    /**
     * Faz a listagem de todos os convidados presentes
     */
    fun getPresent(): List<GuestData> {
        return guestDataBase.getPresent()
    }

    /**
     * Faz a listagem de todos os convidados presentes
     */
    fun getAbsent(): List<GuestData> {
        return guestDataBase.getAbsent()
    }

    /**
     * Atualiza convidado
     */
    fun update(guest: GuestData): Boolean {
        return guestDataBase.update(guest) > 0
    }

    /**
     * Remove convidado
     */
    fun delete(guest: GuestData) {
        guestDataBase.delete(guest)
    }


}

