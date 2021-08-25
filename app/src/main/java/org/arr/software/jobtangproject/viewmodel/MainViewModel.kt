package org.arr.software.jobtangproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.arr.software.jobtangproject.model.Note

class MainViewModel: ViewModel() {

    var lst = MutableLiveData<ArrayList<Note>>()
    var newlist = arrayListOf<Note>()

    fun add(note: Note){
        newlist.add(note)
        lst.value=newlist
    }

    fun remove(note: Note){
        newlist.remove(note)
        lst.value=newlist
    }

}