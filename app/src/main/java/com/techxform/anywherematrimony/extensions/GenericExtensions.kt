package com.techxform.anywherematrimony.extensions

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.text.format.DateUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

/**
 * Returns false if 'null', else value
 */
fun Boolean?.safeGet(): Boolean = this ?: false

/**
 * Launch 3rd party intent with custom [action] (if specified) & [uriString]
 */
fun Context.launchIntent(action: String = Intent.ACTION_VIEW, uriString: String) =
    startActivity(Intent(action, Uri.parse(uriString)))


val String.Companion.empty: String get() = String()

/**
 * Returns empty string if 'null', else value
 */
fun String?.safeGet(): String = this ?: String.empty


/**
 * Kotlin extension to get the class name.
 *
 * This can also be used a TAGs for logging.
 *
 * @return Caller class' name
 */
val Any.TAG: String get() = javaClass.simpleName


/**
 * Return true if string is validEmail
 */
fun String?.isEmailValid() = Patterns.EMAIL_ADDRESS.matcher(
    this
).matches()

fun Date.formatTo(dateFormat: String, timeZone: TimeZone = TimeZone.getDefault()): String {
    val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
    formatter.timeZone = timeZone
    return formatter.format(this)
}

fun Date.getRelativeTimeSpan() : String{
  return  DateUtils.getRelativeTimeSpanString(
        this.time
    ).toString()
}

val Int.px: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Fragment.addOnWindowFocusChangeListener(callback: (hasFocus: Boolean) -> Unit) =
    view?.viewTreeObserver?.addOnWindowFocusChangeListener(callback)

