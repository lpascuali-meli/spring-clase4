package com.spring.links.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkDto {
    private int id;
    private String url;
    private String password;
    private int accessCounter;
    private boolean isActivated;
}
