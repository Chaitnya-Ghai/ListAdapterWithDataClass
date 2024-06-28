package cg.tutorials.listadapterwithdataclass

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListAdapter(var studentData: ArrayList<StudentData>):BaseAdapter() {
    override fun getCount(): Int {
        return studentData.size
    }

    override fun getItem(position: Int): Any {
    return studentData[position]
    }

    override fun getItemId(position: Int): Long {
    return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.layout_list_view_row_items,parent,false)
        val tvRollNo = view.findViewById<TextView>(R.id.tvRollNo)
        tvRollNo.setText(studentData[position].rollNo.toString())
        val tvName = view.findViewById<TextView>(R.id.tvName)
        tvName.setText(studentData[position].name)
        val tvSubject = view.findViewById<TextView>(R.id.tvSubject)
        tvSubject.setText(studentData[position].subject)
        return view
    }
}