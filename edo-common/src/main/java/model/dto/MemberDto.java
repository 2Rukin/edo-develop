package model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import model.enum_.MemberType;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@ApiModel(value = "Участник или подписант согласования")
public class MemberDto implements Serializable {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "Тип участника согласования")
    private MemberType type;

    @ApiModelProperty(value = "Дата создания участника")
    private ZonedDateTime creationDate;

    @ApiModelProperty(value = "Дата, до которой должно быть исполнено")
    private ZonedDateTime executionDate;

    @ApiModelProperty(value = "Дата получения")
    private ZonedDateTime dateOfReceiving;

    @ApiModelProperty(value = "Дата завершения действия")
    private ZonedDateTime endDate;

    @ApiModelProperty(value = "Номер по порядку согласования и порядку отображения на UI")
    private int ordinalNumber;

    @ApiModelProperty(value = "Сотрудник, который является участником")
    private EmployeeDto employee;

}
