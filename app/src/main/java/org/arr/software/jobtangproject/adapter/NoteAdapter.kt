package org.arr.software.jobtangproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import org.arr.software.jobtangproject.databinding.ItemViewBinding
import org.arr.software.jobtangproject.model.Note
import org.arr.software.jobtangproject.viewmodel.MainViewModel

class NoteAdapter(
    val viewModel : MainViewModel,
    val arrayList : ArrayList<Note>,
    val context : Context
) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder =ViewHolder(binding)
        return holder
    }

    override fun onBindViewHolder(holder: NoteAdapter.ViewHolder, position: Int) {
        holder.bind(arrayList.get(position))
    }

    override fun getItemCount(): Int {
        if (arrayList.size == 0) {
            Toast.makeText(context, "리스트가 비었습니다.", Toast.LENGTH_LONG).show()
        } else {

        }
        return arrayList.size
    }


    inner class ViewHolder(private val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            binding.title.text = note.text
            binding.delete.setOnClickListener {
                viewModel.remove(note)
                notifyItemRemoved(arrayList.indexOf(note))
            }
        }

    }

}