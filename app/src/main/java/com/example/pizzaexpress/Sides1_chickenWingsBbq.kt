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
import java.lang.Exception

class Sides1_chickenWingsBbq : AppCompatActivity() {
    //variable declaration
    var qty: TextView? = null
    var vName: TextView? = null
    var vPrice: TextView? = null
    var bSize: RadioButton? = null
    var addToCart: Button? = null
    var dbRef: DatabaseReference? = null
    var crt: CartAdd? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sides1_chicken_wings_bbq)

        //assign IDs to variables
        vName = findViewById(R.id.itemName)
        vPrice = findViewById(R.id.price)
        qty = findViewById(R.id.itemQty)
        addToCart = findViewById(R.id.itemBtt_AddToCart)

        //create java object
        crt = CartAdd()


        //back button redirect to the home page
        val backImgBtn = findViewById<ImageView>(R.id.backBtn_cat)
        backImgBtn.setOnClickListener {
            val launchActivity1 = Intent(applicationContext, CategorySides::class.java)
            startActivity(launchActivity1)
        }
        addToCart?.setOnClickListener(View.OnClickListener {
            dbRef = FirebaseDatabase.getInstance().reference.child("Cart")
            try {
//                crt.setItmName(vName.getText().toString().trim { it <= ' ' })
//                crt.setPrice(vPrice.getText().toString().trim { it <= ' ' })
//                crt.setSize(bSize!!.text.toString().trim { it <= ' ' })
//                crt.setQty(qty.getText().toString().trim { it <= ' ' }.toInt())
//                dbRef!!.child("item1").setValue(crt)
                Toast.makeText(applicationContext, "Added to Cart", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    //Size selection method
    fun onAlignmentSelected(view: View?) {
        val price = findViewById<TextView>(R.id.price)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        when (radioGroup.checkedRadioButtonId) {
            R.id.size_small -> {
                price.text = "Rs. 450"
                bSize = findViewById(R.id.size_small)
            }
            R.id.size_medium -> {
                price.text = "Rs. 800"
                bSize = findViewById(R.id.size_medium)
            }
            R.id.size_large -> {
                price.text = "Rs. 1500"
                bSize = findViewById(R.id.size_large)
            }
        }
    }

    //increase quantity
    fun increaseItemQty1(view: View?) {
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