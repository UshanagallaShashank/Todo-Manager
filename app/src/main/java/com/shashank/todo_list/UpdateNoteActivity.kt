package com.shashank.todo_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.shashank.todo_list.databinding.ActivityUpdateNoteBinding


class UpdateNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateNoteBinding
    private  lateinit var db:NotesDatabaseHelper
    private var noteId:Int=-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = NotesDatabaseHelper( this)

        noteId = intent.getIntExtra( "note_id",  -1)

        if (noteId == -1) {
            finish()
            return
        }

        val note = db.getNoteByID(noteId)

        binding.updateTitleEditText.setText(note.title)
        binding.updateContentEditText.setText(note.content)
        binding.updateDatetimeEditText.setText(note.datetime) // Set datetime
        binding.updatePrioritySpinner.setText(note.priority) // Set priority
        binding.updateStatusSpinner.setText(note.status) // Set status

        binding.updateSaveButton.setOnClickListener{
            val  newTitle=binding.updateTitleEditText.text.toString()
            val newContent = binding.updateContentEditText.text.toString()
            val newDatetime = binding.updateDatetimeEditText.text.toString()
            val newPriority = binding.updatePrioritySpinner.text.toString()
            val newStatus = binding.updateStatusSpinner.text.toString()

            val updatedNote = Note(
                 noteId,
                 newTitle,
                newContent,
                newDatetime,
                newPriority,
                newStatus
            )
            db.updateNote(updatedNote)
            finish()
            Toast.makeText(this,"ChangesSaved", Toast.LENGTH_SHORT).show()
        }
        // Add the necessary code to handle UI elements and update the note
    }
}