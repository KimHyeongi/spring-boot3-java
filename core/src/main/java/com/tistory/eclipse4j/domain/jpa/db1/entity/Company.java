package com.tistory.eclipse4j.domain.jpa.db1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@SqlResultSetMappings({
        @SqlResultSetMapping(name = "SimpleCompany",
                classes = @ConstructorResult(targetClass = SimpleCompany.class,
                        columns = {
                                @ColumnResult(name = "code", type = String.class),
                                @ColumnResult(name = "name", type = String.class)
                        }))
})
@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Audited
@EntityListeners(AuditingEntityListener.class)
@Table(name = "company")
public class Company extends AuditingEntity implements Serializable {

    public Company(final Long id) {
        this.id = id;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @NotAudited
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Employee> employees;

    @Column(name = "streetAddress")
    private String streetAddress;

    public void updateCompanyName(String name) {
        this.name = name;
    }

    public void updateCompanyCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Company company = (Company) o;
        return id != null && Objects.equals(id, company.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
