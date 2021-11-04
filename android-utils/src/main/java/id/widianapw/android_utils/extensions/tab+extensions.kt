package id.widianapw.android_utils.extensions

import com.google.android.material.tabs.TabLayout

/**
 * Created by Widiana Putra on 04/11/21
 * Copyright (c) PT. TIMEDOOR INDONESIA
 */


fun TabLayout.onTabSelected(onTabSelected:(TabLayout.Tab?) -> Unit){
    this.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
        override fun onTabSelected(tab: TabLayout.Tab?) {
            return onTabSelected.invoke(tab)
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
        }

        override fun onTabReselected(tab: TabLayout.Tab?) {
        }

    })
}