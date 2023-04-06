package com.education.util;

import model.dto.EmployeeDto;

public class EmployeeBuilder {

    /**
     * Метод заглушка для генерации id, далее id будет из сессии. Временное решение
     *
     */
    public static EmployeeDto getCurrentEmployee (){
        return EmployeeDto.builder().id(2L).build();
    }
}
