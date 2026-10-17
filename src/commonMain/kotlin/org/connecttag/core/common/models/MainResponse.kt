package org.connecttag.core.common.models

import kotlinx.serialization.Serializable

@Serializable
public data class ResponseError(
    public val code: Int? = null,
    public val message: String? = null,
)

@Serializable
public data class MainResponse<T>(
    public val result: T? = null,
    public val success: Boolean? = false,
    public val errors: ResponseError? = null,
    public val messages: String? = null,
)

@Serializable
public data class MainResponseList<T>(
    public val result: List<T>? = null,
    public val success: Boolean? = false,
    public val errors: ResponseError? = null,
    public val messages: String? = null,
)

public class NetworkResponseException(
    override val message: String,
    public val errorCode: Int? = null,
) : IllegalStateException(message)

public fun <T, R> MainResponse<T>.mapResult(transform: (T) -> R): MainResponse<R> {
    return MainResponse(
        result = result?.let(transform),
        success = success,
        errors = errors,
        messages = messages,
    )
}

public fun <T> MainResponse<T>.toResult(
    allowNullResult: Boolean = false,
    emptyMessage: String = "Empty response result",
): Result<T?> {
    errors?.let { error ->
        return Result.Failure(
            throwable = NetworkResponseException(
                message = error.message ?: messages ?: "Request failed",
                errorCode = error.code,
            ),
            message = error.message ?: messages.orEmpty(),
            code = error.code,
        )
    }

    if (success == false) {
        return Result.Failure(
            throwable = NetworkResponseException(messages ?: "Request failed"),
            message = messages.orEmpty(),
        )
    }

    if (result == null && !allowNullResult) {
        return Result.Failure(
            throwable = NetworkResponseException(messages ?: emptyMessage),
            message = messages ?: emptyMessage,
        )
    }

    return Result.Success(result)
}

public fun <T> MainResponseList<T>.toResult(
    allowNullResult: Boolean = false,
    emptyMessage: String = "Empty response result",
): Result<List<T>?> {
    errors?.let { error ->
        return Result.Failure(
            throwable = NetworkResponseException(
                message = error.message ?: messages ?: "Request failed",
                errorCode = error.code,
            ),
            message = error.message ?: messages.orEmpty(),
            code = error.code,
        )
    }

    if (success == false) {
        return Result.Failure(
            throwable = NetworkResponseException(messages ?: "Request failed"),
            message = messages.orEmpty(),
        )
    }

    if (result == null && !allowNullResult) {
        return Result.Failure(
            throwable = NetworkResponseException(messages ?: emptyMessage),
            message = messages ?: emptyMessage,
        )
    }

    return Result.Success(result)
}
