package com.rohman.fixrecyclerviewblink.viewmodel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rohman.fixrecyclerviewblink.model.BookModel
import com.rohman.fixrecyclerviewblink.repository.remote.BookRepository

class SecondViewmodel :
    ViewModel() {
    private val getBooks by lazy {
        val list = MutableLiveData<ArrayList<BookModel>>()
        BookRepository.getCookingBooks(object : BookRepository.BookCallback {
            override fun onResponse(books: ArrayList<BookModel>) {
                list.postValue(books)
            }

            override fun onError(response: String) {
                Log.d("error", response)
            }
        })
        return@lazy list
    }

    fun getList(): LiveData<ArrayList<BookModel>> {
        return getBooks
    }
}
