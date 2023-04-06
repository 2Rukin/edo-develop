package model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(value = "Объект для передачи данных")
@Builder
public class AddressDto {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "Полный адрес")
    private String fullAddress;

    @ApiModelProperty(value = "Название улицы")
    private String street;

    @ApiModelProperty(value = "Номер дома")
    private String house;

    @ApiModelProperty(value = "Почтовый индекс")
    private Integer index;

    @ApiModelProperty(value = "Корпус")
    private String housing;

    @ApiModelProperty(value = "Строение")
    private String building;

    @ApiModelProperty(value = "Город")
    private String city;

    @ApiModelProperty(value = "Регион")
    private String region;

    @ApiModelProperty(value = "Страна")
    private String country;

    @ApiModelProperty(value = "Квартира")
    private String flat;

    @ApiModelProperty(value = "долгота")
    private String longitude;

    @ApiModelProperty(value = "широта")
    private String latitude;
}
