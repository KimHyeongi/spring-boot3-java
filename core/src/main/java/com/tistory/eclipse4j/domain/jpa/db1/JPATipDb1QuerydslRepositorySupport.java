package com.tistory.eclipse4j.domain.jpa.db1;

import org.springframework.stereotype.Repository;


import jakarta.persistence.*;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.util.Assert;
import com.querydsl.core.dml.DeleteClause;
import com.querydsl.core.dml.UpdateClause;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.PathBuilderFactory;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAUpdateClause;

import jakarta.annotation.*;

@Repository
public abstract class JPATipDb1QuerydslRepositorySupport {

    private final PathBuilder<?> builder;

    private @Nullable EntityManager entityManager;
    private @Nullable Querydsl querydsl;

    public JPATipDb1QuerydslRepositorySupport(Class<?> domainClass) {
        Assert.notNull(domainClass, "Domain class must not be null!");
        this.builder = new PathBuilderFactory().create(domainClass);
    }

    @PersistenceContext(unitName = "jpadb1")
    public void setEntityManager(EntityManager entityManager) {
        Assert.notNull(entityManager, "EntityManager must not be null!");
        this.querydsl = new Querydsl(entityManager, builder);
        this.entityManager = entityManager;
    }

    @PostConstruct
    public void validate() {
        Assert.notNull(entityManager, "EntityManager must not be null!");
        Assert.notNull(querydsl, "Querydsl must not be null!");
    }

    @Nullable
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    protected JPQLQuery<Object> from(EntityPath<?>... paths) {
        return getRequiredQuerydsl().createQuery(paths);
    }

    protected <T> JPQLQuery<T> from(EntityPath<T> path) {
        return getRequiredQuerydsl().createQuery(path).select(path);
    }

    protected DeleteClause<JPADeleteClause> delete(EntityPath<?> path) {
        return new JPADeleteClause(getRequiredEntityManager(), path);
    }

    protected UpdateClause<JPAUpdateClause> update(EntityPath<?> path) {
        return new JPAUpdateClause(getRequiredEntityManager(), path);
    }

    @SuppressWarnings("unchecked")
    protected <T> PathBuilder<T> getBuilder() {
        return (PathBuilder<T>) builder;
    }

    @Nullable
    protected Querydsl getQuerydsl() {
        return this.querydsl;
    }

    private Querydsl getRequiredQuerydsl() {
        if (querydsl == null) {
            throw new IllegalStateException("Querydsl is null!");
        }
        return querydsl;
    }

    private EntityManager getRequiredEntityManager() {
        if (entityManager == null) {
            throw new IllegalStateException("EntityManager is null!");
        }
        return entityManager;
    }
}

