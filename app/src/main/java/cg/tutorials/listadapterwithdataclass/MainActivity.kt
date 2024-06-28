package cg.tutorials.listadapterwithdataclass

import android.app.ActionBar
import android.app.Dialog
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cg.tutorials.listadapterwithdataclass.databinding.ActivityMainBinding
import cg.tutorials.listadapterwithdataclass.databinding.CustomDialogBinding

class MainActivity : AppCompatActivity() {
   private lateinit var binding: ActivityMainBinding
   private var list = arrayListOf<StudentData>()
    private var listAdapter = ListAdapter(list)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        list.add(StudentData(1,"hii","sample"))
        binding.listView.adapter=listAdapter
        binding.listView.setOnItemClickListener { _, _, position, _ ->
            val dialogBinding = CustomDialogBinding.inflate(layoutInflater)
            val updateDia = Dialog(this).apply {
                setCancelable(false)
                setContentView(dialogBinding.root)
                window?.setLayout(
                    ActionBar.LayoutParams.MATCH_PARENT,
                    ActionBar.LayoutParams.WRAP_CONTENT
                )
                show()
            }
            val update = "Update"
            dialogBinding.addBtn.text=(update)
            val oldName: String = list[position].name
            val oldRollNo: String = list[position].rollNo.toString()
            val oldSubject: String = list[position].subject
            dialogBinding.etName.setText(oldName)
            dialogBinding.etSubject.setText(oldSubject)
            dialogBinding.etRollNo.setText(oldRollNo)
            dialogBinding.addBtn.setOnClickListener {
                if (dialogBinding.etName.text.trim().toString().isNullOrBlank()) {
                    dialogBinding.etName.error = "Enter your Name"
                } else if (dialogBinding.etRollNo.text.trim().toString().isNullOrBlank()) {
                    dialogBinding.etRollNo.error = "Enter your RollNo"
                } else if (dialogBinding.etSubject.text.trim().toString().isNullOrBlank()) {
                    dialogBinding.etSubject.error = "Enter your Subject"
                } else {
                    list[position] = StudentData(
                        dialogBinding.etRollNo.text.toString().toInt(),
                        dialogBinding.etName.text.trim().toString(),
                        dialogBinding.etSubject.text.trim().toString()
                    )
                    listAdapter.notifyDataSetChanged()
                    updateDia.dismiss()
                }
            }
            dialogBinding.cancelButton.setOnClickListener {
                updateDia.dismiss()
            }
        }
        binding.listView.setOnItemLongClickListener { _, _, position, _ ->
            AlertDialog.Builder(this).apply {
                setTitle("are you sure to delete this")
                setPositiveButton("Yes"){_,_->
                    list.removeAt(position)
                    listAdapter.notifyDataSetChanged()
                }
                setNegativeButton("No"){_,_->
                }
                show()
            }
            return@setOnItemLongClickListener true
        }

        binding.faBtn.setOnClickListener {
            val dialogBinding = CustomDialogBinding.inflate(layoutInflater)
            val dialog = Dialog(this).apply {
                setContentView(dialogBinding.root)
                setCancelable(false)
                window?.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)
                show()
            }
            dialogBinding.addBtn.setOnClickListener {
                if(dialogBinding.etName.text.trim().toString().isNullOrBlank()){
                    dialogBinding.etName.error ="Enter your Name"
                }
                else if(dialogBinding.etRollNo.text.trim().toString().isNullOrBlank()){
                    dialogBinding.etRollNo.error ="Enter your RollNo"
                }
                else if(dialogBinding.etSubject.text.trim().toString().isNullOrBlank()){
                    dialogBinding.etSubject.error ="Enter your Subject"
                }
                else{
                    list.add(StudentData(
                        dialogBinding.etRollNo.text.toString().toInt(),
                        dialogBinding.etName.text.toString(),
                        dialogBinding.etSubject.text.toString()
                    ))
                    listAdapter.notifyDataSetChanged()
                    dialog.dismiss()
                }
            }
            dialogBinding.cancelButton.setOnClickListener {
                dialog.dismiss()
            }
        }
    }
}