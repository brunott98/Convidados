package br.com.xbrnghost.convidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.xbrnghost.convidados.constants.DataBaseConstants
import br.com.xbrnghost.convidados.databinding.FragmentAbsentBinding
import br.com.xbrnghost.convidados.view.adapter.GuestsAdapter
import br.com.xbrnghost.convidados.view.listener.OnGuestListener
import br.com.xbrnghost.convidados.viewmodel.GuestsViewModel

class AbsentFragment : Fragment() {

    private var _binding: FragmentAbsentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: GuestsViewModel
    private val adapter = GuestsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        viewModel = ViewModelProvider(this).get(GuestsViewModel::class.java)
        _binding = FragmentAbsentBinding.inflate(inflater, container, false)

        binding.recyclerAbsentGuests.layoutManager = LinearLayoutManager(context)
        binding.recyclerAbsentGuests.adapter = adapter

        val listener = object : OnGuestListener {

            override fun onClick(id: Int) {

                val intent = Intent(context,GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(DataBaseConstants.GUEST.ID,id)
                intent.putExtras(bundle)

                startActivity(intent)

            }

            override fun onDelete(id: Int) {
                viewModel.delete(id)
                viewModel.showAbsentGuests()
            }

        }
        adapter.attachListener(listener)
        viewModel.showAbsentGuests()
        observe()

        return binding.root

    }

    override fun onResume() {

        viewModel.showAbsentGuests()
        super.onResume()

    }

    override fun onDestroyView() {

        super.onDestroyView()
        _binding = null

    }

    private fun observe() {

        viewModel.allGuests.observe(viewLifecycleOwner) {
            adapter.updatedGuests(it)
        }

    }

}