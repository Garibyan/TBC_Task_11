package com.garibyan.armen.tbc_task_11

import android.widget.EditText

fun EditText.insertCorrectText(string: String): String{
    return this.text.toString().ifEmpty { string }
}