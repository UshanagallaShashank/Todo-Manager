package com.shashank.todo_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.shashank.todo_list.databinding.ActivityUpdateNoteBinding

class UpdateNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateNoteBinding
    private lateinit var db: NotesDatabaseHelper
    private var noteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = NotesDatabaseHelper(this)

        noteId = intent.getIntExtra("note_id", -1)

        if (noteId == -1) {
            finish()
            return
        }

        val note = db.getNoteByID(noteId)

        binding.updateTitleEditText.setText(note.title)
        binding.updateContentEditText.setText(note.content)
        binding.updateDatetimeEditText.setText(note.datetime) // Set datetime
        binding.updateStatusTextView.text = note.status // Set initial status
        binding.updatePriorityTextView.text = note.priority // Set initial priority

        binding.updateSaveButton.setOnClickListener {
            val newTitle = binding.updateTitleEditText.text.toString()
            val newContent = binding.updateContentEditText.text.toString()
            val newDatetime = binding.updateDatetimeEditText.text.toString()

            // Get selected priority from RadioButton
            val selectedPriority = when {
                binding.updateLowRadioButton.isChecked -> "LOW"
                binding.updateMediumRadioButton.isChecked -> "MEDIUM"
                binding.updateHighRadioButton.isChecked -> "HIGH"
                else -> ""
            }

            // Set the selected priority to the TextView
            binding.updatePriorityTextView.text = selectedPriority

            // Get selected status from RadioButton
            val selectedStatus = when {
                binding.updateCompletedRadioButton.isChecked -> "COMPLETED"
                binding.updatePendingRadioButton.isChecked -> "PENDING"
                binding.updateNewRadioButton.isChecked -> "NEW"
                else -> ""
            }

            // Set the selected status to the TextView
            binding.updateStatusTextView.text = selectedStatus

            val updatedNote = Note(
                noteId,
                newTitle,
                newContent,
                newDatetime,
                selectedPriority, // Use the selected priority
                selectedStatus // Use the selected status
            )
            db.updateNote(updatedNote)
            finish()
            Toast.makeText(this, "ChangesSaved", Toast.LENGTH_SHORT).show()
        }
        // Add the necessary code to handle UI elements and update the note
    }
}
