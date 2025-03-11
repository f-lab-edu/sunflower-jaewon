package com.studiolaum.sunflowerclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.studiolaum.sunflowerclone.databinding.ActivityMainBinding

private const val NUM_PAGES = 2

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater).apply { lifecycleOwner = this@MainActivity }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolBar)
        initPagerAndTabLayout()
    }

    private fun initPagerAndTabLayout() {
        val pager = binding.pager
        val tabLayout = binding.tabLayout
        val tabLayoutTextList =
            listOf(getString(R.string.my_garden), getString(R.string.plant_list))

        pager.adapter = ScreenSlidePagerAdapter(this)

        TabLayoutMediator(tabLayout, pager) { tab, position ->
            tab.text = tabLayoutTextList[position]
            tab.icon = AppCompatResources.getDrawable(this, R.drawable.ic_launcher_foreground)
        }.attach()
    }

    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        private val fragmentList = listOf(MyGardenFragment(), PlantListFragment())

        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment = fragmentList[position]
    }
}