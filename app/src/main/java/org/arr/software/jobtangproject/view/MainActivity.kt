package org.arr.software.jobtangproject.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.arr.software.jobtangproject.R
import org.arr.software.jobtangproject.adapter.NoteAdapter
import org.arr.software.jobtangproject.databinding.ActivityMainBinding
import org.arr.software.jobtangproject.model.Note
import org.arr.software.jobtangproject.viewmodel.MainViewModel
import java.util.EnumSet.of
import java.util.List.of

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var viewManager = LinearLayoutManager(this)
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val application = requireNotNull(this).application
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.button.setOnClickListener {
            addData()
        }
        initialiseAdapter()
    }

    private fun initialiseAdapter() {
        binding.recycler.layoutManager = viewManager
        observeData()
    }

    fun observeData() {
        viewModel.lst.observe(this, Observer {
            Log.i("data", it.toString())
            binding.recycler.adapter = NoteAdapter(viewModel, it, this)
        })
    }


    fun addData() {
        var text = binding.titletxt.text.toString()
        if (text.isNullOrBlank()) {
            Toast.makeText(this, "내용을 입력해주세요!", Toast.LENGTH_LONG).show()
        } else {
            var text2 = Note(text)
            viewModel.add(text2)
            binding.titletxt.text.clear()
            binding.recycler.adapter?.notifyDataSetChanged()
        }

    }
}
