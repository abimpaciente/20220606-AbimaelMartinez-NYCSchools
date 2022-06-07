package com.example.common

import android.content.Context
import android.widget.Toast

fun Context.toast(message: String, lenght: Int = Toast.LENGTH_SHORT){
    Toast.makeText(this, message, lenght).show()
}