package ma.ensa.calculator_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView


class AddActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)




        var email: EditText = findViewById(R.id.email)
        var password: EditText = findViewById(R.id.password)
        var nom: EditText = findViewById(R.id.nom)
        var prenom: EditText = findViewById(R.id.prenom)
        var role: EditText = findViewById(R.id.role)
        var valider: Button = findViewById(R.id.Valider)
        var cancel: Button = findViewById(R.id.Cancel)

        cancel.setOnClickListener(View.OnClickListener{
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        })
        valider.setOnClickListener(View.OnClickListener {
            val myDB = MyDatabaseHelper(this)

            myDB.addAuth(
                email.text.toString().trim(),
                password.text.toString().trim(),
                nom.text.toString().trim(),
                prenom.text.toString().trim(),
                role.text.toString().trim(),
            )
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        })
    }


}