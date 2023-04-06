package model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import model.enum_.ResolutionType;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@ApiModel(value = "Класс-обертка класса Resolution для передачи данных между модулями и классами")
public class ResolutionDto implements Serializable {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "Дата создания")
    private ZonedDateTime creationDate;

    @ApiModelProperty(value = "Дата архивации")
    private ZonedDateTime archivedDate;

    @ApiModelProperty(value = "Дата последнего действия")
    private ZonedDateTime lastActionDate;

    @ApiModelProperty(value = "Вид - резолюция, направление или запрос")
    private ResolutionType type;

    @ApiModelProperty(value = "Создатель")
    private EmployeeDto creator;

    @ApiModelProperty(value = "Принявший резолюцию")
    private EmployeeDto signer;

    @ApiModelProperty(value = "Исполнители резолюции")
    private List<EmployeeDto> executor;

    @ApiModelProperty(value = "Контролер исполнения")
    private EmployeeDto curator;

    @ApiModelProperty(value = "Серийный номер")
    private String serialNumber;

    @ApiModelProperty(value = "Черновик")
    private Boolean isDraft;

    @ApiModelProperty(value = "Задача")
    private String task;

    @ApiModelProperty(value = "Вопрос от граждан")
    private QuestionDto question;

}