package com.tistory.eclipse4j.app.api.company.data;

import com.tistory.eclipse4j.domain.jpa.db1.entity.Company;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class CompanyInfo implements Serializable {

    private Long id;

    private String name;

    public static CompanyInfo of(Company company){
        return CompanyInfo.builder()
                .id(company.getId())
                .name(company.getName())
                .build();
    }
}
