package com.techxform.anywherematrimony.helpers.event

import java.util.concurrent.atomic.AtomicBoolean

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 *
 * https://gist.github.com/JoseAlcerreca/5b661f1800e1e654f07cc54fe87441af
 *
 */
open class Event<out T>(private val content: T) {


	val hasBeenHandled = AtomicBoolean(false)

	fun consume(handleContent: (T) -> Unit) {
		if (!hasBeenHandled.get()) {
			hasBeenHandled.set(true)
			handleContent(content)
		}
	}

	/**
	 * @return The content whether it's been handled or not.
	 */
	fun peekContent() = content

//    var consumed = false
//        private set // Allow external read but not write
//
//    /**
//     * Consumes the content if it's not been consumed yet.
//     * @return The unconsumed content or `null` if it was consumed already.
//     */
////    fun consume(): T? {
////        return if (consumed) {
////            null
////        } else {
////            consumed = true
////            content
////        }
////    }


	//fun peek(): T = content

	override fun equals(other: Any?): Boolean {
		if (this === other) {
			return true
		}

		if (javaClass != other?.javaClass) {
			return false
		}

        other as Event<*>

        if (content != other.content) {
	        return false
        }

		if (hasBeenHandled != other.hasBeenHandled) {
			return false
		}

		return true
	}

	override fun hashCode(): Int {
		var result = content?.hashCode() ?: 0
		result = 31 * result + hasBeenHandled.hashCode()
		return result
	}
}