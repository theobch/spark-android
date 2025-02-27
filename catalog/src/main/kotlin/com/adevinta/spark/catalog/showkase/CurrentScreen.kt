/*
 * Copyright (c) 2023 Adevinta
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.adevinta.spark.catalog.showkase

import android.os.Parcelable
import androidx.compose.runtime.MutableState
import kotlinx.parcelize.Parcelize

internal enum class CurrentScreen {
    COMPONENT_GROUPS,
    COMPONENTS_IN_A_GROUP,
    COMPONENT_STYLES,
    COMPONENT_DETAIL,
    CATEGORIES,
}

internal fun String?.insideGroup() = this == CurrentScreen.COMPONENTS_IN_A_GROUP.name

@Parcelize
internal data class ShowkaseBrowserScreenMetadata(
    val currentGroup: String? = null,
    val currentComponentName: String? = null,
    val currentComponentStyleName: String? = null,
    val currentComponentKey: String? = null,
    val isSearchActive: Boolean = false,
    val searchQuery: String? = null,
) : Parcelable

internal fun MutableState<ShowkaseBrowserScreenMetadata>.clear() {
    update {
        copy(
            isSearchActive = false,
            searchQuery = null,
            currentComponentKey = null,
            currentComponentName = null,
            currentComponentStyleName = null,
            currentGroup = null,
        )
    }
}

internal fun MutableState<ShowkaseBrowserScreenMetadata>.clearActiveSearch() {
    update {
        copy(
            isSearchActive = false,
            searchQuery = null,
        )
    }
}

internal fun <T> MutableState<T>.update(block: T.() -> T) {
    value = block(value)
}
