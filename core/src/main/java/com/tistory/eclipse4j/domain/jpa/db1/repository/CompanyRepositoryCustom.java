package com.tistory.eclipse4j.domain.jpa.db1.repository;

import com.tistory.eclipse4j.domain.jpa.db1.entity.SimpleCompany;

import java.util.List;

public interface CompanyRepositoryCustom {
    Long findMaxId();

    SimpleCompany findSimpleCompanyById(Long id);

    List<SimpleCompany> findSimpleCompanies();
}
