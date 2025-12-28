package com.example.applicationguests.listguests.absents

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
import com.example.applicationguests.databinding.FragmentAllabsentsBinding
import com.example.applicationguests.listenner.OnGuestListenner
import com.example.applicationguests.viewmodel.GuestsViewModel

class AllAbsentsFragment : Fragment() {


    private lateinit var listenner: OnGuestListenner
    private lateinit var viewModel: GuestsViewModel
    private val adapter: GuestsAdapter = GuestsAdapter()
    private var _binding: FragmentAllabsentsBinding? = null

    // Propriedade somente válida entre onCreateView e onDestroyView.
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        b: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(GuestsViewModel::class.java)
        _binding = FragmentAllabsentsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recycler = binding.recycleAllAbsents
        //LAYOUT DA VIEW GROUP
        recycler.layoutManager = LinearLayoutManager(context)
        // ADAPTER
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

                // Atribui o pacote a Intent
                intent.putExtras(bundle)

                startActivity(intent)
            }

            //EVENTO DE LONG CLICK PARA CONVIDADOS
            //CLICAR E SEGURARAR IRA DELETAR O CONVIDADOS
            override fun onDelete(id: Int) {
                viewModel.delete(id)
                viewModel.load(DataBaseConstants.GUEST.FILTER.ABSENT)
            }
        }

        //Criamos os observadores
        observe()

        adapter.attachListener(listenner)
        return root
    }

    override fun onResume() {
        super.onResume()
        viewModel.load(DataBaseConstants.GUEST.FILTER.ABSENT)
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

