/*
 * Copyright 2014-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

package org.jetbrains.dokka.analysis.kotlin.internal

import org.jetbrains.dokka.DokkaConfiguration
import org.jetbrains.dokka.InternalDokkaApi

@InternalDokkaApi
interface SampleProviderFactory {
    /**
     * [SampleProvider] is a short-lived closeable instance.
     * It assumes that [SampleProvider] scope of use is not big.
     * Otherwise, it can lead to high memory consumption / leaks during Dokka running.
     */
    fun build(): SampleProvider
}

/**
 * It is closeable.
 * Otherwise, there is a chance of high memory consumption / leak.
 * In general case, it creates a separate project to analysis samples directories.
 */
@InternalDokkaApi
interface SampleProvider: AutoCloseable {
    class SampleSnippet(val imports: String, val body:String)


    /**
     * @return [SampleSnippet] or null if it has not found by [fqLink]
     */
    fun getSample(sourceSet: DokkaConfiguration.DokkaSourceSet, fqLink: String): SampleSnippet?
}