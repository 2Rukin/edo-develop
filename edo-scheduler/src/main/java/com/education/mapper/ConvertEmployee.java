package com.education.mapper;


import model.dto.AddressDto;
import model.dto.DepartmentDto;
import model.dto.EmployeeDto;
import model.dto.ExternalEmployeeDto;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author Usolkin Dmitry
 * Конвертация ExternalEmployeeDto в EmployeeDto
 */
@Component
public class ConvertEmployee {

    public static Collection<EmployeeDto> toDto(Collection<ExternalEmployeeDto> externalEmployeeDtos) {
        return externalEmployeeDtos.stream().map(ConvertEmployee::toDto).collect(Collectors.toList());
    }

    public static EmployeeDto toDto(ExternalEmployeeDto externalEmployeeDto) {
        EmployeeDto employeeDto = EmployeeDto.builder()
                .firstName(externalEmployeeDto.getName().getFirst())
                .lastName(externalEmployeeDto.getName().getLast())
                .middleName(externalEmployeeDto.getName().getMiddle())
                .address(addressToDto(externalEmployeeDto.getLocation()).build())
                .phone(externalEmployeeDto.getPhone())
                .workPhone(externalEmployeeDto.getCell())
                .birthDate(externalEmployeeDto.getDob().getDate())
                .username(externalEmployeeDto.getLogin().getUsername())
                .externalId(externalEmployeeDto.getId().getValue())
                .department(departmentToDto(externalEmployeeDto.getCompany()).phone(externalEmployeeDto.getPhone()).build())
                .archivedDate(externalEmployeeDto.isDelete() ? ZonedDateTime.now() : null)
                .build();
        /*Проверяем если компания удалена, ставим дату архивации компании и сотрудника*/
        if (externalEmployeeDto.getCompany().isDelete()) {
            employeeDto.getDepartment().setArchivedDate(ZonedDateTime.now());
            employeeDto.setArchivedDate(ZonedDateTime.now());
        }
        return  employeeDto;
    }

    private static DepartmentDto.DepartmentDtoBuilder departmentToDto(ExternalEmployeeDto.Company company) {
        return DepartmentDto.builder()
                .shortName(company.getName())
                .fullName(company.getName())
                .address(addressToDto(company.getLocation()).build())
                .externalId(company.getId().getValue()); // build without phone
    }

    private static AddressDto.AddressDtoBuilder addressToDto(ExternalEmployeeDto.Location location) {
        return AddressDto.builder()
                .street(location.getStreet().getName())
                .index(location.getPostcode())
                .city(location.getCity())
                .region(location.getState())
                .country(location.getCountry())
                .latitude(location.getCoordinates().getLatitude())
                .longitude(location.getCoordinates().getLongitude());
    }
}
