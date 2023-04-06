package model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.ZonedDateTime;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(value = "Обьект для передачи данных")
public class DepartmentDto {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "краткое имя")
    private String shortName;

    @ApiModelProperty(value = "полное имя")
    private String fullName;

    @ApiModelProperty(value = "адрес")
    private AddressDto address;

    @ApiModelProperty(value = "номер телефона")
    private String phone;

    @ApiModelProperty(value = "внешний индификатор, который будем получать из чужого хранилища")
    private String externalId;

    @ApiModelProperty(value = "вышестоящий департамент")
    private DepartmentDto department;

    @ApiModelProperty(value = "дата создания")
    private ZonedDateTime creationDate;

    @ApiModelProperty(value = "дата архивации")
    private ZonedDateTime archivedDate;
}
