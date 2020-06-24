package com.rohman.fixrecyclerviewblink.repository.remote

import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.rohman.fixrecyclerviewblink.model.BookModel
import com.rohman.fixrecyclerviewblink.utils.Constant
import org.json.JSONObject


object BookRepository {
    val listBook = ArrayList<BookModel>()
    val listCook = ArrayList<BookModel>()
    fun getProgrammingBooks(bookCallback: BookCallback) {
        Log.d("Booklist", listBook.size.toString())
        AndroidNetworking.get(Constant.ENDPOINT_PROGRAMMING.value)
            .setTag(this)
            .setPriority(Priority.HIGH)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    val items = response.getJSONArray("items")
                    for (i in 0 until items.length()) {
                        try {
                            val book = items.getJSONObject(i).getJSONObject("volumeInfo")
                            val id = items.getJSONObject(i).getString("id")
                            val title = book.getString("title");
                            val desc = book.getString("description")
                            val photo = book.getJSONObject("imageLinks").getString("thumbnail")
                            val resBook =
                                BookModel(
                                    id,
                                    photo,
                                    title,
                                    desc
                                )
                            listBook.add(resBook)
                        } catch (e: Exception) {
                            Log.d("bookcallback", e.toString())
                        }

                    }
                    Log.d("DATAMODEL", listBook.toString())
                    bookCallback.onResponse(listBook)
                }

                override fun onError(anError: ANError?) {
                    bookCallback.onError(anError.toString())
                }
            })
        listBook.clear()
    }

    fun getCookingBooks(bookCallback: BookCallback) {
        AndroidNetworking.get(Constant.ENDPOINT_COOKING.value)
            .setTag(this)
            .setPriority(Priority.HIGH)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    val items = response.getJSONArray("items")
                    for (i in 0 until items.length()) {
                        try {
                            val book = items.getJSONObject(i).getJSONObject("volumeInfo")
                            val id = items.getJSONObject(i).getString("id")
                            val title = book.getString("title");
                            val desc = book.getString("description")
                            val photo = book.getJSONObject("imageLinks").getString("thumbnail")
                            val resBook =
                                BookModel(
                                    id,
                                    photo,
                                    title,
                                    desc
                                )
                            listCook.add(resBook)
                        } catch (e: Exception) {
                            Log.d("bookcallback", e.toString())
                        }

                    }
                    Log.d("DATAMODEL", listCook.toString())
                    bookCallback.onResponse(listCook)
                }

                override fun onError(anError: ANError?) {
                    bookCallback.onError(anError.toString())
                }
            })
        listCook.clear()
    }

    interface BookCallback {
        fun onResponse(books: ArrayList<BookModel>)
        fun onError(response: String)
    }
}