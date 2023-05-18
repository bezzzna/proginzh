package com.example.yourplace.presentation


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.yourplace.MAIN
import com.example.yourplace.R
import com.example.yourplace.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController


    //функция создания активити
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        //подключение биндинга для удобства
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.first_frag)

        MAIN = this


    }

}