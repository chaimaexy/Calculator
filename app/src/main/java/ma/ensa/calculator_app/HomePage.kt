package ma.ensa.calculator_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView

class HomePage : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        var isLayoutVisible = false
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)


        val drawer: DrawerLayout = findViewById(R.id.drawerLayout)
        val Navigation: NavigationView = findViewById(R.id.nav_view)
        val toolbar: Toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)

        Navigation.bringToFront()
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        Navigation.setNavigationItemSelectedListener(this)



        var add_button: Button = findViewById<Button>(R.id.add_button)
        var layout_add: LinearLayout = findViewById<LinearLayout>(R.id.addLayout)
        var layout_remove: LinearLayout = findViewById<LinearLayout>(R.id.removeLayout)
        var addElement: Button = findViewById<Button>(R.id.add_User)
        var removeElement: Button = findViewById<Button>(R.id.remove_User)

        add_button.setOnClickListener {
            isLayoutVisible = !isLayoutVisible
            layout_add.visibility = if(isLayoutVisible) View.VISIBLE else View.GONE
            layout_remove.visibility = if(isLayoutVisible) View.VISIBLE else View.GONE
        }
        addElement.setOnClickListener{
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
        removeElement.setOnClickListener{
            val intent = Intent(this, RemoveActivity::class.java)
            startActivity(intent)
        }
        /* writing the user name in the title
        val firstName = intent.getStringExtra("firstName")
        val lastName = intent.getStringExtra("lastName")

        // Find the NavigationView by its ID
        val navigationView = findViewById<NavigationView>(R.id.nav_view)

        // Find the TextView inside the headerLayout of the NavigationView
        val headerView = navigationView.getHeaderView(0)
        val identifTextView = headerView.findViewById<TextView>(R.id.identif)

        // Set the text for the TextView
        //identifTextView.text = "$firstName $lastName"*/
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                val intent = Intent(this,HomePage::class.java)
                startActivity(intent)
            }
            R.id.standard -> {
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
            R.id.scientifique -> {
                val intent = Intent(this,MainActivity2::class.java)
                startActivity(intent)
            }
            R.id.logout -> {
                navigateToAuthenticationPage()
                true
            }

        }
        val drawer: DrawerLayout = findViewById(R.id.drawerLayout)
        drawer.closeDrawer(GravityCompat.START)

        return false
    }

    private fun navigateToAuthenticationPage() {

        val intent = Intent(this, MainActivity3::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish() // Close the current activity
    }
}