package com.example.quickcash.Util

/*************************************************
 * Title: LiveData with SnackBar, Navigation and other events (the SingleLiveEvent case)
 * Author: Jose Alcérreca
 * Date: April 27, 2018
 * Code Version: 1
 * Availability: https://medium.com/androiddevelopers/livedata-with-snackbar-navigation-and-other-events-the-singleliveevent-case-ac2622673150
 ***************************************************/
/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 */
open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        if (hasBeenHandled) {
           return null
        } else {
            hasBeenHandled = true
           return content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}