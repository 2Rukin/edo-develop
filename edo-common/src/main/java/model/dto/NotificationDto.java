package model.dto;
import model.enum_.NotificationType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@ApiModel("Объект хранения элементов")


public class NotificationDto {
    @ApiModelProperty("ID настроек оповещения")
    private Long id;

    @ApiModelProperty("Тип оповещения")
    private NotificationType type;

}
