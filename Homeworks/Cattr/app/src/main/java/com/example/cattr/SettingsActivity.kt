package com.example.cattr

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.storage.StorageReference
import com.theartofdev.edmodo.cropper.CropImage
import id.zelory.compressor.Compressor
import id.zelory.compressor.compressFormat
import id.zelory.compressor.constraint.format
import id.zelory.compressor.constraint.quality
import id.zelory.compressor.constraint.resolution
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.activity_status.*
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.File


class SettingsActivity : AppCompatActivity() {
    private var mDatabase: DatabaseReference? = null
    private var mCurrentUser: FirebaseUser? = null
    private var mStorageRef: StorageReference? = null
    private var galleryId: Int = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        mCurrentUser = FirebaseAuth.getInstance().currentUser

        val userId = mCurrentUser!!.uid

        mDatabase = FirebaseDatabase.getInstance().reference
                .child("Users")
                .child(userId)

        mDatabase!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val displayName = snapshot.child("name").value
                var image = snapshot.child("image").value
                val status = snapshot.child("status").value
                var thumbNail = snapshot.child("thumbNail").value

                profileNameSettings.text = displayName.toString()
                profileStatusSettings.text = status.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        changeStatusSettings.setOnClickListener {
            val intent = Intent(this, StatusActivity::class.java)
            intent.putExtra("status", profileStatusSettings.text.toString().trim())
            startActivity(intent)
        }

        changePictureSettings.setOnClickListener {
            val galleryIntent = Intent()
            galleryIntent.type = "image/*"
            galleryIntent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(galleryIntent, galleryId)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == RESULT_OK) {

                val image: Uri? = data!!.data
                CropImage.activity(image)
                    .setAspectRatio(1, 1)
                    .start(this)

                val resultUri: Uri = result.uri
                var userId = mCurrentUser!!.uid
                var thumbFile = File(resultUri.path!!)

                //https://github.com/zetbaitsu/Compressor
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
                if (resultCode == Activity.RESULT_OK) {
                    val resultUri = result.uri
                    var userId = mCurrentUser!!.uid
                    val thumbFile = File(resultUri.path!!)
                    lifecycleScope.launch {
                        var thumbBitmap = Compressor.compress(this@SettingsActivity, thumbFile) {
                            resolution(200, 200)
                            quality(80)
                        }
//                                .compressFormat()

                        //upload image to firebase
                        var byteArray = ByteArrayOutputStream()
                        thumbBitmap.compress
                    }


                }
            }
        }
    }
}