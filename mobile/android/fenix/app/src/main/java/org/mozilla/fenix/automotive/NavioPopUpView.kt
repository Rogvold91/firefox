/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.fenix.automotive

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import org.mozilla.fenix.R
import org.mozilla.fenix.ext.getRootView

object NavioPopUpView {

    private const val DURATION_MS = 5_000L
    private const val MARGIN_BOTTOM_DP = 46

    private var popupView: View? = null
    private var handler: Handler? = null
    private val dismissRunnable = Runnable { dismissPopUp() }

    fun isPopUpShowing(): Boolean = popupView != null

    fun showPopUpIfNotShowing(
        context: Context,
        textMessage: String,
        statusIconResId: Int,
        closeIconResId: Int,
    ) {
        if (!isPopUpShowing()) {
            showPopUp(context, textMessage, statusIconResId, closeIconResId)
        }
    }

    fun showPopUp(
        context: Context,
        textMessage: String,
        statusIconResId: Int,
        closeIconResId: Int,
    ) {
        val root = context.getRootView() as? ViewGroup ?: return

        dismissPopUp()

        val inflated = LayoutInflater.from(context).inflate(R.layout.navio_snackbar_base, root, false)

        inflated.findViewById<TextView>(R.id.navio_text_message).text = textMessage

        val iconStatus = inflated.findViewById<ImageView>(R.id.navio_icon)
        when {
            statusIconResId > 0 -> iconStatus.setImageResource(statusIconResId)
            statusIconResId < 0 -> iconStatus.visibility = View.GONE
        }

        val iconClose = inflated.findViewById<ImageView>(R.id.navio_final_icon)
        if (closeIconResId > 0) {
            iconClose.setImageResource(closeIconResId)
        }

        val marginBottomPx =
            (MARGIN_BOTTOM_DP * context.resources.displayMetrics.density + 0.5f).toInt()
        val params = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
        ).apply {
            gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
            bottomMargin = marginBottomPx
        }

        inflated.elevation = 100f
        popupView = inflated
        val mainHandler = handler ?: Handler(Looper.getMainLooper()).also { handler = it }
        mainHandler.removeCallbacks(dismissRunnable)
        root.addView(inflated, params)
        mainHandler.postDelayed(dismissRunnable, DURATION_MS)
        iconClose.setOnClickListener {
            mainHandler.removeCallbacks(dismissRunnable)
            dismissPopUp()
        }
    }

    private fun dismissPopUp() {
        val view = popupView ?: return
        (view.parent as? ViewGroup)?.removeView(view)
        popupView = null
    }
}
