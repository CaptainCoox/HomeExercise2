package at.fh.swengb.becker.homeexercise2

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNoteActivity : AppCompatActivity() {
    lateinit var db: NotesRoomDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        db = NotesRoomDatabase.getDatabase(this)
    }

    fun saveData (view: View){
        val sharedPreferences = getSharedPreferences("notes", Context.MODE_PRIVATE)

        if (newTitle.text.isEmpty() || newTitle.text.length < 3) {
            fehler.text = "Der Titel muss befüllt sein und mindestens 3 Zeichen beinhalten!"
        }
        else if (newContent.text.isEmpty() || newContent.text.length < 3){
            fehler.text = "Der Inhalt muss befüllt sein und mindestens 3 Zeichen beinhalten!"
        }
        else {
            /*
            sharedPreferences.edit().putString("title", newTitle.text.toString()).apply()
            sharedPreferences.edit().putString("content", newContent.text.toString()).apply()
            finish()
            */

            db.noteDao.insert(Note(newTitle.text.toString(), newContent.text.toString()))

            val intent = Intent(this, NoteListActivity::class.java)
            startActivity(intent)

        }
    }
}
