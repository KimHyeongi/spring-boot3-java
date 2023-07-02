package com.tistory.eclipse4j.domain.jpa.db1.service;

import com.tistory.eclipse4j.domain.jpa.ThreadSleep;
import com.tistory.eclipse4j.domain.jpa.db1.entity.Reservation;
import com.tistory.eclipse4j.domain.jpa.db1.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.*;

@Service
@RequiredArgsConstructor
public class ReservationTxService {

	private final ReservationRepository reservationRepository;

	@Transactional(readOnly = false)
	public Reservation findAndUpdate(Long id, Long productId, long ms) {
		Reservation reservation = reservationRepository.findById(id).orElseThrow(()->new EntityNotFoundException(String.valueOf(id)));
		ThreadSleep.sleep(ms);
		reservation.updateProductId(productId);
		reservationRepository.save(reservation);
		return reservation;
	}

	@Transactional(readOnly = true)
	public Reservation findById(Long id, long ms) {
		Reservation reservation = reservationRepository.findById(id).orElseThrow(()->new EntityNotFoundException(String.valueOf(id)));
		ThreadSleep.sleep(ms);
		return reservation;
	}
}
