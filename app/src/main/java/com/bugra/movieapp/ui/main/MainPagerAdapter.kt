package com.bugra.movieapp.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.bugra.movieapp.R
import com.bugra.movieapp.ui.movies.MoviesFragment

class MainPagerAdapter(context: Context, fragmentManager: FragmentManager, behavior: Int) :
    FragmentStatePagerAdapter(fragmentManager, behavior) {

    private val tabs = context.applicationContext.resources.getStringArray(R.array.tabs)

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> MoviesFragment.newInstance()
            1 -> MoviesFragment.newInstance()
            else -> throw  IllegalStateException("Cannot find fragment! Position: $position")
        }
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return tabs[position].toUpperCase()
    }

}