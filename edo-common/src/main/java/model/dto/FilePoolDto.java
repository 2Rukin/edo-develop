package model.dto;

import io.swagger.annotations.ApiModelProperty;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * @author Nadezhda Pupina
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@ApiModel(value = "Класс-обертка для взаимодействия с иными модулями и внешними системами")
public class FilePoolDto implements Serializable {

    @ApiModelProperty(value = "id файла", name = "id", dataType = "Long", example = "1")
    private Long id;

    @ApiModelProperty(value = "Ключ для получения файла из хранилища", name = "storageFileId", dataType = "UUID", example = "e58ed763-928c-4155-bee9-fdbaaadc15f3")
    private UUID storageFileId;

    @ApiModelProperty(value = "Имя обращения", name = "name", dataType = "String", example = "Dina")
    private String name;

    @ApiModelProperty(value = "Формат файла", name = "extension", dataType = "String", example = "pdf")
    private String extension;

    @ApiModelProperty(value = "Размер обращения", name = "size", dataType = "Long", example = "34")
    private Long size;

    @ApiModelProperty(value = "Количество страниц", name = "pageCount", dataType = "Long", example = "3")
    private Long pageCount;

    @ApiModelProperty(value = "Дата создания", name = "uploadDate", dataType = "ZonedDateTime", example = "2021-03-23T16:43:32.010069453")
    private ZonedDateTime uploadDate;

    @ApiModelProperty(value = "Дата архивации", name = "archivedDate", dataType = "ZonedDateTime", example = "2021-03-26T16:43:32.010069453")
    private ZonedDateTime archivedDate;

    @ApiModelProperty(value = "id создателя файла", name = "creator", dataType = "EmployeeDTO", example = "3")
    private EmployeeDto creator;

}
