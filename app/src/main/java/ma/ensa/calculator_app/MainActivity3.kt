package ma.ensa.calculator_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.android.material.textfield.TextInputEditText


class MainActivity3 : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)






        val emailInput = findViewById<TextInputEditText>(R.id.emailInput)
        val passwordInput = findViewById<TextInputEditText>(R.id.passwordInput)
        val loginButton = findViewById<Button>(R.id.Valider)

        loginButton.setOnClickListener {
            // Get the entered email and password
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            val databaseHelper = MyDatabaseHelper(this)
            val cursor = databaseHelper.authenticateUser(email, password)

            if (cursor != null && cursor.moveToFirst()) {
                val firstNameIndex = cursor.getColumnIndex(MyDatabaseHelper.COLUMN_NOM)
                val lastNameIndex = cursor.getColumnIndex(MyDatabaseHelper.COLUMN_PRENOM)

                if (firstNameIndex >= 0 && lastNameIndex >= 0) {
                    val firstName = cursor.getString(firstNameIndex)
                    val lastName = cursor.getString(lastNameIndex)

                    val intent = Intent(this, HomePage::class.java)

                    startActivity(intent)
                } else {
                    // Handle the case where "nom" and "prenom" columns are not found
                    Toast.makeText(
                        this,
                        "Authentication failed. Please check your credentials.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                // Handle the case where the cursor is null or no data found in the database
                Toast.makeText(
                    this,
                    "Empty fields detected...",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }



    }


}