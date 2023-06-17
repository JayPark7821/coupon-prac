package kr.jay.api.service;

import org.springframework.stereotype.Service;

import kr.jay.api.domain.Coupon;
import kr.jay.api.repository.CouponCountRepository;
import kr.jay.api.repository.CouponRepository;

@Service
public class ApplyService {

	private final CouponRepository couponRepository;
	private final CouponCountRepository couponCountRepository;

	public ApplyService(final CouponRepository couponRepository, final CouponCountRepository couponCountRepository) {
		this.couponRepository = couponRepository;
		this.couponCountRepository = couponCountRepository;
	}

	public void apply(final Long userId) {
		final Long count = couponCountRepository.increment();

		if (count > 100) {
			return;
		}
		couponRepository.save(new Coupon(userId));
	}
}
