package com.example.pizzaexpress

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pizzaexpress.R
import android.content.Intent
import com.example.pizzaexpress.CategoryPizza
import com.example.pizzaexpress.CategorySides
import com.example.pizzaexpress.Beverages
import com.example.pizzaexpress.Dessert
import androidx.drawerlayout.widget.DrawerLayout
import androidx.core.view.GravityCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemReselectedListener
import com.example.pizzaexpress.MyCart
import com.example.pizzaexpress.Favourites
import com.google.android.material.navigation.NavigationView
import com.example.pizzaexpress.ValueOffers
import com.example.pizzaexpress.OrderHistory
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DataSnapshot
import com.example.pizzaexpress.Home
import com.example.pizzaexpress._gVariables
import com.google.firebase.database.DatabaseError
import com.example.pizzaexpress.Register
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.android.gms.tasks.OnSuccessListener
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.pizzaexpress.MyProfile
import android.app.Activity
import android.provider.MediaStore
import android.app.ProgressDialog
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.storage.OnProgressListener
import androidx.cardview.widget.CardView
import com.example.pizzaexpress.Dessert1_lava
import com.example.pizzaexpress.Dessert2_macaroon
import com.example.pizzaexpress.Dessert3_banoffee
import com.example.pizzaexpress.Dessert4_mooncake
import com.example.pizzaexpress.Dessert5_stick
import com.example.pizzaexpress.Dessert6_whoopie
import com.example.pizzaexpress.Login
import com.example.pizzaexpress.UserProfile
import android.text.TextUtils
import android.view.View
import android.widget.*
import com.example.pizzaexpress.OrderDelivery
import com.example.pizzaexpress.Offer1Options
import com.example.pizzaexpress.Offer2Options
import com.example.pizzaexpress.Offer3Options
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.pizzaexpress.Pizza1_sausageDelight
import com.example.pizzaexpress.Pizza2_MeatLoversChicken
import com.example.pizzaexpress.Pizza3_SuperSupreme
import com.example.pizzaexpress.Pizza4_PrawnwithChickenBacon
import com.example.pizzaexpress.Sides1_chickenWingsBbq
import com.example.pizzaexpress.Displayed_Option_list
import com.example.pizzaexpress.DialogQuantityActivity
import com.example.pizzaexpress.CartAdd

class Pizza3_SuperSupreme : AppCompatActivity() {
    private var qty: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pizza2__meat_lovers_chicken)
        qty = findViewById(R.id.itemQty)

        //back button redirect to the home page
        val backImgBtn = findViewById<ImageView>(R.id.backBtn_cat)
        backImgBtn.setOnClickListener {
            val launchActivity1 = Intent(applicationContext, CategoryPizza::class.java)
            startActivity(launchActivity1)
        }


        //Bottom navigation bar
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.botmNavBar)
        bottomNavigationView.setOnNavigationItemReselectedListener { item ->
            when (item.itemId) {
                R.id.Home -> startActivity(Intent(applicationContext, Home::class.java))
                R.id.cart -> startActivity(Intent(applicationContext, MyCart::class.java))
                R.id.favourite -> startActivity(Intent(applicationContext, Favourites::class.java))
                R.id.settings -> startActivity(Intent(applicationContext, Account::class.java))
            }
        }
    }

    //Size selection method
    fun onAlignmentSelected(view: View?) {
        val price = findViewById<TextView>(R.id.price)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        when (radioGroup.checkedRadioButtonId) {
            R.id.size_small -> price.text = "Rs. 550"
            R.id.size_medium -> price.text = "Rs. 800"
            R.id.size_large -> price.text = "Rs. 1400"
        }
    }

    //increase quantity
    fun increaseItemQty(view: View?) {
        var temp: Int
        val text: String
        temp = qty!!.text.toString().toInt()
        temp++
        text = temp.toString()
        qty!!.text = text
    }

    //decrease quantity
    fun decreaseItemQty(view: View?) {
        var temp: Int
        val text: String
        temp = qty!!.text.toString().toInt()
        if (temp == 1) {
            Toast.makeText(this, "Qty should be at least 01", Toast.LENGTH_SHORT).show()
        } else {
            temp--
            text = temp.toString()
            qty!!.text = text
        }
    }
}