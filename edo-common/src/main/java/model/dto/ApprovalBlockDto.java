package model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import model.enum_.ApprovalBlockType;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@ApiModel(value = "Блок c участниками или подписантами согласования")
public class ApprovalBlockDto implements Serializable {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "Тип блока листа согласования")
    private ApprovalBlockType type;

    @ApiModelProperty(value = "Участник перенаправивший лист согласования")
    private MemberDto memberWhoRedirectedApproval;

    @ApiModelProperty(value = "Номер по порядку согласования и порядку отображения на UI")
    private int ordinalNumber;

    @ApiModelProperty(value = "Участники (пустой, если это блок с подписантами)")
    private Collection<MemberDto> participants = new HashSet<>();

    @ApiModelProperty(value = "Подписанты (пустой, если это блок с участниками)")
    private Collection<MemberDto> signatories = new HashSet<>();

}
