package com.example.applicationguests.listguests.presents

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.applicationguests.activities.GuestFormActivity
import com.example.applicationguests.adapter.GuestsAdapter
import com.example.applicationguests.constants.DataBaseConstants
import com.example.applicationguests.databinding.FragmentAllpresentsBinding
import com.example.applicationguests.listenner.OnGuestListenner
import com.example.applicationguests.viewmodel.GuestsViewModel

class AllPresentsFragment : Fragment() {

    private var _binding: FragmentAllpresentsBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: GuestsViewModel
    private val adapter: GuestsAdapter = GuestsAdapter()
    private lateinit var listenner: OnGuestListenner

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        b: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(GuestsViewModel::class.java)
        _binding = FragmentAllpresentsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //LAYOUT DA VIEW GROUP
        val recycler = binding.recycleAllPresents
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = adapter

        //Classe Anonima
        //EVENTO DE CLICK PARA CONVIDADOS
        listenner = object : OnGuestListenner {
            override fun onClick(id: Int) {
                //Abrir uma activity nova
                val intent = Intent(context, GuestFormActivity::class.java)

                //INICIAR UMA ACTIVITY COM INFORMAÇÕES
                val bundle = Bundle()
                bundle.putInt(DataBaseConstants.GUEST.NEW_ID, id)
                intent.putExtras(bundle)

                startActivity(intent)
            }

            //EVENTO DE LONG CLICK PARA CONVIDADOS
            //CLICAR E SEGURARAR IRA DELETAR O CONVIDADOS
            override fun onDelete(id: Int) {
                viewModel.delete(id)
                viewModel.load(DataBaseConstants.GUEST.FILTER.PRESENT)
            }
        }

        //Criamos os observadores
        observe()

        adapter.attachListener(listenner)
        return root
    }


    override fun onResume() {
        super.onResume()
        viewModel.load(DataBaseConstants.GUEST.FILTER.PRESENT)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //Observamos a Lista de convidados
    private fun observe() {
        viewModel.guestList.observe(viewLifecycleOwner) {
            adapter.updateGuests(it)
        }
    }
}