package br.com.xbrnghost.convidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import br.com.xbrnghost.convidados.GuestModel
import br.com.xbrnghost.convidados.R
import br.com.xbrnghost.convidados.constants.DataBaseConstants
import br.com.xbrnghost.convidados.databinding.ActivityGuestFormBinding
import br.com.xbrnghost.convidados.viewmodel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel

    private var guestId = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)



        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        binding.radioPresent.isChecked = true
        binding.buttonSave.setOnClickListener(this)

        observe()

        loadData()


    }

    override fun onClick(v: View) {

        if (v.id == R.id.button_save) {

            val name = binding.editName.text.toString()
            val presence = binding.radioPresent.isChecked


            val model = GuestModel().apply{
                this.id = guestId
                this.name = name
                this.presence = presence
            }

            viewModel.save(model)

        }
    }

    private fun loadData() {

        val bundle = intent.extras
        if (bundle != null) {

            guestId = bundle.getInt(DataBaseConstants.GUEST.ID)
            viewModel.get(guestId)

        }

    }

    private fun observe() {

        viewModel.guest.observe(this) {

            binding.editName.setText(it.name)

            if (it.presence) {
                binding.radioPresent.isChecked = true

            } else {
                binding.radioAbsent.isChecked = true
            }

        }

        viewModel.guestSaved.observe(this) {

            if (it != "") {

                Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
                finish()

            } else {

                Toast.makeText(applicationContext, "Falha", Toast.LENGTH_SHORT).show()

            }

        }

    }

}