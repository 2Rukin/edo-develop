package model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.ZonedDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@ApiModel("Объект хранения элементов")
public class NomenclatureDto {

    @ApiModelProperty("ID записи")
    private Long id;

    @ApiModelProperty("дата создания")
    private ZonedDateTime creationDate;

    @ApiModelProperty("дата архивации")
    private ZonedDateTime archivedDate;

    @ApiModelProperty("Название шаблона для текущего элемента")
    private String template;

    @ApiModelProperty("Текущее значение элементов")
    private Long currentValue;

    @ApiModelProperty("Индекс элемента")
    private int index;

}
