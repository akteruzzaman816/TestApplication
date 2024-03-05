### How To Install : 
  [![](https://jitpack.io/v/akteruzzaman816/TestApplication.svg)](https://jitpack.io/#akteruzzaman816/TestApplication)
                      

Step 1. Add it in your root build.gradle at the end of repositories:

	dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}

Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.akteruzzaman816:TestApplication:1.0.4'
	}

 Step 3. Add data to the manifest
         <meta-data
            android:name="com.adfinix.site_id"
            android:value="your_publisher_id" />


### Usage
    <com.akter.testlibrary.AdfinixAds
        app:adSlotId="164"
        android:id="@+id/ad_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent" />
