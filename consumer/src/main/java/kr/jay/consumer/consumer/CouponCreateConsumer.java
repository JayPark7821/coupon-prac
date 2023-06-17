package kr.jay.consumer.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import kr.jay.consumer.domain.Coupon;
import kr.jay.consumer.repository.CouponRepository;

@Component
public class CouponCreateConsumer {

	private final CouponRepository couponRepository;

	public CouponCreateConsumer(final CouponRepository couponRepository) {
		this.couponRepository = couponRepository;
	}

	@KafkaListener(topics = "coupon_create", groupId = "group_1")
	public void listener(final Long userId) {
		System.out.println("userId = " + userId);
		couponRepository.save(new Coupon(userId));
	}
}
