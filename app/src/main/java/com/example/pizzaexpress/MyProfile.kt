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

class MyProfile : AppCompatActivity() {
    //>>>>>> temp global val
    val temp = "0779537716"
    var userName: EditText? = null
    var userMobile: EditText? = null
    var userEmail: EditText? = null
    var userAddress: EditText? = null
    var userPassword: EditText? = null
    var submitBtn: Button? = null
    var userObj: UserProfile? = null
    var dbRef: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)
        userName = findViewById(R.id.profileUsername)
        userEmail = findViewById(R.id.profileTextEmail)
        userMobile = findViewById(R.id.profileTextMobile)
        userAddress = findViewById(R.id.profileTextAddress)
        userPassword = findViewById(R.id.profileTextPass)
        submitBtn = findViewById(R.id.profileSubmit)
        userObj = UserProfile()


        //add data to DB
        submitBtn?.setOnClickListener(View.OnClickListener {
            dbRef = FirebaseDatabase.getInstance().reference
            if (TextUtils.isEmpty(userName!!.getText().toString().trim { it <= ' ' })) {
                userName!!.setError("Enter User Name")
                userName!!.requestFocus()
            }
            if (TextUtils.isEmpty(userMobile!!.getText().toString())) {
                userMobile!!.setError("Enter Mobile Number")
                userMobile!!.requestFocus()
            }
            if (TextUtils.isEmpty(userEmail!!.getText().toString().trim { it <= ' ' })) {
                userEmail!!.setError("Enter Email")
                userEmail!!.requestFocus()
            }
            if (TextUtils.isEmpty(userAddress!!.getText().toString().trim { it <= ' ' })) {
                userAddress!!.setError("Enter address")
                userAddress!!.requestFocus()
            }
            if (TextUtils.isEmpty(userPassword!!.getText().toString().trim { it <= ' ' })) {
                userPassword!!.setError("Enter password")
                userPassword!!.requestFocus()
            } else {
                dbRef!!.child("Users").child(temp).child("userName")
                    .setValue(userName!!.getText().toString().trim { it <= ' ' })
                dbRef!!.child("Users").child(temp).child("phoneNumber")
                    .setValue(userMobile!!.getText().toString().toInt())
                dbRef!!.child("Users").child(temp).child("email")
                    .setValue(userEmail!!.getText().toString())
                dbRef!!.child("Users").child(temp).child("address")
                    .setValue(userAddress!!.getText().toString())
                dbRef!!.child("Users").child(temp).child("password")
                    .setValue(userPassword!!.getText().toString())

                //clear input fields
                ClearData()
            }
        })

        //back button redirect to the home page
        val backImgBtn = findViewById<ImageView>(R.id.accBtn)
        backImgBtn.setOnClickListener {
            val launchActivity1 = Intent(applicationContext, Account::class.java)
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

    fun ClearData() {
        userName!!.setText("")
        userMobile!!.setText("")
        userEmail!!.setText("")
        userAddress!!.setText("")
        userPassword!!.setText("")
        Toast.makeText(this, "Successfully updated", Toast.LENGTH_SHORT).show()
    }
}