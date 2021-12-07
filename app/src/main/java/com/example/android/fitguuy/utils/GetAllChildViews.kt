package com.example.android.fitguuy.utils

import android.view.View
import android.view.ViewGroup
//https://stackoverflow.com/questions/33446052/find-all-child-views-for-given-root-view-recursively
//TODO Ikke implementert løsning, bruker tilsvarende kode i RecordViewAdapter for å sette
//lytter på alle barna. Er
class GetAllChildViews {

    fun View.getAllChildren(): List<View> {
        val result = ArrayList<View>()
        if (this !is ViewGroup) {
            result.add(this)
        } else {
            for (index in 0 until this.childCount) {
                val child = this.getChildAt(index)
                result.addAll(child.getAllChildren())
            }
        }
        return result
    }
}