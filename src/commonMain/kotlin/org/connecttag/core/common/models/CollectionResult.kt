package org.connecttag.core.common.models

public sealed interface CollectionResult<out T> {
    public data class Available<out T>(
        public val value: T,
    ) : CollectionResult<T>

    public data class PermissionDenied(
        public val permissions: List<String>,
        public val feature: String? = null,
    ) : CollectionResult<Nothing>

    public data object Unsupported : CollectionResult<Nothing>

    public data class Error(
        public val cause: Throwable,
    ) : CollectionResult<Nothing>

    public fun getOrNull(): T? = when (this) {
        is Available -> value
        is PermissionDenied,
        is Unsupported,
        is Error -> null
    }
}
