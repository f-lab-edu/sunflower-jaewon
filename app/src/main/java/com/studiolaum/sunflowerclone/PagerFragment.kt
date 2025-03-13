package com.studiolaum.sunflowerclone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.studiolaum.sunflowerclone.databinding.FragmentPagerBinding

private const val NUM_PAGES = 2

class PagerFragment : Fragment() {
    private val binding by lazy {
        FragmentPagerBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@PagerFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initPagerAndTabLayout()
        return binding.root
    }

    private fun initPagerAndTabLayout() {
        val pager = binding.pager
        val tabLayout = binding.tabLayout
        val tabLayoutTextList =
            listOf(getString(R.string.my_garden), getString(R.string.plant_list))

        pager.adapter = ScreenSlidePagerAdapter(this)

        TabLayoutMediator(tabLayout, pager) { tab, position ->
            tab.text = tabLayoutTextList[position]
            tab.icon =
                context?.let { AppCompatResources.getDrawable(it, R.drawable.ic_launcher_foreground) }
        }.attach()
    }

    private inner class ScreenSlidePagerAdapter(fa: Fragment) : FragmentStateAdapter(fa) {
        private val fragmentList = listOf(MyGardenFragment(), PlantListFragment())

        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment = fragmentList[position]
    }
}