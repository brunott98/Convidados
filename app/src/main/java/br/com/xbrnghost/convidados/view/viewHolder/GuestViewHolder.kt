package br.com.xbrnghost.convidados.view.viewHolder

import android.app.Dialog
import android.content.DialogInterface
import android.view.View
import android.view.View.OnLongClickListener
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import br.com.xbrnghost.convidados.GuestModel
import br.com.xbrnghost.convidados.databinding.RowGuestBinding
import br.com.xbrnghost.convidados.view.listener.OnGuestListener

class GuestViewHolder(private val bind: RowGuestBinding,
                      private val listener: OnGuestListener) : RecyclerView.ViewHolder(bind.root) {


    fun bind(guest: GuestModel) {

        bind.textGuestRow.text = guest.name

        bind.textGuestRow.setOnClickListener {
            listener.onClick(guest.id)

        }

        bind.textGuestRow.setOnLongClickListener{

            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de convidado")
                .setMessage("Tem certeza que deseja remover?")
                .setPositiveButton("Sim") { dialog, which -> listener.onDelete(guest.id) }
                .setNegativeButton("Não",null)
                .create()
                .show()


            true
        }

    }

 }