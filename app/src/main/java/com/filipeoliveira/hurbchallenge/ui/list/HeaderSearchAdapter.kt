package com.filipeoliveira.hurbchallenge.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.RecyclerView
import com.filipeoliveira.hurbchallenge.databinding.WidgetSearchBinding

class HeaderSearchAdapter(
    val onTextInserted: (String) -> Unit
) : RecyclerView.Adapter<HeaderSearchAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: WidgetSearchBinding): RecyclerView.ViewHolder(binding.root){
        fun bind() {
            binding.root.layoutParams = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )

            binding.widgetSearchTil.editText?.setOnEditorActionListener { textView, i, _ ->
                if (i == EditorInfo.IME_ACTION_DONE) {
                    val text = textView.text.toString()
                    onTextInserted(text)
                    return@setOnEditorActionListener true
                }

                return@setOnEditorActionListener false
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = WidgetSearchBinding.inflate(inflater)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = 1
}