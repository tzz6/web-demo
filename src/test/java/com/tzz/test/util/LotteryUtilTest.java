package com.tzz.test.util;

import java.util.List;

import org.junit.Test;

import com.tzz.util.LotteryUtil;
import com.tzz.util.RewardEntry;

public class LotteryUtilTest {


	/** 判断是否全是同一名称 */
	public boolean isWinner(List<RewardEntry> rewardEntries) {
		boolean isWinner = true;
		for (RewardEntry rewardEntry : rewardEntries) {
			if (rewardEntries.get(0).getName() != rewardEntry.getName()) {// 名称不全相同
				return false;
			}
		}
		return isWinner;
	}

	/** 测试 */
	@Test
	public void testLottery() {
		LotteryUtil lotteryUtil = new LotteryUtil();
		List<RewardEntry> rewardModelList = null;
		for (int i = 0; i < 100; i++) {
			rewardModelList = lotteryUtil.getKeys(1);
			if (isWinner(rewardModelList)) {
				System.out.println("===第" + i + "次抽中===");
				for (RewardEntry rewardModel : rewardModelList) {
					System.out.println("name="+rewardModel.getName() + "," + "percent=" + rewardModel.getPercent());
				}
			}
		}
	}
	
	/** 测试 */
	@Test
	public void testGetLottery() {
		LotteryUtil lotteryUtil = new LotteryUtil();
		List<RewardEntry> rewardModelList = lotteryUtil.getKeys(1);
		System.out.println(rewardModelList.size());
	}
}