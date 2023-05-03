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

class Dessert : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dessert)

        //back button redirect to the home page
        val backImgBtn = findViewById<ImageView>(R.id.floatingActionButton3)
        backImgBtn.setOnClickListener {
            val launchActivity1 = Intent(applicationContext, Home::class.java)
            startActivity(launchActivity1)
        }


        //Navigate inside the Item
        val cardObj = findViewById<CardView>(R.id.item_dessert1)
        cardObj.setOnClickListener {
            val intObj = Intent(applicationContext, Dessert1_lava::class.java)
            startActivity(intObj)
        }

        //Navigate inside the Item
        val cardObj2 = findViewById<CardView>(R.id.item_dessert2)
        cardObj2.setOnClickListener {
            val intObj2 = Intent(applicationContext, Dessert2_macaroon::class.java)
            startActivity(intObj2)
        }

        //Navigate inside the Item
        val cardObj3 = findViewById<CardView>(R.id.item_dessert3)
        cardObj3.setOnClickListener {
            val intObj3 = Intent(applicationContext, Dessert3_banoffee::class.java)
            startActivity(intObj3)
        }

        //Navigate inside the Item
        val cardObj4 = findViewById<CardView>(R.id.item_dessert4)
        cardObj4.setOnClickListener {
            val intObj4 = Intent(applicationContext, Dessert4_mooncake::class.java)
            startActivity(intObj4)
        }

        //Navigate inside the Item
        val cardObj5 = findViewById<CardView>(R.id.item_dessert5)
        cardObj5.setOnClickListener {
            val intObj5 = Intent(applicationContext, Dessert5_stick::class.java)
            startActivity(intObj5)
        }

        //Navigate inside the Item
        val cardObj6 = findViewById<CardView>(R.id.item_dessert6)
        cardObj6.setOnClickListener {
            val intObj6 = Intent(applicationContext, Dessert6_whoopie::class.java)
            startActivity(intObj6)
        }
    }
}