package com.tistory.eclipse4j.domain.jpa.db1.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import jakarta.persistence.*;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class AuditingEntity {

	private static final String SYSTEM_USER = "SYSTEM";

	@Column(name = "deleted", nullable = false)
	private boolean deleted = false;

	@CreatedBy
	@Column(name = "createdBy", nullable = false, length = 50)
	private String createdBy;

	@CreatedDate
	@Column(name = "createdAt", nullable = false)
	private Date createdAt;

	@LastModifiedBy
	@Column(name = "modifiedBy", length = 50)
	private String modifiedBy;

	@LastModifiedDate
	@Column(name = "modifiedAt")
	private Date modifiedAt;

	@PrePersist
	public void prePersist() {
		this.createdBy = SYSTEM_USER;
		this.modifiedBy = SYSTEM_USER;
	}

	@PreUpdate
	public void preUpdate() {
		this.modifiedBy = SYSTEM_USER;
	}

	@PreRemove
	public void preDelete() {
		setDeleted(true);
	}
}
