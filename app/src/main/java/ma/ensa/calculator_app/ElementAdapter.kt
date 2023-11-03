package ma.ensa.calculator_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ma.ensa.calculator_app.model.Element
class ElementAdapter(
    private val elements: List<Element>,
    private val onDeleteClickListener: (Element) -> Unit
) : RecyclerView.Adapter<ElementAdapter.ElementViewHolder>() {

    inner class ElementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nameTextViewName: TextView = itemView.findViewById(R.id.textViewName)
        val nameTextViewRole: TextView = itemView.findViewById(R.id.textViewRole)
        val nameTextViewLogin: TextView = itemView.findViewById(R.id.textViewEmail)
        val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_element, parent, false)
        return ElementViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ElementViewHolder, position: Int) {
        val element = elements[position]
        holder.nameTextViewName.text = element.nom // Display the user's login or any other appropriate property
        holder.nameTextViewRole.text = element.role // Display the user's login or any other appropriate property
        holder.nameTextViewLogin.text = element.login

        holder.deleteButton.setOnClickListener { onDeleteClickListener(element) }
    }

    override fun getItemCount(): Int {
        return elements.size
    }
}
