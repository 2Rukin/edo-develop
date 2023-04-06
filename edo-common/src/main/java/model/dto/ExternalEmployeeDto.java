package model.dto;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jdk.jfr.Registered;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.ZonedDateTime;

/**
 * @author Nadezhda Pupina and Usolkin Dmitry
 *
 */
@Getter
@Setter
@ApiModel(value = "Данные о новом пользователе")
@ToString
public class ExternalEmployeeDto {

        @ApiModelProperty(value = "Пол")
        private String gender;
        @ApiModelProperty(value = "Имя")
        private  Name name;
        @ApiModelProperty(value = "Адрес")
        private Location location;
        @ApiModelProperty(value = "Почта")
        private String email;
        @ApiModelProperty(value = "Поле с классом для аутенитфикации и авторизации")
        private Login login;
        @ApiModelProperty(value = "Возраст и год рождения")
        private Date dob;
        @ApiModelProperty(value = "Информация о регистрации")
        private Regist registered ;
        @ApiModelProperty(value = "Номер телефона сотовый")
        private String phone;
        @ApiModelProperty(value = "Номер ячейки")
        private String cell;
        @ApiModelProperty(value = "Идентификатор")
        private Id id;
        @ApiModelProperty(value = "Изображение")
        private Picture picture;
        @ApiModelProperty(value = "Преобразование сетевых адресов")
        private String nat;
        @ApiModelProperty(value = "Компания")
        private Company company;
        @ApiModelProperty(value = "Удален")
        @JsonAlias(value = "is_deleted")
        private boolean isDelete;
        @Getter
        @Setter
        @ApiModel(value = "Класс для поля name, в котором присутствуют поля ФИО")
         public static class Name {
                private String last;
                private String middle;
                private String first;
        }
        @Getter
        @Setter
        @ApiModel(value = "Класс для поля street, в котором присутствуют поля номера улицы и имени")
        @ToString
        public static  class Location {
                private Street street;
                private String city;
                private String state;
                private String country;
                private Integer postcode;
                private Coordinates coordinates;
                @JsonAlias(value = "timezone")
                private CustTimeZone timeZone;
                @Getter
                @Setter
                @ApiModel(value = "Класс для поля coordinates , в котором присутсвуют широта и долгота ")
                @ToString
                public static  class Coordinates {
                        private String latitude;
                        private String longitude;

                }
                @Getter
                @Setter
                @ApiModel(value = "Класс для поля street, в котором присутсвуют поля номера улицы и имени")
                public class  Street {
                        private Long number;
                        private String name;
                }
                @Getter
                @Setter
                @ApiModel(value = "Часовой пояс и страна")
                public static class CustTimeZone {
                        private String offset;
                        private String description;
                }

        }

        @Getter
        @Setter
        @ApiModel(value = "Информация по аутентификации и авторизации")
        public static  class Login {
                private String uuid;
                private String username;
                private String password;
                private String salt;
                private String md5;
                private String sha1;
                private String sha256;

        }
        @Getter
        @Setter
        @ApiModel(value = "Время рождения и возраст")
        public static  class Date {
                @JsonFormat(pattern="yyyy-MM-dd")
                private LocalDate date;
                private Integer age;
        }
        @Getter
        @Setter
        @ApiModel(value = "Регистрация")
        private static class Regist {
                private String date;
                private Integer age;

        }
        @Getter
        @Setter
        @ApiModel(value = "Индетификатор")
        public static class Id {
                private String name;
                private String value;
        }
        @Getter
        @Setter
        @ApiModel(value = "Картинка")
        public static class Picture {
                private String large;
                private String medium;
                private String thumbnail;
        }
        @Getter
        @Setter
        @ApiModel(value = "Компания")
        public static  class Company {
                private Id id;
                private String name;
                private Location location;
                @JsonAlias(value = "is_deleted")
                private boolean isDelete;
        }
}
