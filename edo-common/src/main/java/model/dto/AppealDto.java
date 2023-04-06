package model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.Valid;
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
public class AppealDto implements Serializable {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "Дата создания обращения")
    private ZonedDateTime creationDate;

    @ApiModelProperty(value = "Дата отработанного обращения")
    private ZonedDateTime archivedDate;

    @ApiModelProperty(value = "Номер обращения")
    private String number;

    @ApiModelProperty(value = "Заголовок обращения")
    private String annotation;

    @ApiModelProperty(value = "Статус обращения")
    private Status appealsStatus;

    @ApiModelProperty(value = "Способ получения обращения")
    private ReceiptMethod sendingMethod;

    @ApiModelProperty(value = "Подписант(ы) обращения")
    private Collection<EmployeeDto> signer;

    @ApiModelProperty(value = "Создатель обращения")
    private EmployeeDto creator;

    @ApiModelProperty(value = "Отправить им обращение тоже (в копию)")
    private Collection<EmployeeDto> addressee;
@Valid
    @ApiModelProperty(value = "Автор, соавторы обращения")
    private Collection<AuthorDto> authors = new HashSet<>();

    @ApiModelProperty(value = "Несколько вопросов")
    private Collection<QuestionDto> questions = new HashSet<>();

    @ApiModelProperty(value = "Номенклатура")
    private NomenclatureDto nomenclature;

    @ApiModelProperty(value = "Несколько файлов")
    private Collection<FilePoolDto> file = new HashSet<>();

}
