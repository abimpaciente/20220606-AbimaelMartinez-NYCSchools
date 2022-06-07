package com.example.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.common.toast
import com.example.model.SchoolListResponse
import com.example.nycscools.databinding.ItemSchoolsBinding

class SchoolsAdapter(
    private val items: List<SchoolListResponse>,
    private val onClick: (SchoolListResponse) -> Unit
) :
    RecyclerView.Adapter<SchoolsAdapter.SchoolsViewHolder>() {

    class SchoolsViewHolder(val binding: ItemSchoolsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolsViewHolder =
        SchoolsViewHolder(
            ItemSchoolsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: SchoolsAdapter.SchoolsViewHolder, position: Int) {
        try {
            holder.binding.schoolName.text = items[position].school_name
            holder.binding.schoolLocation.text = items[position].location
            holder.binding.schoolEmail.text = items[position].schoolEmail
            holder.binding.btnScoreSat.setOnClickListener {
                onClick(items[position])
            }

        } catch (e: Exception) {

            holder.binding.root.context.toast(e.toString())
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}