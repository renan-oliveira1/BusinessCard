package com.ifsp.edu.br.pdm.businesscard.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.ifsp.edu.br.pdm.businesscard.App
import com.ifsp.edu.br.pdm.businesscard.R
import com.ifsp.edu.br.pdm.businesscard.data.BusinessCard
import com.ifsp.edu.br.pdm.businesscard.databinding.ActivityAddBusinessBinding

class AddBusinessActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAddBusinessBinding.inflate(layoutInflater) }
    private val mainViewModel: MainViewModel by viewModels{
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        insertListeners()
    }

    private fun insertListeners(){
        binding.btnClose.setOnClickListener{
            finish()
        }

        binding.btnConfirm.setOnClickListener {
            val businessCard = BusinessCard(
                name = binding.tilName.editText?.text.toString(),
                company = binding.tilCompany.editText?.text.toString(),
                phone = binding.tilPhone.editText?.text.toString(),
                email = binding.tilEmail.editText?.text.toString(),
                backgroundInfo = binding.tilColor.editText?.text.toString(),
            )
            if(!verifyDatasInputs()){
                mainViewModel.insert(businessCard)
                Toast.makeText(this, R.string.label_show_sucess, Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(this, R.string.label_show_error, Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun verifyDatasInputs(): Boolean {
         return binding.tilName.editText?.text.toString().isBlank() || binding.tilCompany.editText?.text.toString().isBlank()
                 || binding.tilEmail.editText?.text.toString().isBlank() || binding.tilColor.editText?.text.toString().isBlank()
                 || binding.tilPhone.editText?.text.toString().isBlank()
    }
}