Mars Photos
==================================

MarsPhotos is a demo app that shows actual images of Mar's surface. These images are
real-life photos from Mars captured by NASA's Mars rovers. The data is stored on a Web server
as a REST web service.

This app demonstrated the use of [Retrofit](https://square.github.io/retrofit/) to make REST requests to the web service, [Gson](https://github.com/google/gson) to
handle the deserialization of the returned JSON to Kotlin data objects, and [Coil](https://coil-kt.github.io/coil/) to load images by URL.

Pre-requisites
--------------

You need to know:
- How to create Composable functions.
- How to use architecture components including ViewModel.
- How to use coroutines for long-running tasks.
- Familiarity with lazy grid

Getting Started
---------------

1. [Install Android Studio](https://developer.android.com/studio/install.html), if you don't already
   have it.
2. Download the sample.
3. Import the sample into Android Studio.
4. Build and run the sample.

## [Build this app through hands-on codelabs in the Android Basics with Compose Course](https://developer.android.com/courses/android-basics-compose/course)

### [Get data from the internet](https://developer.android.com/codelabs/basic-android-kotlin-compose-getting-data-internet)
Learn how to use community-developed libraries to connect to a web service to retrieve and display data in your Android Kotlin compose app. 

### [Add repository and Manual DI](https://developer.android.com/codelabs/basic-android-kotlin-compose-add-repository)
Learn how to improve the architecture of the app by separating the network calls into a repository.

### [Load and display images from the internet](https://developer.android.com/codelabs/basic-android-kotlin-compose-load-images)
Use the Coil library to load and display photos from the internet in your Android Compose app. 
