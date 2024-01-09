package com.shashank.todo_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.shashank.todo_list.databinding.ActivityAddNoteBinding

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var db: NotesDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NotesDatabaseHelper(context = this)

        binding.saveButton.setOnClickListener {
            saveNote()
        }
    }

    private fun saveNote() {
        val title = binding.titleEditText.text.toString()
        val content = binding.contentEditText.text.toString()
        val  datetime=binding.contentEditText.text.toString()
        val  priority=binding.contentEditText.text.toString()
        val  status=binding.contentEditText.text.toString()
        val note = Note(0, title = title, content , datetime , priority, status)
        db.insertNote(note)

        finish()

        Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show()
    }
}
