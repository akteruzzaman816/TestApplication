package com.akter.testlibrary

import android.annotation.SuppressLint

object Adfinix {
    const val TAG = "#X_"
    val adUrls = listOf(
        "https://ads-adfinix-staging.sgp1.cdn.digitaloceanspaces.com/TataCube230124/TataCube230124/index.html",
        "https://adfinix-ads.sgp1.cdn.digitaloceanspaces.com/BergerDamGuard280124logoV2/BergerDamGuard280124logoV2/index.html?id=eyJUaW1lIjoiMjAyNC0wMS0yOVQxMDoxNzoyNS4zODI4NTg5NTZaIiwiTWludXRlIjoyODQ0MjA1NywiQ2FtcGFpZ25JZCI6NTg5LCJBZEdyb3VwSWQiOjEwOTIsIkFkc0lkIjoyOTQzLCJTbG90SWQiOjEwNzQsIldlYnNpdGVJZCI6NywiSW1wcmVzc2lvbklkIjowLCJJbXByZXNzaW9ucyI6MSwiVmlld3MiOjAsIkNsaWNrcyI6MCwiQ29udmVyc2lvbnMiOjAsIkJpZExvc3MiOjAsIkFhcFJlY2l2ZWQiOjAsIkJsYW5rSW52ZW50b3J5IjowLCJQMVZpZXdzIjowLCJQMlZpZXdzIjowLCJQM1ZpZXdzIjowLCJQNFZpZXdzIjowLCJQcmljaW5nTW9kZWwiOiJjcG0iLCJBZHZlcnRpc2VyQ29zdCI6MC4wMTUsIlB1Ymxpc2hlclJlY2VpdmVkIjowLjAxLCJBcG1SZWNpdmVkIjowLCJDb3VudHJ5IjoiQmFuZ2xhZGVzaCIsIkNpdHkiOiJEaGFrYSIsIkNhdGVnb3J5IjoiSUFCMTIiLCJBZFNpemUiOiIyNTB4MzAwIiwiQnJvd3NlciI6IkNocm9tZSIsIlBvc2l0aW9uIjoiYmVsb3ciLCJTY3JlZW4iOiIxNTM2IHggODY0IiwiT3MiOiJMaW51eCIsIkxhbmd1YWdlIjoiYmFuZ2xhIiwiTmV0d29yayI6IkFDRSBJVCBOZXR3b3JrcyIsIkRvbWFpbk5hbWUiOiJodHRwczovL3d3dy5iZC1wcmF0aWRpbi5jb20vIiwiRGV2aWNlIjoiVW5rbm93biIsIkRldmljZU1vZGVsIjoiVW5rbm93biIsIkNvbm5lY3Rpb25UeXBlIjoiQ2FibGUvRFNMIiwiQ29ubmVjdGlvblN0YXR1cyI6IjRnIiwiUmVnaW9uIjoiYmFuZ2xhZGVzaCIsIlVuaXF1ZVJlYWNoIjowLCJLZXl3b3JkIjoiIiwiR2VuZGVyIjoibWFsZSIsIlVzZXJBZ2VudCI6Ik1vemlsbGEvNS4wIChYMTE7IExpbnV4IHg4Nl82NCkgQXBwbGVXZWJLaXQvNTM3LjM2IChLSFRNTCwgbGlrZSBHZWNrbykgQ2hyb21lLzEyMC4wLjAuMCBTYWZhcmkvNTM3LjM2IiwiSXAiOiIxMDMuMjQ1LjEwOC4yMDEiLCJJc01vYmlsZSI6Im5vIiwiU3NwT3JEc3AiOiJzc3AiLCJMYXQiOjIzLjcwODYsIkxvbiI6OTAuNDAxNywiR2VvRmVuY2UiOjB9",
        "https://www.pv-magazine.com/wp-content/uploads/2021/06/MS-81-Animated-GIF-Banner-Ad-for-PV-Magazine-594X160_V4.gif",
        "https://developers.google.com/static/admob/images/android-testad-0-admob.png"
    )

    @SuppressLint("SetJavaScriptEnabled")
    fun initialize(adfinixAds: AdfinixAds) {
        adfinixAds.apply {
            isVerticalScrollBarEnabled = false
            isHorizontalScrollBarEnabled = false
            settings.apply {
                loadWithOverviewMode = true
                useWideViewPort = true
                javaScriptEnabled = true
                setInitialScale(110)
            }
        }
    }
}