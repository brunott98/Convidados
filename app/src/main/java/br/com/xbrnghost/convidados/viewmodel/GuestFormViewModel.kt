package br.com.xbrnghost.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.xbrnghost.convidados.GuestModel
import br.com.xbrnghost.convidados.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository(application)

    private val guestModelLiveData = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = guestModelLiveData

    private val _guestSaved = MutableLiveData<String>()
    val guestSaved: LiveData<String> = _guestSaved


    fun save(guest: GuestModel){

        if(guest.id == 0 && guest.name!= ""){


            if(repository.insert(guest)){

                _guestSaved.value = "Inserção feita com sucesso"

            } else{
                _guestSaved.value = "Falha na inserção"
            }



        } else if(guest.id!= 0 && guest.name!= ""){


            if(repository.update(guest)){

                _guestSaved.value = "Atualização feita com sucesso"

            } else{
                _guestSaved.value = "Falha na atualização"
            }



        }

    }


    fun get(id: Int){
        guestModelLiveData.value = repository.get(id)
    }

}