package at.fh.swengb.becker.homeexercise2

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_note.view.*

class NoteAdapter: RecyclerView.Adapter<NoteViewHolder>() {
    var noteList = listOf<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val noteItemView = inflater.inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(noteItemView)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(viewHolder: NoteViewHolder, position: Int) {
        val note = noteList[position]
        viewHolder.bindItem(note) // Unresolved reference, i.e. the function bindItem does not exist. Why?
    }

    fun updateList(newList: List<Note>) {
        noteList = newList
        notifyDataSetChanged()
    }
}

class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bindItem(note: Note) {
        itemView.note_title.text = note.title
        itemView.note_content.text = note.content
    }
}