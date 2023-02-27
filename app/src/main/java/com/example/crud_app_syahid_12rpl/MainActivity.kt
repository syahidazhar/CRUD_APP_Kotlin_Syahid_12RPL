package com.example.crud_app_syahid_12rpl

import android.annotation.SuppressLint
import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.crud_app_syahid_12rpl.helper.db_helper
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    lateinit var inputName: TextInputEditText
    lateinit var inputAge: TextInputEditText
    lateinit var btnAdd: Button
    lateinit var btnPrint: Button
    lateinit var btnDelete: Button
    lateinit var textID: TextView
    lateinit var textName: TextView
    lateinit var textAge: TextView
    var progressDialog: ProgressDialog? = null

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inputName = findViewById(R.id.inputname)
        inputAge = findViewById(R.id.inputAge)
        btnAdd = findViewById(R.id.btnADD)
        btnPrint = findViewById(R.id.btnPrint)
        btnDelete = findViewById(R.id.btnDelete)
        textID = findViewById(R.id.id)
        textName = findViewById(R.id.name)
        textAge = findViewById(R.id.age)


        btnAdd.setOnClickListener{
            val db = db_helper(this,null)
            val name = inputName.text.toString()
            val age = inputAge.text.toString()

            db.addProfile(name,age)

            inputName.text!!.clear()
            inputAge.text!!.clear()

            loadData()
        }

        btnPrint.setOnClickListener{
            loadData()
        }

        btnDelete.setOnClickListener{
            val db = db_helper(this,null)
            db.deleteData()
            loadData()
        }

        loadData()
    }

    @SuppressLint("Range")
    fun loadData(){
        val db = db_helper(this, null)
        val cursor = db.getProfile()

        textID.setText("Name\n\n")
        textName.setText("Name\n\n")
        textAge.setText("Name\n\n")

        progressDialog = ProgressDialog(this@MainActivity)
        progressDialog!!.setTitle("Loadige")
        progressDialog!!.setMessage("Hola Senor Como Estas")
        progressDialog!!.max = 100
        progressDialog!!.progress = 1
        progressDialog!!.show()


        Thread(Runnable {
            try {
                Thread.sleep(1000)
            }catch (e:Exception){
                e.printStackTrace()
            }
            progressDialog!!.dismiss()
        }).start()

        if (cursor!!.moveToFirst()){
//                textName.text = "Name\n"
//                textName.text = "Age\n"
            textID.append(cursor.getString(
                cursor.getColumnIndex(db_helper.ID_COL))+"\n")
            textName.append(cursor.getString(
                cursor.getColumnIndex(db_helper.NAME_COL))+"\n")
            textAge.append(cursor.getString(
                cursor.getColumnIndex(db_helper.AGE_COL))+"\n")

        }

        if (cursor.moveToNext()){
            while (cursor.moveToNext()){
                textID.append(cursor.getString(
                    cursor.getColumnIndex(db_helper.ID_COL))+"\n")
                textName.append(cursor.getString(
                    cursor.getColumnIndex(db_helper.NAME_COL))+"\n")
                textAge.append(cursor.getString(
                    cursor.getColumnIndex(db_helper.AGE_COL))+"\n")
            }
        }

        cursor.close()
    }
}