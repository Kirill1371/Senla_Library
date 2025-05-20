//package com.lib.library.api.dto;
//
//import lombok.Data;
//import java.time.LocalDate;
//
//@Data
//public class AuthorDto {
//    private Long id;
//    private String name;
//    private LocalDate birthDay;
//    private String country;
//    private Long staffId;
//}

package com.lib.library.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDate;

@Data
@Schema(description = "DTO для работы с авторами")
public class AuthorDto {
    @Schema(description = "Уникальный идентификатор автора", example = "1")
    private Long id;

    @Schema(description = "Полное имя автора", example = "Лев Толстой", required = true)
    private String name;

    @Schema(description = "Дата рождения", example = "1828-09-09")
    private LocalDate birthDay;

    @Schema(description = "Страна происхождения", example = "Россия")
    private String country;

    @Schema(description = "ID сотрудника, создавшего запись", example = "5")
    private Long staffId;
}