package com.example.diuresis

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat

class VolumeRecordAdapter internal constructor(context: Context) : RecyclerView.Adapter<VolumeRecordAdapter.VolumeRecordViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var records = emptyList<VolumeRecord>() // Cached copy of words

    inner class VolumeRecordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val volumeRecordView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VolumeRecordViewHolder {
        val itemView = inflater.inflate(R.layout.recycler_view_item, parent, false)
        return VolumeRecordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: VolumeRecordViewHolder, position: Int) {
        val current = records[position]
        val dateFormat = SimpleDateFormat("dd.MM hh:mm")
        val formatted = dateFormat.format(current.createDate)
        holder.volumeRecordView.text = formatted + " - " + current.volume + " мл"
    }

    override fun getItemCount(): Int {
        return records.size
    }

    internal fun setRecords(records: List<VolumeRecord>) {
        this.records = records
        notifyDataSetChanged()
    }
}
