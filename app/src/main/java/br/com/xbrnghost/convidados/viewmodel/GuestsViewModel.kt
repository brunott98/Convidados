package br.com.xbrnghost.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.xbrnghost.convidados.GuestModel
import br.com.xbrnghost.convidados.repository.GuestRepository

class GuestsViewModel(application: Application) : AndroidViewModel(application) {

    //private var repository = GuestRepository.getInstance(application.applicationContext)
    private val repository = GuestRepository(application.applicationContext)


    private val listAllGuests = MutableLiveData<List<GuestModel>>()
    val allGuests: LiveData<List<GuestModel>> = listAllGuests


    fun showAllGuests(){

        listAllGuests.value = repository.getAllGuests()

    }

    fun showAbsentGuests(){

        listAllGuests.value = repository.getAbsentGuests()

    }

    fun showPresentGuests(){

        listAllGuests.value = repository.getPresentGuests()

    }



    fun delete(id: Int){

        repository.delete(id)

    }

}