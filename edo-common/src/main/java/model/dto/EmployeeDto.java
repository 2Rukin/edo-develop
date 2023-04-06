package model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "объект для передачи данных")
@ToString
public class EmployeeDto implements Serializable {
    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "Имя")
    private String firstName;

    @ApiModelProperty(value = "Фамилия")
    private String lastName;

    @ApiModelProperty(value = "Отчество")
    private String middleName;

    @ApiModelProperty(value = "Адрес")
    private AddressDto address;

    @ApiModelProperty(value = "ФИО в дательном падеже")
    private String fioDative;

    @ApiModelProperty(value = "ФИО в именительном падеже")
    private String fioNominative;

    @ApiModelProperty(value = "ФИО в родительном падеже")
    private String fioGenitive;

    @ApiModelProperty(value = "Внешний индификатор, который будем получать из чужого хранилища")
    private String externalId;

    @ApiModelProperty(value = "Номер телефона сотовый")
    private String phone;

    @ApiModelProperty(value = "Рабочий номер телефона")
    private String workPhone;

    @ApiModelProperty(value = "Дата рождения")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @ApiModelProperty(value = "Имя пользователя")
    private String username;

    @ApiModelProperty(value = "Дата создания")
    private ZonedDateTime creationDate;

    @ApiModelProperty(value = "Дата архивации")
    private ZonedDateTime archivedDate;

    @ApiModelProperty(value = "Департамент")
    private DepartmentDto department;

    @ApiModelProperty(value = "Настройки оповещений")
    private NotificationDto notificationDto;
}
