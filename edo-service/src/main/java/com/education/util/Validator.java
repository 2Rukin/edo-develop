package com.education.util;

import lombok.NoArgsConstructor;
import model.dto.ApprovalBlockDto;
import model.dto.ApprovalDto;
import model.dto.MemberDto;
import model.enum_.ApprovalBlockType;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Класс для валидации DTO
 */
@Component
@NoArgsConstructor
public class Validator {

    /**
     * Проверяет ApprovalBlockDto
     */
    public void validateApprovalBlockDto(ApprovalBlockDto approvalBlockDto) {

        // Проверка на соответствие типу блока согласования
        if (approvalBlockDto.getType().equals(ApprovalBlockType.PARTICIPANT_BLOCK)) {

            // Проверка поля signatories на отсутствие участников
            if (approvalBlockDto.getSignatories().size() != 0) {
                throw new IllegalArgumentException("The participant approval block must be without an signatories");
            }

            // Проверка на отсутствие повторяющихся порядковых номеров у участников
            if (approvalBlockDto.getParticipants().stream()
                    .map(MemberDto::getOrdinalNumber)
                    .collect(Collectors.toSet())
                    .size() != approvalBlockDto.getParticipants().size()) {
                throw new IllegalArgumentException("Members cannot have the same ordinal number in the same block.");
            }

        } else if (approvalBlockDto.getType().equals(ApprovalBlockType.SIGNATORY_BLOCK)) {

            // Проверка поля participants на отсутствие участников
            if (approvalBlockDto.getParticipants().size() != 0) {
                throw new IllegalArgumentException("The signatory approval block must be without an participants");
            }

            // Проверка на отсутствие повторяющихся порядковых номеров у участников
            if (approvalBlockDto.getSignatories().stream()
                    .map(MemberDto::getOrdinalNumber)
                    .collect(Collectors.toSet())
                    .size() != approvalBlockDto.getSignatories().size()) {
                throw new IllegalArgumentException("Members cannot have the same ordinal number in the same block.");
            }

        } else {
            throw new IllegalArgumentException("The approval block must be of type");
        }
    }

    /**
     * Проверяет ApprovalDto
     */
    public void validateApprovalDto(ApprovalDto approvalDto) {

        // Проверка на соответствие типу
        if (approvalDto.getSignatoryApprovalBlocks().stream()
                .map(ApprovalBlockDto::getType)
                .toList().contains(ApprovalBlockType.PARTICIPANT_BLOCK)) {
            throw new IllegalArgumentException("Signatory approval blocks cannot be of type PARTICIPANT_BLOCK.");
        }
        if (approvalDto.getParticipantApprovalBlocks().stream()
                .map(ApprovalBlockDto::getType)
                .toList().contains(ApprovalBlockType.SIGNATORY_BLOCK)) {
            throw new IllegalArgumentException("Participant approval blocks cannot be of type SIGNATORY_BLOCK.");
        }

        // Проверка на отсутствие повторяющихся порядковых номеров у блоков с участниками
        if (approvalDto.getParticipantApprovalBlocks().stream()
                .map(ApprovalBlockDto::getOrdinalNumber)
                .collect(Collectors.toSet())
                .size() != approvalDto.getParticipantApprovalBlocks().size()) {
            throw new IllegalArgumentException("Participant approval blocks cannot have the same ordinal number in the same approval.");
        }

        // Проверка на отсутствие повторяющихся порядковых номеров у блоков с подписантами
        if (approvalDto.getSignatoryApprovalBlocks().stream()
                .map(ApprovalBlockDto::getOrdinalNumber)
                .collect(Collectors.toSet())
                .size() != approvalDto.getSignatoryApprovalBlocks().size()) {
            throw new IllegalArgumentException("Signatory approval blocks cannot have the same ordinal number in the same approval.");
        }
    }
}
