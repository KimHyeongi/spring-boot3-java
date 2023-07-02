package com.tistory.eclipse4j.domain.jpa.db1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SimpleCompany implements Serializable {

    private String name;

    private String code;
}
