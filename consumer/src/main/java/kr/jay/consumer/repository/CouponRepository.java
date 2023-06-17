package kr.jay.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.jay.consumer.domain.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
