package model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@ApiModel(value = "Класс-обертка для взаимодействия с иными модулями и внешними системами")
public class QuestionDto implements Serializable {

    @ApiModelProperty(value = "id вопроса", name = "id", dataType = "Long", example = "e58ed763-928c-4155-bee9-fdbaaadc15f3")
    private Long id;

    @ApiModelProperty(value = "Дата создания", name = "creationDate", dataType = "ZonedDateTime", example = "2021-03-23T16:43:32.010069453")
    private ZonedDateTime creationDate;

    @ApiModelProperty(value = "Дата архивации", name = "archivedDate", dataType = "ZonedDateTime", example = "2021-03-25T16:43:32.010069453")
    private ZonedDateTime archivedDate;

    @ApiModelProperty(value = "Краткое содержание вопроса", name = "summary", dataType = "String",
            example = "Как поднять настроение программисту")
    private String summary;

    @ApiModelProperty(value = "Тема")
    private ThemeDto theme;

}
