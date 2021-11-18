package com.adobe.finalProject.stories

import com.adobe.finalProject.R

enum class Section(val id: Int, val value: String, val viewId: Int) {

    DEFAULT(0, "home", R.id.filterHome),
    ARTS(1, "arts", R.id.filterArts),
    AUTOMOBILES(2, "automobiles", R.id.filterAutomobiles),
    BOOKS(3, "books", R.id.filterBooks),
    BUSINESS(4, "business", R.id.filterBusiness),
}