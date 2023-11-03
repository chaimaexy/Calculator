package ma.ensa.calculator_app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText


class MainActivity3 : AppCompatActivity() {

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
            val isAuthenticated = databaseHelper.authenticateUser(email, password)

            if (isAuthenticated) {

                val intent = Intent(this, HomePage::class.java)
                startActivity(intent)
            } else {
                // Authentication failed, show a toast message
                Toast.makeText(this, "Authentication failed. Please check your credentials.", Toast.LENGTH_SHORT).show()
            }
        }
    }


}