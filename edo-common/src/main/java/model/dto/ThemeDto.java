package model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.ZonedDateTime;

/**
 * @author AlexeySpiridonov
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ApiModel(value = "Тема обращения")
public class ThemeDto {

    @ApiModelProperty(value = "номер id")
    private Long id;

    @ApiModelProperty(value = "название темы")
    private String name;

    @ApiModelProperty(value = "дата архивации")
    private ZonedDateTime archivedDate;

    @ApiModelProperty(value = "дата создания")
    private ZonedDateTime creationDate;

    @ApiModelProperty(value = "идентификатор темы")
    private String code;

    @ApiModelProperty(value = "родительская тема")
    private ThemeDto parentTheme;
}
