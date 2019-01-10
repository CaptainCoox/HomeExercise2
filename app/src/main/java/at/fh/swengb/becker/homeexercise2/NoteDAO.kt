package at.fh.swengb.becker.homeexercise2

import android.arch.persistence.room.*

@Entity
class Note(@PrimaryKey val title: String, val content: String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

@Dao
interface NoteDao {

    @Insert
    fun insert(note: Note)

    @Query("SELECT * FROM Note")
    fun findAll(): List<Note>
}