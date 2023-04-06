package model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.HashSet;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@ApiModel(value = "Лист согласования")
public class ApprovalDto implements Serializable {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "Обращение, за которым закреплён лист согласования")
    private AppealDto appeal;

    @ApiModelProperty(value = "Блоки с участниками")
    private Collection<ApprovalBlockDto> participantApprovalBlocks = new HashSet<>();

    @ApiModelProperty(value = "Инициатор(участник с типом инициатор)")
    private MemberDto initiator;

    @ApiModelProperty(value = "Комментарий инициатора")
    private String initiatorComment;

    @ApiModelProperty(value = "Дата создания листа согласования")
    private ZonedDateTime creationDate;

    @ApiModelProperty(value = "Дата направления на согласование")
    private ZonedDateTime dateSentForApproval;

    @ApiModelProperty(value = "Дата подписания")
    private ZonedDateTime signingDate;

    @ApiModelProperty(value = "Дата возврата на доработку")
    private ZonedDateTime returnDateForRevision;

    @ApiModelProperty(value = "Дата обработки возврата")
    private ZonedDateTime returnProcessingDate;

    @ApiModelProperty(value = "Дата архивации листа согласования")
    private ZonedDateTime archivedDate;

    @ApiModelProperty(value = "Текущий участник листа согласования")
    private MemberDto currentMember;

    @ApiModelProperty(value = "Блоки с Подписантами")
    private Collection<ApprovalBlockDto> signatoryApprovalBlocks = new HashSet<>();

}
