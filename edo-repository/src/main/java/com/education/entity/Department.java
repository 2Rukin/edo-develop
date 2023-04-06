package com.education.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

import static jakarta.persistence.FetchType.LAZY;

/**
 * @author Usolkin Dmitry
 * Сущность Department расширяет сущность BaseEntity
 */
@Entity(name = "department")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "department")
@SuperBuilder
@ToString
public class Department extends BaseEntity {

    /* Поле короткое имя*/
    @Column(name = "short_name")
    private String shortName;

    /* Поле полное имя*/
    @Column(name = "full_name")
    private String fullName;

    /* Поле адрес*/
    @OneToOne(fetch = LAZY)
    @JoinColumn (name = "address")
    private Address address;

    /* Поле с внешним идентификатором, у себя не создаем сущность,
    скачиваем из чужого хранилища)
     */
    @Column(name = "external_id")
    private String externalId;

    /* Поле с номером телефона*/
    @Column(name = "phone")
    private String phone;

    /* Поле с вышестоящим департаментом */
    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "department")
    private Department department;

    /* Поле с датой создания */
    @Column(name = "creation_date")
    private ZonedDateTime creationDate;

    /* Поле с датой архивирования */
    @Column(name = "archived_date")
    private ZonedDateTime archivedDate;

}
