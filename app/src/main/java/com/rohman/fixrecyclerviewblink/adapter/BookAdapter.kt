package com.rohman.fixrecyclerviewblink.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rohman.fixrecyclerviewblink.R
import com.rohman.fixrecyclerviewblink.model.BookModel


class BookAdapter(private val context: Context) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {
    private val bookList =  ArrayList<BookModel>()
    fun setData(books: ArrayList<BookModel>) {
        bookList.clear()
        bookList.addAll(books)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val photo = view.findViewById(R.id.imgBook) as ImageView
        val title = view.findViewById(R.id.textTitle) as TextView
        val desc = view.findViewById(R.id.textDesc) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.book_view, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            title.text = bookList[position].title
            desc.text = bookList[position].desc
            Glide.with(context).load(bookList[position].photo).into(photo)
        }
    }
}