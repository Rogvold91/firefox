/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.fenix.components.metrics

import android.app.Application
import androidx.annotation.VisibleForTesting
import mozilla.components.lib.crash.CrashReporter
import org.mozilla.fenix.utils.Settings

class AdjustMetricsService(
    @Suppress("UNUSED_PARAMETER") application: Application,
    @Suppress("UNUSED_PARAMETER") storage: MetricsStorage,
    @Suppress("UNUSED_PARAMETER") crashReporter: CrashReporter,
) : MetricsService {
    override val type = MetricServiceType.Marketing

    override fun start() = Unit

    override fun stop() = Unit

    override fun track(event: Event) = Unit

    override fun shouldTrack(event: Event): Boolean = false

    companion object {
        @VisibleForTesting
        internal fun alreadyKnown(settings: Settings): Boolean {
            return settings.adjustCampaignId.isNotEmpty() || settings.adjustNetwork.isNotEmpty() ||
                settings.adjustCreative.isNotEmpty() || settings.adjustAdGroup.isNotEmpty()
        }
    }
}
