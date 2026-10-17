package org.connecttag.core.common.models

public fun interface RemoteDtoMapper<RemoteDto, Domain> {
    public fun toDomain(dto: RemoteDto): Domain
}

public fun interface LocalEntityMapper<Entity, Domain> {
    public fun toDomain(entity: Entity): Domain
}

public fun interface UiModelMapper<Domain, UiModel> {
    public fun toUiModel(domain: Domain): UiModel
}

