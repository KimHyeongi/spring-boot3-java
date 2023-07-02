package com.tistory.eclipse4j.domain.jpa.db1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "reservation")
public class Reservation extends AuditingEntity implements Serializable{

	public Reservation(Long id) {
		this.id = id;
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "customerId")
	private Long customerId;

	@Column(name = "productId")
	private Long productId;

	@Version
	private Long version;

	public void updateProductId(Long productId) {
		this.productId = productId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Reservation that = (Reservation) o;
		return id != null && Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

}
