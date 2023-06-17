package kr.jay.api.service;

import org.springframework.stereotype.Service;

import kr.jay.api.domain.Coupon;
import kr.jay.api.repository.CouponRepository;

@Service
public class ApplyService {

	private final CouponRepository couponRepository;

	public ApplyService(final CouponRepository couponRepository) {
		this.couponRepository = couponRepository;
	}

	public void apply(final Long userId) {
		final long count = couponRepository.count();
		if (count > 100) {
			return;
		}
		couponRepository.save(new Coupon(userId));
	}
}
