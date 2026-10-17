package org.connecttag.core.common.models

public sealed class UiState<out T> {
    public data object Idle : UiState<Nothing>()
    public data object Loading : UiState<Nothing>()
    public data class Success<out T>(public val data: T) : UiState<T>()
    public data class Error(public val message: String) : UiState<Nothing>()
}

