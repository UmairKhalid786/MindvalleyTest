package com.mindvalley.test.utils

import android.content.Context
import android.util.DisplayMetrics
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


fun getFormatedDate(serverDate: String):String {

    var formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
    var postedDate = LocalDateTime.parse(serverDate, formatter)

    var currentYearDateFormat = DateTimeFormatter.ofPattern("d MMMM, HH:mm")
    var otherYearDateFormate = DateTimeFormatter.ofPattern("d MMMM yyyy, HH:mm")
    val today = Calendar.getInstance()
    val currentYear = today.get(Calendar.YEAR);

    if (currentYear.equals(postedDate.year))
        return LocalDateTime.parse(serverDate, formatter).format(currentYearDateFormat);
    else
        return LocalDateTime.parse(serverDate, formatter).format(otherYearDateFormate);
}


fun dpToPixel(dp: Int, context: Context): Int {
    return ( dp * (context.getResources()
        .getDisplayMetrics().densityDpi  / DisplayMetrics.DENSITY_DEFAULT))
}

/**
 * This method converts device specific pixels to density independent pixels.
 *
 * @param px A value in px (pixels) unit. Which we need to convert into db
 * @param context Context to get resources and device specific display metrics
 * @return A float value to represent dp equivalent to px value
 */
fun pixelsToDp(px: Float, context: Context): Float {
    return px / (context.getResources()
        .getDisplayMetrics().densityDpi as Float / DisplayMetrics.DENSITY_DEFAULT)
}




