package com.example.applicationguests.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationguests.databinding.RowGuestBinding
import com.example.applicationguests.listenner.OnGuestListenner
import com.example.applicationguests.repository.GuestData
import com.example.applicationguests.viewholder.GuestViewHolder

class GuestsAdapter : RecyclerView.Adapter<GuestViewHolder>() {

    //Lista de convidados
    private lateinit var guestListenner: OnGuestListenner
    private var guestList: List<GuestData> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        val itemBinding = RowGuestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GuestViewHolder(itemBinding, guestListenner)

    }

    //Implementamos os membros
    //TAMANHO DA LISTA
    override fun getItemCount(): Int {
        return guestList.count()
    }

    //ATRIBUIR VALORES
    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        holder.bind(guestList[position])
    }



    fun updateGuests(list: List<GuestData>) {
        guestList = list
        notifyDataSetChanged()
    }


    //EVENTO DE LISTAGENS
    fun attachListener(listenner: OnGuestListenner) {
        guestListenner = listenner
    }

}