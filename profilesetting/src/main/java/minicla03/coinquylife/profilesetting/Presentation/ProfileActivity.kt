package minicla03.coinquylife.profilesetting.Presentation

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Parcelable
import android.provider.MediaStore
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.util.Objects

import minicla03.coinquylife.profilesetting.R

class ProfileActivity: AppCompatActivity
{
    public override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_layout)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        Objects.requireNonNull(supportActionBar).hide()

        initializeUI()
        setupListeners()
    }

    private fun initializeUI()
    {
        imgProfile = findViewById<View>(R.id.imgProfile)
        tvName = findViewById<View>(R.id.tvName)
        etBio = findViewById<View>(R.id.etBio)
        toolbar = findViewById<View>(R.id.topAppBar)

        if (user != null) {
            tvName.setText(user.getName())
            //tvBio.setText(user.getBio());
            if (user.getProfileImage() != null) {
                //imgProfile.setImageDrawable(Converters.toDrawable(user.getProfileImage()));
            }
        }
    }

    private fun showBioEditDialog() {
        val input = EditText(this)
        input.setText(etBio.getText().toString())

        Builder(this)
            .setTitle("Modifica Bio")
            .setView(input)
            .setPositiveButton("Salva") { dialog, which ->
                etBio.setText(input.text.toString())
            }
            .setNegativeButton("Annulla", null)
            .show()
    }

    private fun showImagePickerDialog() {
        val options = arrayOf("Galleria", "Fotocamera")

        val builder: AlertDialog.Builder = Builder(this)
        builder.setTitle("Scegli immagine profilo")
            .setItems(options) { dialog, which ->
                if (which === 0) {
                    val galleryIntent = Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    )
                    startActivityForResult(galleryIntent, REQUEST_GALLERY)
                } else {
                    val cameraIntent =
                        Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(cameraIntent, REQUEST_CAMERA)
                }
            }.show()
    }

    private fun setupListeners() {
        imgProfile.setOnClickListener { v ->
            showImagePickerDialog()
        }

        etBio.setOnClickListener { v ->
            showBioEditDialog()
        }

        toolbar.setNavigationOnClickListener { v ->
            val intent = Intent(
                this,
                DashboardActivity::class.java
            )
            intent.putExtra("user", user)
            intent.putExtra("coiquyHouse", coiquyHouse)
            startActivity(intent)
        }
        toolbar.setOnMenuItemClickListener { item ->
            if (item.getItemId() === R.id.action_settings) {
                val intent = Intent(
                    this,
                    SettingActivity::class.java
                )
                startActivity(intent)
            }
            false
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != RESULT_OK || data == null) return

        if (requestCode == REQUEST_GALLERY) {
            val selectedImage = data.data
            if (selectedImage != null) {
                imgProfile.setImageURI(selectedImage)

                if (user != null) {
                    //user.setProfileImage(selectedImage.toString());
                }
            }
        } else if (requestCode == REQUEST_CAMERA) {
            val photo = data.extras!!["data"] as Bitmap?
            if (photo != null) {
                imgProfile.setImageBitmap(photo)

                if (user != null) {
                    // devi prima salvare l'immagine su file e poi ottenere l'URI
                    //Uri photoUri = saveBitmapToFile(photo);
                    //user.setProfilePictureUri(photoUri.toString());
                }
            }
        }
    }

}