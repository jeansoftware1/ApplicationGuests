package com.example.applicationguests.viewholder

//REFERENCIAS DE ELEMENTOS

import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationguests.R
import com.example.applicationguests.databinding.RowGuestBinding
import com.example.applicationguests.listenner.OnGuestListenner
import com.example.applicationguests.repository.GuestData

class GuestViewHolder(private val bind: RowGuestBinding, private val listenner: OnGuestListenner) :
    RecyclerView.ViewHolder(bind.root) {

    fun bind(guest: GuestData) {
        bind.textNameguest.text = guest.name

        bind.textNameguest.setOnClickListener {
            listenner.onClick(guest.id)
        }
        //Long click
        //O LONG CLICK IRÁ REMOVER OS CONVIDADOS, CLICANDO E SEGURANDO
        bind.textNameguest.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(v: View): Boolean {

                //ALERTA DE CONFIRMAÇÃO EM PRIMEIRO PLANO
                //PODEMOS ULTILIZAR TRÊS TIPOS DE ALERTAS
                //1 - POSITIVE BUTTON, TEM QUE TER UMA CONFIRMAÇÃO DO USUARIO
                //2 - NEUTRAL BUTTON, NÃO NESSECITA DE CONFIRMAÇÃO, MAS TEM QUE SER CLICADO
                //3 - NEGATIVE BUTTON, IRA CONFIRMAR QUE O USUARIO NÃO DESEJA REMOVER
                AlertDialog.Builder(itemView.context)
                    .setTitle("Guest removal")
                    .setMessage("Are you sure you want to remove?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(/* text = */ "Yes") {/*AÇÂO DE CONFIRMAÇÃO*/ dialog, which ->
                        listenner.onDelete(guest.id)
                    }
                    .setNeutralButton("Cancel", null)
                    .show()

                return true
            }
        })
    }

}