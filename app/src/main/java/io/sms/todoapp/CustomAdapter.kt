package io.sms.todoapp

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlin.coroutines.coroutineContext


class CustomAdapter(private val dataSet: ArrayList<TodoModel>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val todoText: TextView
        val todoBtn: ImageButton

        init {
            // Define click listener for the ViewHolder's View.
            todoText = view.findViewById(R.id.infoOfTodo)
            todoBtn = view.findViewById(R.id.markAsDone)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.single_todo, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.todoText.text = dataSet[position].todoText
        if(!dataSet[position].todoStatus) {
            viewHolder.todoBtn.setImageResource(R.drawable.ic_baseline_pending_24)
        } else {
            viewHolder.todoBtn.setImageResource(R.drawable.ic_baseline_done_24)
        }
        viewHolder.todoBtn.setOnClickListener{
            Toast.makeText(viewHolder.itemView.context, "Todo status changed!", Toast.LENGTH_SHORT).show()
            dataSet[position].todoStatus = !dataSet[position].todoStatus
            notifyItemChanged(position)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}