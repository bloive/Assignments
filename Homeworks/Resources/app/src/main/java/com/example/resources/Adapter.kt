package com.example.resources
import android.content.Context
import android.widget.BaseAdapter
import java.security.AccessControlContext

abstract class Adapter(context: Context) : BaseAdapter() {
    private lateinit var mContext: Context

    public fun Adapter (c Context) {
        mContext = c
    }
}