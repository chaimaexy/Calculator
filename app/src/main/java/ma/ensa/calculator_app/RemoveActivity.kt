package ma.ensa.calculator_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import ma.ensa.calculator_app.model.Element


class RemoveActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {
    private lateinit var myDB: MyDatabaseHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ElementAdapter

    private val elements = mutableListOf<Element>()

    // Inside RemoveActivity
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remove)

        val drawer: DrawerLayout = findViewById(R.id.drawerLayout)
        val Navigation: NavigationView = findViewById(R.id.nav_view)
        val toolbar: Toolbar = findViewById(R.id.toolbar)

        Navigation.bringToFront()
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        Navigation.setNavigationItemSelectedListener(this)

        // Load user elements (you might need to modify this part based on your database)
        myDB = MyDatabaseHelper(this)
        val cursor = myDB.readAllData()
        if (cursor != null) {
            while (cursor.moveToNext()) {
                val id = cursor.getInt(cursor.getColumnIndex("_id"))
                val login = cursor.getString(cursor.getColumnIndex("login"))
                val password = cursor.getString(cursor.getColumnIndex("password"))
                val nom = cursor.getString(cursor.getColumnIndex("nom"))
                val prenom = cursor.getString(cursor.getColumnIndex("prenom"))
                val role = cursor.getString(cursor.getColumnIndex("role"))
                val oldPassword = cursor.getString(cursor.getColumnIndex("old_password"))
                val element = Element(id, login, password, nom, prenom, role, oldPassword)
                elements.add(element)
            }
            cursor.close()
        }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ElementAdapter(elements) { element ->
            // Handle delete button click here
            removeElement(element)
        }
        recyclerView.adapter = adapter
    }



    private fun removeElement(element: Element) {
        // Implement database removal logic here (e.g., delete the element from your database)
        myDB.deleteElement(element.id)
        // Update the RecyclerView after removing the element
        elements.remove(element)
        adapter.notifyDataSetChanged()

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