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

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //redirect to the pizza page
        val pizzaImgBtn = findViewById<ImageView>(R.id.category1_pizza_imgBtn)
        pizzaImgBtn.setOnClickListener {
            val launchActivity1 = Intent(applicationContext, CategoryPizza::class.java)
            startActivity(launchActivity1)
        }

        //redirect to the sides and other page
        val sidesImgBtn = findViewById<ImageView>(R.id.category2_sides_imgBtn)
        sidesImgBtn.setOnClickListener {
            val launchActivity1 = Intent(applicationContext, CategorySides::class.java)
            startActivity(launchActivity1)
        }

        //redirect to the beverages page
        val bevImgBtn = findViewById<ImageView>(R.id.category3_beverages_imgBtn)
        bevImgBtn.setOnClickListener {
            val launchActivity1 = Intent(applicationContext, Beverages::class.java)
            startActivity(launchActivity1)
        }

        //redirect to the desserts page
        val dessertImgBtn = findViewById<ImageView>(R.id.category4_desserts_imgBtn)
        dessertImgBtn.setOnClickListener {
            val launchActivity1 = Intent(applicationContext, Dessert::class.java)
            startActivity(launchActivity1)
        }


        // View Navigation Drawer
        val backImgBtn = findViewById<ImageView>(R.id.navBackBtn)
        backImgBtn.setOnClickListener {
            val drawer = findViewById<DrawerLayout>(R.id.drawerLay)
            drawer.openDrawer(GravityCompat.START)
        }


        //////////////////////////////////////
        //Bottom navigation bar
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.botmNavBar)
        bottomNavigationView.setOnNavigationItemReselectedListener { item ->
            when (item.itemId) {
                R.id.Home -> {}
                R.id.cart -> startActivity(Intent(applicationContext, MyCart::class.java))
                R.id.favourite -> startActivity(Intent(applicationContext, Favourites::class.java))
                R.id.settings -> startActivity(Intent(applicationContext, Account::class.java))
            }
        }

        /////////////////////////////////
        //Side Navigation Bar
        val navigationView = findViewById<NavigationView>(R.id.sideNavBar)
        navigationView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navOffers -> {
                    startActivity(Intent(applicationContext, ValueOffers::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navOrderhis -> {
                    startActivity(Intent(applicationContext, OrderHistory::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navFav -> {
                    startActivity(Intent(applicationContext, Favourites::class.java))
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
    }
}