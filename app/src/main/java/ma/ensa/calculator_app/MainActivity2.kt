package ma.ensa.calculator_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import org.mariuszgromada.math.mxparser.Expression
import kotlin.math.*

class MainActivity2 : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var variable: String
    private lateinit var input: TextView
    private lateinit var output: TextView
    private var memoryValue: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        val drawer: DrawerLayout =findViewById(R.id.drawerLayout)
        val Navigation:NavigationView=findViewById(R.id.nav_view)
        val toolbar: Toolbar = findViewById(R.id.toolbar)

        //setSupportActionBar(toolbar)

        input = findViewById(R.id.input1)
        output = findViewById(R.id.output1)
        variable = ""
        Navigation.bringToFront()
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        Navigation.setNavigationItemSelectedListener(this)

        val egale: Button = findViewById(R.id.egale1)
        val clearButton: Button = findViewById(R.id.C1)
        val read:Button=findViewById(R.id.MR1)
        val sin:Button=findViewById(R.id.sin)
        val cos:Button=findViewById(R.id.cos)
        val power:Button=findViewById(R.id.carry)
        val tan:Button=findViewById(R.id.tan)
        val ln:Button=findViewById(R.id.ln)
        val exp:Button=findViewById(R.id.expo)
        val absolu:Button=findViewById(R.id.absolu)
        val inverse:Button=findViewById(R.id.inverse)
        val number0: Button = findViewById(R.id.button_01)
        val number1: Button = findViewById(R.id.button_11)
        val number2: Button = findViewById(R.id.button_21)
        val number3: Button = findViewById(R.id.button_31)
        val number4: Button = findViewById(R.id.button_41)
        val number5: Button = findViewById(R.id.button_51)
        val number6: Button = findViewById(R.id.button_61)
        val number7: Button = findViewById(R.id.button_71)
        val number8: Button = findViewById(R.id.button_81)
        val log:Button=findViewById(R.id.log)
        val sqrt:Button=findViewById(R.id.sqrt)
        val number9: Button = findViewById(R.id.button_91)
        val virgule: Button = findViewById(R.id.virgulr1)
        val pareRight:Button=findViewById(R.id.pareRight1)
        val pareLeft:Button=findViewById(R.id.pareLeft1)
        val modulo: Button = findViewById(R.id.modulo1)
        val addition: Button = findViewById(R.id.plus1)
        val multiplication: Button =findViewById(R.id.fois1)
        val sustraction: Button = findViewById(R.id.moin1)
        val division: Button = findViewById(R.id.division1)
        val buttonMPlus: Button =findViewById(R.id.mLeft1)
        val buttonMmoin: Button =findViewById(R.id.mRight1)
        val buttonMc: Button =findViewById(R.id.MC1)
        val supprime:Button=findViewById(R.id.supp)
        val fact:Button=findViewById(R.id.X)
        sin.setOnClickListener {
            input.text = add_data1("sin(")
        }
        inverse.setOnClickListener{
            input.text = add_data1("1/(")
        }
        supprime.setOnClickListener {
            deleteLastCharacterFromInput()
        }
        fact.setOnClickListener {
            input.text = add_data1("!")
        }

        power.setOnClickListener {
            input.text = add_data1("^")
        }
        absolu.setOnClickListener{
            input.text = add_data1("abs(")
        }

        cos.setOnClickListener {
            input.text = add_data1("cos(")
        }

        tan.setOnClickListener {
            input.text = add_data1("tan(")
        }

        ln.setOnClickListener{
            input.text=add_data1("ln(")
        }
        exp.setOnClickListener{
            input.text=add_data1("e^(")
        }
        log.setOnClickListener{
            input.text=add_data1("log(")
        }
        number0.setOnClickListener {
            input.text = add_data1("0")
        }

        number1.setOnClickListener {
            input.text = add_data1("1")
        }

        number2.setOnClickListener {
            input.text = add_data1("2")
        }
        number3.setOnClickListener {
            input.text = add_data1("3")
        }

        number4.setOnClickListener {
            input.text = add_data1("4")
        }
        sqrt.setOnClickListener{
            input.text = add_data1("sqrt(")
        }
        number5.setOnClickListener {
            input.text = add_data1("5")
        }

        number6.setOnClickListener {
            input.text = add_data1("6")
        }

        number7.setOnClickListener {
            input.text = add_data1("7")
        }

        number8.setOnClickListener {
            input.text = add_data1("8")
        }

        number9.setOnClickListener {
            input.text = add_data1("9")
        }
        addition.setOnClickListener {
            handleOperatorButton1('+')
        }

        sustraction.setOnClickListener {
            handleOperatorButton1('-')
        }

        multiplication.setOnClickListener {
            handleOperatorButton1('*')
        }

        division.setOnClickListener {
            handleOperatorButton1('/')
        }

        modulo.setOnClickListener {
            handleOperatorButton1('%')
        }
        virgule.setOnClickListener {
            input.text = add_data1(".")
        }
        pareRight.setOnClickListener{
            input.text = add_data1(")")
        }
        pareLeft.setOnClickListener{
            input.text = add_data1("(")
        }
        clearButton.setOnClickListener {
            input.text = ""
            output.text = ""
        }

        read.setOnClickListener{
            output.text=getValueToStore1().toString()
        }
        buttonMc.setOnClickListener{
            clearMemory1()
        }
        buttonMPlus.setOnClickListener {
            memoryValue += Expression(getInputExpression1()).calculate()
        }

        buttonMmoin.setOnClickListener {
            // Soustrayez la valeur actuelle de la mémoire
            memoryValue -= Expression(getInputExpression1()).calculate()
        }
        egale.setOnClickListener {
            showResult1()
        }


    }
    private fun add_data1(param: String): String {
        variable = input.text.toString()
        return "$variable$param"
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            com.google.android.material.R.id.standard -> {
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
            R.id.scientifique -> {
                val intent = Intent(this,MainActivity2::class.java)
                startActivity(intent)
            }

        }
        val drawer: DrawerLayout = findViewById(R.id.drawerLayout)
        drawer.closeDrawer(GravityCompat.START)

        return false
    }
    private fun handleOperatorButton1(operator: Char) {
        val inputText = input.text.toString()
        if (inputText.isNotEmpty()) {
            val lastChar = inputText.last()

            if (lastChar.isOperator1() && operator != lastChar) {
                input.text = inputText.dropLast(1) + operator
            } else if (!lastChar.isOperator1()) {
                input.text = add_data1(operator.toString())
            }
        } else {

            input.text = add_data1(operator.toString())
        }
    }

    private fun Char.isOperator1(): Boolean {
        return this in setOf('+', '-', '*', '/', '%')
    }
    private fun getInputExpression1(): String {
        var expression = input.text.toString().replace(Regex("x"), "*")
        return expression
    }
    private fun getValueToStore1(): Double {
        val valueToStore = memoryValue
        output.text = valueToStore.toString()
        return valueToStore
    }
    private fun clearMemory1(){
        memoryValue=0.0
    }

    private fun showResult1() {
        try {
            val expression = getInputExpression1()
            val result = Expression(expression).calculate()
            output.text = result.toString()
            input.text=""
        } catch (e: Exception) {
            output.text = "Error"
        }
    }

    private fun deleteLastCharacterFromInput() {
        val currentInput = input.text.toString()
        if (currentInput.isNotEmpty()) {
            val newInput = currentInput.substring(0, currentInput.length - 1)
            input.text = newInput
        }
    }



}