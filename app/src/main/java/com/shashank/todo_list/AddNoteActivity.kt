package com.shashank.todo_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import android.widget.TextView
import com.shashank.todo_list.databinding.ActivityAddNoteBinding
import android.widget.Toast

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var db: NotesDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NotesDatabaseHelper(context = this)

        // Accessing RadioGroups and TextViews from AddNoteActivity layout
        val priorityRadioGroup: RadioGroup = findViewById(R.id.priorityRadioGroup)
        val prioritySpinner: TextView = findViewById(R.id.prioritySpinner)

        val statusRadioGroup: RadioGroup = findViewById(R.id.statusRadioGroup)
        val statusTextView: TextView = findViewById(R.id.statusTextView)

        // Adding RadioGroup logic for Priority
        priorityRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.lowRadioButton -> prioritySpinner.text = "LOW"
                R.id.mediumRadioButton -> prioritySpinner.text = "MEDIUM"
                R.id.highRadioButton -> prioritySpinner.text = "HIGH"
            }
        }

        // Adding RadioGroup logic for Status
        statusRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.completedRadioButton -> statusTextView.text = "COMPLETED"
                R.id.pendingRadioButton -> statusTextView.text = "PENDING"
                R.id.newRadioButton -> statusTextView.text = "NEW"
            }
        }

        binding.saveButton.setOnClickListener {
            saveNote()
        }
    }

    private fun saveNote() {
        val title = binding.titleEditText.text.toString()
        val content = binding.contentEditText.text.toString()
        val datetime = binding.datetimeEditText.text.toString()
        val priority = binding.prioritySpinner.text.toString()
        val status = binding.statusTextView.text.toString()

        val note = Note(0, title, content, datetime, priority, status)
        db.insertNote(note)

        finish()

        Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show()
    }
}
