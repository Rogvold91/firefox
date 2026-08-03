/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.fenix.utils

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.Context

/**
 * Displays the "add search widget" prompt for capable devices.
 *
 * @param activity the parent [Activity].
 */
fun maybeShowAddSearchWidgetPrompt(activity: Activity) = Unit

/**
 * Displays the "add search widget" prompt if the device supports it.
 *
 * This function checks if the current Android version is Oreo (API 26) or higher
 * and if the `AppWidgetManager` supports pinning app widgets. If both conditions are met,
 * it requests to pin the search widget.
 *
 * @param context [Context] used for the Android framework interactions.
 */
fun showAddSearchWidgetPromptIfSupported(context: Context) = Unit

/**
 * Checks whether the device is capable of displaying the "add search widget" prompt.
 */
fun canShowAddSearchWidgetPrompt(appWidgetManager: AppWidgetManager) = false
