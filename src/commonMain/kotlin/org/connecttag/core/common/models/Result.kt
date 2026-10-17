package org.connecttag.core.common.models

public sealed class Result<out T> {
    public data class Success<out T>(public val data: T) : Result<T>()

    public data class Failure(
        public val throwable: Throwable,
        public val error: DomainError = DomainError.fromThrowable(throwable),
        public val message: String = throwable.message.orEmpty(),
        public val code: Int? = null,
    ) : Result<Nothing>()

    public val isSuccess: Boolean
        get() = this is Success

    public val isFailure: Boolean
        get() = this is Failure

    public fun getOrNull(): T? = when (this) {
        is Success -> data
        is Failure -> null
    }

    public fun getOrDefault(defaultValue: @UnsafeVariance T): T = when (this) {
        is Success -> data
        is Failure -> defaultValue
    }

    public fun exceptionOrNull(): Throwable? = when (this) {
        is Success -> null
        is Failure -> throwable
    }

    public inline fun onSuccess(block: (T) -> Unit): Result<T> = also {
        if (this is Success) block(data)
    }

    public inline fun onFailure(block: (Throwable) -> Unit): Result<T> = also {
        if (this is Failure) block(throwable)
    }
}

