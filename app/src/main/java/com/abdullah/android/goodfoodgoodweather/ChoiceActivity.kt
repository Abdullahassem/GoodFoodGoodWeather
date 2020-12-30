package com.abdullah.android.goodfoodgoodweather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

private lateinit var coffeeImageView:ImageView
private lateinit var burgerImageView:ImageView
private lateinit var pizzaImageView:ImageView
private lateinit var chickenImageView:ImageView
private lateinit var cakeImageView:ImageView
private lateinit var iceCreamImageView:ImageView
private lateinit var editText: EditText
private lateinit var button: Button

class ChoiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choice)

        coffeeImageView=findViewById(R.id.coffee)
        burgerImageView=findViewById(R.id.burger)
        pizzaImageView=findViewById(R.id.pizza)
        chickenImageView=findViewById(R.id.chicken)
        cakeImageView=findViewById(R.id.cake)
        iceCreamImageView=findViewById(R.id.iceCream)
        editText=findViewById(R.id.editText)
        button=findViewById(R.id.button)

        val lon=intent.getDoubleExtra("longitude",0.0)
        val lat=intent.getDoubleExtra("latitude",0.0)

        fun passTo(term:String){
            var intent = Intent (this,MainActivity::class.java)
            intent.putExtra("latitude",lat)
            intent.putExtra("longitude",lon)
            intent.putExtra("term",term)
            this.startActivity(intent)
        }
        coffeeImageView.setOnClickListener {
            passTo("Coffee")
        }
        burgerImageView.setOnClickListener {
            passTo("Burger")
        }
        pizzaImageView.setOnClickListener {
            passTo("Pizza")
        }
        chickenImageView.setOnClickListener {
            passTo("Chicken")
        }
        cakeImageView.setOnClickListener {
            passTo("Cake")
        }
        iceCreamImageView.setOnClickListener {
            passTo("Ice Cream")
        }

        button.setOnClickListener {
            passTo(editText.text.toString())
        }





    }
}