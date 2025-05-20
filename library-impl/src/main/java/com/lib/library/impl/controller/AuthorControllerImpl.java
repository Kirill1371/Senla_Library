//package com.lib.library.impl.Controller;
//
//import com.lib.library.api.controller.AuthorController;
//import com.lib.library.api.dto.AuthorDto;
//import com.lib.library.db.entity.Staff;
//import com.lib.library.impl.Service.AuthorService;
//import com.lib.library.impl.Service.impl.UserDetailsServiceImpl;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.RestController;
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//public class AuthorControllerImpl implements AuthorController {
//
//    private final AuthorService service;
//    private final UserDetailsServiceImpl userDetailsService;
//
//    @Override
//    public AuthorDto create(AuthorDto dto) {
//        setCurrentStaffId(dto);
//        System.out.println("Received DTO: " + dto);
//        return service.create(dto);
//    }
//
//    @Override
//    public AuthorDto getById(Long id) {
//        return service.getById(id);
//    }
//
//    @Override
//    public List<AuthorDto> getAll() {
//        return service.getAll();
//    }
//
//    @Override
//    public AuthorDto update(Long id, AuthorDto dto) {
//        setCurrentStaffId(dto);
//        System.out.println("Received DTO: " + dto);
//        return service.update(id, dto);
//    }
//
//    @Override
//    public void delete(Long id) {
//        service.delete(id);
//    }
//
//    private void setCurrentStaffId(AuthorDto authorDto) {
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        Staff staff = userDetailsService.getStaffByEmail(username);
//        authorDto.setStaffId(staff.getId());
//    }
//}



package com.lib.library.impl.controller;

import com.lib.library.api.controller.AuthorController;
import com.lib.library.api.dto.AuthorDto;
import com.lib.library.db.entity.Staff;
import com.lib.library.impl.service.AuthorService;
import com.lib.library.impl.service.impl.UserDetailsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Author Management", description = "API для управления авторами книг")
@SecurityRequirement(name = "bearerAuth")
public class AuthorControllerImpl implements AuthorController {

    private final AuthorService service;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    @Operation(
            summary = "Создать нового автора",
            description = "Создает запись нового автора в системе. Требует аутентификации."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Автор успешно создан",
                    content = @Content(schema = @Schema(implementation = AuthorDto.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Неверные параметры запроса"),
            @ApiResponse(
                    responseCode = "401",
                    description = "Требуется аутентификация")
    })
    public AuthorDto create(
            @Parameter(description = "DTO автора для создания", required = true)
            AuthorDto dto) {
        setCurrentStaffId(dto);
        System.out.println("Received DTO: " + dto);
        return service.create(dto);
    }

    @Override
    @Operation(
            summary = "Получить автора по ID",
            description = "Возвращает информацию об авторе по его идентификатору"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Автор найден",
                    content = @Content(schema = @Schema(implementation = AuthorDto.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Автор не найден")
    })
    public AuthorDto getById(
            @Parameter(description = "ID автора", example = "1", required = true)
            Long id) {
        return service.getById(id);
    }

    @Override
    @Operation(
            summary = "Получить всех авторов",
            description = "Возвращает список всех авторов в системе"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Список авторов успешно получен",
            content = @Content(schema = @Schema(implementation = AuthorDto[].class))
    )
    public List<AuthorDto> getAll() {
        return service.getAll();
    }

    @Override
    @Operation(
            summary = "Обновить информацию об авторе",
            description = "Обновляет данные автора по его идентификатору. Требует аутентификации."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Автор успешно обновлен",
                    content = @Content(schema = @Schema(implementation = AuthorDto.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Неверные параметры запроса"),
            @ApiResponse(
                    responseCode = "401",
                    description = "Требуется аутентификация"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Автор не найден")
    })
    public AuthorDto update(
            @Parameter(description = "ID автора для обновления", example = "1", required = true)
            Long id,
            @Parameter(description = "Обновленные данные автора", required = true)
            AuthorDto dto) {
        setCurrentStaffId(dto);
        System.out.println("Received DTO: " + dto);
        return service.update(id, dto);
    }

    @Override
    @Operation(
            summary = "Удалить автора",
            description = "Удаляет автора из системы по его идентификатору. Требует аутентификации."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Автор успешно удален"),
            @ApiResponse(
                    responseCode = "401",
                    description = "Требуется аутентификация"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Автор не найден")
    })
    public void delete(
            @Parameter(description = "ID автора для удаления", example = "1", required = true)
            Long id) {
        service.delete(id);
    }

    private void setCurrentStaffId(AuthorDto authorDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Staff staff = userDetailsService.getStaffByEmail(username);
        authorDto.setStaffId(staff.getId());
    }
}