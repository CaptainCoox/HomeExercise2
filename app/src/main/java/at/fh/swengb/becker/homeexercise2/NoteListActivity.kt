package at.fh.swengb.becker.homeexercise2

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_note_list.*

class NoteListActivity : AppCompatActivity() {
    lateinit var db: NotesRoomDatabase
    private var notes = listOf<Note>()
    private val noteAdapter = NoteAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        db = NotesRoomDatabase.getDatabase(this)

        val sharedPreferences = getSharedPreferences("userdata", Context.MODE_PRIVATE)
        val actualName = sharedPreferences.getString("name","Debug")
        val actualAge = sharedPreferences.getInt("age",8)

        info_box.text = "Notes for $actualName, $actualAge"
    }

    override fun onResume() {
        super.onResume()

        notes = db.noteDao.findAll()

        noteAdapter.updateList(notes)
        recyclerView.adapter = noteAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun addNote (view: View){
        val intent = Intent(this, AddNoteActivity::class.java)
        startActivity(intent)
    }
}
