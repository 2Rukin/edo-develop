package model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import model.enum_.Employment;

import java.io.Serializable;

/**
 * Дублирующее DTO "model.dto.AuthorDto" для сущности "Author"
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@ApiModel(value = "Автор обращения")
public class AuthorDto implements Serializable {

    @ApiModelProperty(value = "id автора")
    private Long id;
@NotNull
    @ApiModelProperty(value = "имя автора")
    private String firstName;

    @ApiModelProperty(value = "фамилия автора")
    private String lastName;

    @ApiModelProperty(value = "отчество автора")
    private String middleName;

    @ApiModelProperty(value = "адрес автора")
    private String address;

    @ApiModelProperty(value = "СНИЛС автора")
    private String snils;

    @ApiModelProperty(value = "номер телефона автора (мобильный)")
    private String mobilePhone;
    @Email(message = "полная хрень")
    @ApiModelProperty(value = "адрес электронной почты автора")
    private String email;

    @ApiModelProperty(value = "трудоустройство автора (Безработный, Работник, Учащийся)")
    private Employment employment;

    @ApiModelProperty(value = "ФИО автора в дательном падеже")
    private String fioDative;

    @ApiModelProperty(value = "ФИО автора в родительном падеже")
    private String fioGenitive;

    @ApiModelProperty(value = "ФИО автора в именительном падеже")
    private String fioNominative;

}