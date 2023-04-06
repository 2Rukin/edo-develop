package model.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import model.enum_.ReceiptMethod;
import model.enum_.Status;

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
@ApiModel(value = "Обращение граждан")
public class AllAppealDto implements Serializable {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "Дата создания обращения")
    private ZonedDateTime creationDate;

    @ApiModelProperty(value = "Создатель обращения")
    private EmployeeDto creator;

    @ApiModelProperty(value = "Несколько вопросов")
    private Collection<QuestionDto> questions = new HashSet<>();


}
