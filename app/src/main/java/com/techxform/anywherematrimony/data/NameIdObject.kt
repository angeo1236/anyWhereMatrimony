package com.techxform.anywherematrimony.data

import com.techxform.anywherematrimony.extensions.empty

class NameIdObject {
    var id: String = String.empty
    var name: String = String.empty
    var selected: Boolean = false

    override fun toString(): String {
        return name
    }
}