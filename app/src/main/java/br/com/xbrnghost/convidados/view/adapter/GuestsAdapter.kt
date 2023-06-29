package br.com.xbrnghost.convidados.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import br.com.xbrnghost.convidados.GuestModel
import br.com.xbrnghost.convidados.databinding.RowGuestBinding
import br.com.xbrnghost.convidados.view.listener.OnGuestListener
import br.com.xbrnghost.convidados.view.viewHolder.GuestViewHolder

class GuestsAdapter: RecyclerView.Adapter<GuestViewHolder>() {

    private var guestList: List<GuestModel> = listOf()
    private lateinit var listener: OnGuestListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {

        val item = RowGuestBinding.inflate(LayoutInflater.
        from(parent.context),parent,false)

        return GuestViewHolder(item, listener)
    }

    override fun getItemCount(): Int {

        return guestList.count()

    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {

        holder.bind(guestList[position])

    }

    fun updatedGuests(list: List<GuestModel>){

        guestList = list
        notifyDataSetChanged()

    }

    fun attachListener(guestListener: OnGuestListener){

        listener = guestListener
    }

}