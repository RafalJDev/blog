package com.dawidkotarba.blog.model.dto.impl;

import com.dawidkotarba.blog.model.dto.InDto;
import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Builder
public class UserInDto implements InDto {

    @NotNull
    private Long id;
    @NotBlank
    private String username;
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @NotBlank
    private String password;
    private boolean enabled;
    @NotNull
    @NotEmpty
    private Set<Long> authorities;
}
