package com.example.android.fitguuy.utils


//TODO metode for å lage flere id'er, ble aldri brukt. Men ble endel id'er å skrive ettervært
class CONST {
    private val BUTTONLIST: MutableCollection<String>
        get() {
            TODO()
        }

    open fun createListID(name: String, size: Int) { //Lag generisk for å retunere alle type view
        for (n in 1..5) {
            BUTTONLIST.add("R.id".plus(name).plus(n))
        }
    }
}
