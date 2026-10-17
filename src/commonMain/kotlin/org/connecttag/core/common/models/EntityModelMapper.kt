package org.connecttag.core.common.models

public interface EntityModelMapper<Entity, Model> {
    public fun mapFromEntity(entity: Entity): Model

    public fun entityFromModel(model: Model): Entity
}

