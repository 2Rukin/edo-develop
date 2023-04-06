package com.education.mapper;

import com.education.entity.BaseEntity;

import java.util.Collection;

/**
 * @author Usolkin Dmitry
 * Общий интерфейс для маппинга сущностей в дто и обратно
 */

public interface
AbstractMapper <Entity extends BaseEntity,Dto> {

    Dto toDto(Entity entity);

    Entity toEntity(Dto dto);

    Collection <Dto> toDto(Collection<Entity> entities);

    Collection<Entity> toEntity(Collection<Dto> dtoEntities);


}