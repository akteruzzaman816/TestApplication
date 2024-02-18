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


### Usage
    <com.akter.testlibrary.PreviewAds
        android:layout_width="match_parent"
        android:layout_height="100dp"
        app:ad_type="0"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent" />
