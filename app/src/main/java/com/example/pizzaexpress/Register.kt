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

class Register : AppCompatActivity() {
    var mFullName: EditText? = null
    var mEmail: EditText? = null
    var mPassword: EditText? = null
    var mPhone: EditText? = null
    var mAddress: EditText? = null
    var mRegisterBtn: Button? = null
    var mLoginBtn: TextView? = null
    var ref: DatabaseReference? = null
    var rootNode: FirebaseDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        //init layout views
        mFullName = findViewById(R.id.fullName)
        mEmail = findViewById(R.id.Email)
        mPassword = findViewById(R.id.password)
        mPhone = findViewById(R.id.phone)
        mAddress = findViewById(R.id.address)
        mRegisterBtn = findViewById(R.id.registerBtn)
        mLoginBtn = findViewById(R.id.createText)
        mRegisterBtn?.setOnClickListener(View.OnClickListener {
            rootNode = FirebaseDatabase.getInstance()
            ref = rootNode!!.getReference("Customer")
            if (mPassword!!.getText().length < 7) Toast.makeText(
                applicationContext,
                "Password is not valid",
                Toast.LENGTH_SHORT
            ).show() else if (mEmail!!.getText().length < 1) Toast.makeText(
                applicationContext, "Email is required", Toast.LENGTH_SHORT
            ).show() else if (mPhone!!.getText().length < 10) Toast.makeText(
                applicationContext, "Phone number is not valid", Toast.LENGTH_SHORT
            ).show() else if (mFullName!!.getText().length < 1) Toast.makeText(
                applicationContext, "Full Name is required", Toast.LENGTH_SHORT
            ).show() else {

                //get data from User class
                val name = mFullName!!.getText().toString().trim { it <= ' ' }
                val email = mEmail!!.getText().toString().trim { it <= ' ' }
                val phoneNo = mPhone!!.getText().toString().trim { it <= ' ' }
                val password = mPassword!!.getText().toString().trim { it <= ' ' }
                val address = mAddress!!.getText().toString().trim { it <= ' ' }
                val points = "20.00"
                val user = User(email, password, name, phoneNo, address, points)
                ref!!.child(phoneNo).setValue(user)
                Toast.makeText(
                    applicationContext,
                    "Account created successfully!",
                    Toast.LENGTH_SHORT
                ).show()
                val newInt = Intent(this@Register, Login::class.java)
                newInt.putExtra("pno", phoneNo)
                startActivity(newInt)
            }
        })
        mLoginBtn?.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    applicationContext,
                    Login::class.java
                )
            )
        })
    }
}