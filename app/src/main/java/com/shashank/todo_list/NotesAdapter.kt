package com.shashank.todo_list

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(private var notes: List<Note>, private val context: Context) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {
    private val db:NotesDatabaseHelper=NotesDatabaseHelper(context)
    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleTextView: TextView = itemView.findViewById(R.id.titletextview)
        var contentTextView: TextView = itemView.findViewById(R.id.contentextview)
        var datetimeTextView: TextView = itemView.findViewById(R.id.datetimetextView)
        var priorityTextView: TextView = itemView.findViewById(R.id.priyoritytextView)
        var statusTextView: TextView = itemView.findViewById(R.id.statustextView)
        val updateButton: ImageView =itemView.findViewById(R.id.updateButton)
        val deleteButton: ImageView =itemView.findViewById(R.id.deleteButton)
        // Add other TextViews or views here
        
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = notes[position]
        holder.titleTextView.text = currentNote.title
        holder.contentTextView.text = currentNote.content
        holder.datetimeTextView.text = currentNote.datetime
        holder.priorityTextView.text = currentNote.priority
        holder.statusTextView.text = currentNote.status

        holder.updateButton.setOnClickListener{
        val intent=Intent(holder.itemView.context,UpdateNoteActivity::class.java).apply{
            putExtra("note_id",currentNote.id);
        }
            holder.itemView.context.startActivity(intent)
        }
        holder.deleteButton.setOnClickListener{
            db.deleteNote(currentNote.id)
            refreshData(db.getAllNotes())
        }
        // Set other TextViews or views here
    }

    fun refreshData(newNotes:List<Note>){
        notes=newNotes
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return notes.size
    }

//    // Update notes when needed
    fun updateNotes(newNotes: List<Note>) {
        notes = newNotes
        notifyDataSetChanged()
    }
}
