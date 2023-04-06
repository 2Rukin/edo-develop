package com.education.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;
import java.util.Collection;

/**
 * Сущность представляющая собой лист согласования
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Table(name = "approval")
public class Approval extends BaseEntity {

    /**
     * Содержит ссылку на обращение, за которым закреплён лист согласования
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appeal_id", referencedColumnName = "id")
    private Appeal appeal;

    /**
     * Блоки с участниками
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "approval_participantapprovalblock",
            joinColumns = @JoinColumn(name = "approval_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "approvalblock_id", referencedColumnName = "id"))
    private Collection<ApprovalBlock> participantApprovalBlocks;

    /**
     * Инициатор(участник с типом инициатор)
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "initiator_id", referencedColumnName = "id")
    private Member initiator;

    /**
     * Комментарий инициатора
     */
    @Column(name = "initiator_comment")
    private String initiatorComment;

    /**
     * Дата создания листа согласования
     */
    @Column(name = "creation_date")
    private ZonedDateTime creationDate;

    /**
     * Дата направления на согласование
     */
    @Column(name = "date_sent_for_approval")
    private ZonedDateTime dateSentForApproval;

    /**
     * Дата подписания
     */
    @Column(name = "signing_date")
    private ZonedDateTime signingDate;

    /**
     * Дата возврата на доработку
     */
    @Column(name = "return_date_for_revision")
    private ZonedDateTime returnDateForRevision;

    /**
     * Дата обработки возврата
     */
    @Column(name = "return_processing_date")
    private ZonedDateTime returnProcessingDate;

    /**
     * Дата архивации листа согласования
     */
    @Column(name = "archived_date")
    private ZonedDateTime archivedDate;

    /**
     * Текущий участник листа согласования
     */
    @JoinColumn(name = "current_member_id", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.LAZY)
    private Member currentMember;

    /**
     * Блоки с Подписантами
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "approval_signatoryapprovalblock",
            joinColumns = @JoinColumn(name = "approval_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "approvalblock_id", referencedColumnName = "id"))
    private Collection<ApprovalBlock> signatoryApprovalBlocks;

}
