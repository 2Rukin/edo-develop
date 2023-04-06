package com.education.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import model.enum_.ReceiptMethod;
import model.enum_.Status;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.HashSet;

/**
 * Сущность, описывающая обращение гражданина
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Table(name = "appeal")
public class Appeal extends BaseEntity {

    /**
     * Дата создания обращения
     */
    @Column(name = "creation_date")
    private ZonedDateTime creationDate;

    /**
     * Дата отработанного обращения
     */
    @Column(name = "archived_date")
    private ZonedDateTime archivedDate;

    /**
     * Номер обращения
     */
    @Column(name = "number")
    private String number;

    /**
     * Заголовок обращения
     */
    @Column(name = "annotation")
    private String annotation;

    /**
     * Поле "appealsStatus" - статус обращения
     * (Новое, Зарегистрировано, На рассмотрении, В работе, Архив, Ожидает отправки, Выполнено)
     */
    @Column(name = "appeals_status")
    @Enumerated(EnumType.STRING)
    private Status appealsStatus;

    /**
     * Поле "sendingMethod" - способ получения обращения
     * (На бумаге, Через электронную почту, Лично в приемной, По телефону)
     */
    @Column(name = "appeals_receipt_method")
    @Enumerated(EnumType.STRING)
    private ReceiptMethod sendingMethod;

    /**
     * Обработчик обращения (несколько Employee - подписанты)
     * те из работников, кто будут рассматривать обращение недовольного гражданина
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "appeal_signer",
            joinColumns = @JoinColumn(name = "appeal_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"))
    private Collection<Employee> signer = new HashSet<>();

    /**
     * Создатель обращения (Не автор!)
     * грубо говоря - работник, который принял обращение недовольного гражданина
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    private Employee creator;

    /**
     * Несколько Employee "в копию" рассылки обращения
     * они тоже посмотрят обращение, но подписи их нам не нужны
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "appeal_addressee",
            joinColumns = @JoinColumn(name = "appeal_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"))
    private Collection<Employee> addressee = new HashSet<>();

    /**
     * Несколько "authors" - автор, соавторы обращения
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "appeal_authors",
            joinColumns = @JoinColumn(name = "appeal_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"))
    private Collection<Author> authors = new HashSet<>();

    /**
     * Несколько "questions" - несколько вопросов
     * внутри гневные вопросы недовольных граждан
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "appeal_question",
            joinColumns = @JoinColumn(name = "appeal_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "question_id", referencedColumnName = "id"))
    private Collection<Question> questions = new HashSet<>();

    /**
     * Номенклатура
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nomenclature_id", referencedColumnName = "id")
    private Nomenclature nomenclature;

    /**
     * Несколько "file" - несколько файлов
     * внутри гневное письмо/письма недовольных граждан
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "appeal_filepool",
            joinColumns = @JoinColumn(name = "appeal_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "filepool_id", referencedColumnName = "id"))
    private Collection<FilePool> file = new HashSet<>();

}