package kr.jay.api.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.jay.api.repository.CouponRepository;

@SpringBootTest
class ApplyServiceTest {

	@Autowired
	private ApplyService applyService;

	@Autowired
	private CouponRepository couponRepository;

	@Test
	void 한번응모() throws Exception {
		//given
		//when
		applyService.apply(1L);
		//then
		assertThat(couponRepository.count()).isEqualTo(1);
	}

	@Test
	void 여러명_응모() throws Exception {
		//given
		int threadCount = 1000;
		final ExecutorService executorService = Executors.newFixedThreadPool(16);
		final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
		//when
		for (int i = 0; i < threadCount; i++) {
			long userId = i;
			executorService.submit(() -> {
				try {
					applyService.apply(userId);
				} finally {
					countDownLatch.countDown();
				}
			});
		}
		countDownLatch.await();

		final long count = couponRepository.count();
		assertThat(count).isEqualTo(100);

	}
}
