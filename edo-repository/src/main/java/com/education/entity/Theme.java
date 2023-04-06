package com.education.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

/**
 * @author AlexeySpiridonov
 * Сущность описывающая тему обращения граждан
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Table(name = "theme")
public class Theme extends BaseEntity {

    /**
     * Название темы
     */
    @Column(name = "name")
    private String name;

    /**
     *  Дата архивации
     */
    @Column(name = "archived_date")
    private ZonedDateTime archivedDate;

    /**
     * Дата создания
     */
    @Column(name = "creation_date")
    private ZonedDateTime creationDate;

    /**
     * Номер темы (код)
     */
    @Column(name = "code")
    private String code;

    /**
     *  связка с entity Theme
    * */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_theme")
    private Theme parentTheme;
}