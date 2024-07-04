package com.example.fromulmassite

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isEmpty
import androidx.core.widget.doAfterTextChanged
import com.example.fromulmassite.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        var randomNumber = Random.nextInt(0, 101)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.scoreNumberText.text = "$randomNumber/100"
        binding.progressBar.progress = randomNumber

        binding.switchButton.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                binding.authCheckbox.isClickable = true
                binding.newsCheckbox.isClickable = true
                disactivateButtonSave()
            } else {
                binding.authCheckbox.isClickable = false
                binding.authCheckbox.isChecked = false
                binding.newsCheckbox.isClickable = false
                binding.newsCheckbox.isChecked = false
            }
        }

        binding.editTextName.doAfterTextChanged {
            if (!binding.editTextName.text.toString().isEmpty()) activateButtonSave()
            else disactivateButtonSave()
        }
        binding.editTextNumber.doAfterTextChanged {
            if (!binding.editTextNumber.text.toString().isEmpty()) activateButtonSave()
            else disactivateButtonSave()
        }
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            activateButtonSave()
        }
        binding.buttonSave.setOnClickListener {
            Snackbar.make(it, "Изменения сохранены", Snackbar.LENGTH_LONG).show()
        }
        binding.authCheckbox.setOnClickListener {
            activateButtonSave()
        }
        binding.newsCheckbox.setOnClickListener {
            activateButtonSave()
        }
    }

    fun activateButtonSave() {
        if (!binding.editTextName.text.toString().isEmpty() && !binding.editTextNumber.text.toString().isEmpty()) {
            if (binding.radioButton.isChecked || binding.radioButton1.isChecked) {
                if (binding.switchButton.isChecked()) {
                    if (binding.authCheckbox.isChecked || binding.newsCheckbox.isChecked) {
                        binding.buttonSave.isEnabled = true
                    }
                    else{
                        binding.buttonSave.isEnabled = false
                    }
                } else {
                    binding.buttonSave.isEnabled = true
                }
            }
        } else {
            binding.buttonSave.isEnabled = false
        }
    }

    fun disactivateButtonSave() {
        binding.buttonSave.isEnabled = false
    }
}