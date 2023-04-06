package com.education.publisher.approval;

import model.dto.ApprovalDto;

public interface ApprovalPublisher {
    void save(ApprovalDto approvalDto);
}