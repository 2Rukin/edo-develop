package com.education.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "employee")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

/**
 * @author George Kiladze
 * Сущность Employee расширяет сущность BaseEntity
 */
public class Employee extends BaseEntity {

    /**
     * firstName - Имя
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * lastName - Фамилия
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * middleName - Отчество
     */
    @Column(name = "middle_name")
    private String middleName;

    /**
     * address - Адрес
     */
    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "address")
    private Address address;

    /**
     * fioDative - ФИО в дательном падеже
     */
    @Column(name = "fio_dative")
    private String fioDative;

    /**
     * fioNominative - ФИО в именительном падеже
     */
    @Column(name = "fio_nominative")
    private String fioNominative;

    /**
     * fioGenitive - ФИО в родительном падеже
     */
    @Column(name = "fio_genitive")
    private String fioGenitive;

    /**
     * externalId - Внешний индификатор, который будем получать из чужого хранилища
     */
    @Column(name = "external_id")
    private String externalId;

    /**
     * phone - Номер телефона сотовый
     */
    @Column(name = "phone")
    private String phone;

    /**
     * workPhone - Рабочий номер телефона
     */
    @Column(name = "work_phone")
    private String workPhone;

    /**
     * birthDate - Дата рождения
     */
    @Column(name = "birth_date")
    private LocalDate birthDate;

    /**
     * username - Имя пользователя
     */
    @Column(name = "username")
    private String username;

    /**
    * notification - Настройки оповещения для пользователя
    */
    @JoinColumn(name = "notification_id")
    @OneToOne
    private Notification notification;

    /**
     * creationDate - Дата создания
     */
    @Column(name = "creation_date")
    private ZonedDateTime creationDate;

    /**
     * archivedDate - Дата архивации
     */
    @Column(name = "archived_date")
    private ZonedDateTime archivedDate;

    /**
     * department - Департамент
     */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "department")
    private Department department;

}
