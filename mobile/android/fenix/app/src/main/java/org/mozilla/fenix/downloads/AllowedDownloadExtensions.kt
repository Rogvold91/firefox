/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.fenix.downloads

import java.util.Locale

object AllowedDownloadExtensions {
    val ALLOWED: Set<String> = setOf(
        ".aac", ".m4a", ".m4b", ".adts", ".adt",
        ".ogg", ".oga",
        ".mp3",
        ".wav", ".wave", ".pcm",
        ".flac",
        ".opus",
        ".mp4", ".m4v",
        ".avi",
        ".mov",
        ".mkv",
        ".webm",
        ".ogv", ".ogm",
        ".flv",
        ".wmv",
        ".jpeg", ".jpg",
        ".png",
        ".gif",
        ".bmp",
        ".tiff", ".tif",
    )

    fun isAllowedFileName(fileName: String?): Boolean {
        if (fileName.isNullOrEmpty()) return false
        var name = fileName
        val query = name.indexOf('?')
        if (query != -1) {
            name = name.substring(0, query)
        }
        val index = name.lastIndexOf('.')
        if (index == -1) return false
        return ALLOWED.contains(name.substring(index).lowercase(Locale.ENGLISH))
    }

    fun isAllowedDownload(fileName: String?, url: String?): Boolean {
        if (isAllowedFileName(fileName)) return true
        val urlName = url
            ?.substringAfterLast('/')
            ?.substringBefore('?')
        return isAllowedFileName(urlName)
    }
}
