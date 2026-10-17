package org.connecttag.core.common.models

public sealed interface DomainError {
    public val message: String
    public val cause: Throwable?

    public data class Network(
        override val message: String,
        override val cause: Throwable? = null,
    ) : DomainError

    public data class Unauthorized(
        override val message: String,
        override val cause: Throwable? = null,
    ) : DomainError

    public data class Validation(
        override val message: String,
        public val code: Int? = null,
        override val cause: Throwable? = null,
    ) : DomainError

    public data class Server(
        override val message: String,
        public val code: Int? = null,
        override val cause: Throwable? = null,
    ) : DomainError

    public data class Unknown(
        override val message: String,
        override val cause: Throwable? = null,
    ) : DomainError

    public companion object {
        public fun fromThrowable(throwable: Throwable): DomainError =
            Unknown(message = throwable.message.orEmpty(), cause = throwable)
    }
}

