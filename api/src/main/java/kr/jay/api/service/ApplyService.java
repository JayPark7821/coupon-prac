package kr.jay.api.service;

import org.springframework.stereotype.Service;

import kr.jay.api.producer.CouponCreateProducer;
import kr.jay.api.repository.AppliedUserRepository;
import kr.jay.api.repository.CouponCountRepository;
import kr.jay.api.repository.CouponRepository;

@Service
public class ApplyService {

	private final CouponRepository couponRepository;
	private final CouponCountRepository couponCountRepository;
	private final CouponCreateProducer couponCreateProducer;
	private final AppliedUserRepository appliedUserRepository;


	public ApplyService(
		final CouponRepository couponRepository,
		final CouponCountRepository couponCountRepository,
		final CouponCreateProducer couponCreateProducer,
		final AppliedUserRepository appliedUserRepository) {
		this.couponRepository = couponRepository;
		this.couponCountRepository = couponCountRepository;
		this.couponCreateProducer = couponCreateProducer;
		this.appliedUserRepository = appliedUserRepository;
	}

	public void apply(final Long userId) {
		final Long apply = appliedUserRepository.add(userId);

		if (apply != 1) {
			return;
		}
		final Long count = couponCountRepository.increment();
		if (count > 100) {
			return;
		}
		couponCreateProducer.create(userId);
	}
}
