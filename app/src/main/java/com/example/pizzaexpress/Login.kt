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

class Login : AppCompatActivity() {
    var mPhone: EditText? = null
    var mPassword: EditText? = null
    var mLoginBtn: Button? = null
    var mCreateBtn: TextView? = null
    var dbref: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mPhone = findViewById(R.id.phone)
        mPassword = findViewById(R.id.password)
        mLoginBtn = findViewById(R.id.loginBtn)
        mCreateBtn = findViewById(R.id.createText)
        val user = User()
        mLoginBtn?.setOnClickListener(View.OnClickListener {
            dbref = FirebaseDatabase.getInstance().reference.child("Customer")
            dbref!!.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (dataSnapshot1 in dataSnapshot.children) {
                        val details1 = dataSnapshot1.getValue(
                            User::class.java
                        )
                        if (mPhone!!.getText().toString()
                                .equals(details1!!.phoneNo.toString(), ignoreCase = true) &&
                            mPassword!!.getText().toString()
                                .equals(details1.password.toString(), ignoreCase = true)
                        ) {
                            Toast.makeText(this@Login, "Login successful", Toast.LENGTH_SHORT)
                                .show()
                            val data = mPhone!!.getText().toString().trim { it <= ' ' }
                            val intent = Intent(this@Login, Home::class.java)
                            _gVariables.uPhoneNum = details1.phoneNo
                            intent.putExtra("pno", data)
                            startActivity(intent)
                            break
                        }

                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })
        })
        mCreateBtn?.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    applicationContext,
                    Register::class.java
                )
            )
        })
    }
}