package com.example.pizzaexpress

import android.annotation.SuppressLint
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
import android.net.Uri
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
import de.hdodenhof.circleimageview.CircleImageView
import java.io.IOException
import java.util.*

class Account : AppCompatActivity() {
    private var userImage: CircleImageView? = null
    private var btnChoose: ImageButton? = null
    private var filePath: Uri? = null
    private val PICK_IMAGE_REQUEST = 1
    private var name: TextView? = null
    private var mobile: TextView? = null
    private var email: TextView? = null
    private var points: TextView? = null
    var storage: FirebaseStorage? = null
    var sRef: StorageReference? = null
    var dbRef: DatabaseReference? = null

    //>>>>>> temp global val
    val temp = "0779537716"
    @SuppressLint("WrongViewCast", "CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        btnChoose = findViewById(R.id.addImageImgBtn)
        userImage = findViewById(R.id.userImage)
        name = findViewById(R.id.accViewName)
        mobile = findViewById(R.id.accViewMobile)
        email = findViewById(R.id.accViewEmail)
        points = findViewById(R.id.accLoylPoints)
        storage = FirebaseStorage.getInstance()
        sRef = storage!!.reference


        //display Image
        val megabyte = (1024 * 1024).toLong()
        sRef!!.child("images/$temp").getBytes(megabyte).addOnSuccessListener { bytes ->
            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            userImage!!.setImageBitmap(bitmap)
        }

        //get data
        dbRef = FirebaseDatabase.getInstance().reference.child("Users/$temp")
        dbRef!!.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                name!!.setText(Objects.requireNonNull(snapshot.child("userName").value).toString())
                mobile!!.setText(
                    Objects.requireNonNull(snapshot.child("phoneNumber").value).toString()
                )
                email!!.setText(Objects.requireNonNull(snapshot.child("email").value).toString())
                points!!.setText(Objects.requireNonNull(snapshot.child("points").value).toString())
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Account, "error loading " + error.message, Toast.LENGTH_SHORT)
                    .show()
            }
        })


        //Navigate to MyProfile
        val editAccBtn = findViewById<Button>(R.id.button)
        editAccBtn.setOnClickListener {
            val launchActivity1 = Intent(applicationContext, MyProfile::class.java)
            startActivity(launchActivity1)
        }


        //select image from gallery
        btnChoose?.setOnClickListener(View.OnClickListener { chooseImage() })


        //back button redirect to the home page
        val backImgBtn = findViewById<ImageView>(R.id.backBtn)
        backImgBtn.setOnClickListener {
            val launchActivity1 = Intent(applicationContext, Home::class.java)
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

    private fun chooseImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
            filePath = data.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                userImage!!.setImageBitmap(bitmap)
                uploadImage()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun uploadImage() {
        if (filePath != null) {
            val progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Uploading....")
            progressDialog.show()
            val ref = sRef!!.child("images/$temp")
            ref.putFile(filePath!!)
                .addOnSuccessListener {
                    progressDialog.dismiss()
                    Toast.makeText(this@Account, "Uploaded successful", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    progressDialog.dismiss()
                    Toast.makeText(this@Account, "Failed " + e.message, Toast.LENGTH_SHORT).show()
                }
                .addOnProgressListener { taskSnapshot ->
                    val progress =
                        (100 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount).toDouble()
                    progressDialog.setMessage("Uploaded " + progress.toInt() + "%")
                }
        }
    }
}