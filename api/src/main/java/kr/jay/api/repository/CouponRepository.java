package kr.jay.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.jay.api.domain.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
