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
    private var state = 0
    //private val ADD_TASK_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        db = NotesRoomDatabase.getDatabase(this)

        /*noteAdapter.updateList(notes)
        recyclerView.adapter = noteAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)*/

        val sharedPreferences = getSharedPreferences("userdata", Context.MODE_PRIVATE)
        val actualName = sharedPreferences.getString("name","Debug")
        val actualAge = sharedPreferences.getInt("age",8)

        info_box.text = "Notes for $actualName, $actualAge"
    }

    override fun onResume() {
        super.onResume()

        /*val sharedPref = getSharedPreferences("notes", Context.MODE_PRIVATE)
        if (state > 0){
            notes.add(Note(sharedPref.getString("title","Debug"), sharedPref.getString("content","Debug")))
        } else {
            state++
        }*/
        notes = db.noteDao.findAll()

        noteAdapter.updateList(notes)
        recyclerView.adapter = noteAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun addNote (view: View){
        val intent = Intent(this, AddNoteActivity::class.java)
        //startActivityForResult(intent, ADD_TASK_REQUEST)
        startActivity(intent)
    }
}
