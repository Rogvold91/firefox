/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.fenix.downloads.dialog

import android.util.TypedValue
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.R as appcompatR

private const val DOWNLOAD_DIALOG_FONT_INCREASE_SP = 10f

/**
 * Increases title, message and action button text sizes on the first-party download dialog.
 */
internal fun AlertDialog.enlargeDownloadDialogFonts() {
    val applyFonts: () -> Unit = {
        val density = context.resources.displayMetrics.scaledDensity
        fun TextView.increaseBySp(deltaSp: Float) {
            setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize / density + deltaSp)
        }

        findViewById<TextView>(appcompatR.id.alertTitle)?.increaseBySp(DOWNLOAD_DIALOG_FONT_INCREASE_SP)
        findViewById<TextView>(android.R.id.message)?.increaseBySp(DOWNLOAD_DIALOG_FONT_INCREASE_SP)
        getButton(AlertDialog.BUTTON_POSITIVE)?.increaseBySp(DOWNLOAD_DIALOG_FONT_INCREASE_SP)
        getButton(AlertDialog.BUTTON_NEGATIVE)?.increaseBySp(DOWNLOAD_DIALOG_FONT_INCREASE_SP)
        getButton(AlertDialog.BUTTON_NEUTRAL)?.increaseBySp(DOWNLOAD_DIALOG_FONT_INCREASE_SP)
        Unit
    }

    // Buttons may not be ready until after the dialog window is laid out.
    window?.decorView?.post(applyFonts) ?: applyFonts()
}
