package at.fh.swengb.becker.homeexercise2

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.sql.Types.NULL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun start(view: View){

        // pruefen, ob Eingaben valide (isString, isInt)
        if (name_in.text.matches(Regex(".*\\d+.*"))) {
            error_box.text = "Der Name darf keine Zahlen enthalten!"
        }
        else if (name_in.text.isEmpty() || name_in.text.length < 3) {
            error_box.text = "Der Name muss befüllt sein und mindestens 3 Zeichen beinhalten!"
        }
        else if (age_in.text.toString().toIntOrNull() == null) {
            error_box.text = "Bitte gültiges Alter eingeben!"
        }
        else if(age_in.text.isEmpty() || age_in.text.toString().toInt() < 0 ) {
            error_box.text = "Das Alter muss befüllt sein und darf nicht negativ sein!"
        }
        else {
            val sharedPreferences = getSharedPreferences("userdata", Context.MODE_PRIVATE)

            sharedPreferences.edit().putString("name", name_in.text.toString()).apply()
            sharedPreferences.edit().putInt("age", age_in.text.toString().toInt()).apply()

            val intent = Intent(this, NoteListActivity::class.java)
            startActivity(intent)
        }
    }
}
