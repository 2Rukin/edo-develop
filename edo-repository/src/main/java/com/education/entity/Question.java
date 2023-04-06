package com.education.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.HashSet;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * @author Nadezhda Pupina
 * Class Question отвечает за вопросы граждан, расширяет сущность BaseEntity
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
@JsonInclude(NON_NULL)
@Table(name = "question")
public class Question extends BaseEntity {

    /**
     * creationDate - question create date
     */
    @Column(name = "creation_date", nullable = false)
    private ZonedDateTime creationDate;

    /**
     * archivedDate - question archive date
     */
    @Column(name = "archived_date")
    private ZonedDateTime archivedDate;

    /**
     * summary - question description
     */
    @Column(name = "summary", nullable = false)
    private String summary;

    /**
     * Тема
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme_id", referencedColumnName = "id")
    private Theme theme;


}
