package com.mohanty.hibernatepractice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Publisher {
    private String code;
    private String publisherName;
}
