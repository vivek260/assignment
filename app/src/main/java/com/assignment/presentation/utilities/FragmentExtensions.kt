package com.assignment.presentation.utilities

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.assignment.R

fun FragmentManager.addFragmentByTag(fragment: Fragment, tag: String) {
    try {
        beginTransaction()
            .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
            .add(R.id.fragment_container, fragment, tag)
            .addToBackStack(tag)
            .commit()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}