package org.allen;

import java.time.LocalDateTime;

public class DealServiceImpl implements DealService {
	private final DealRepository dealRepository = new DealRepository();

	public Deal createDeal(double price, int maxItems, int durationHours) {
		Deal deal = new Deal();
		deal.setDealId("D1");
		deal.setPrice(price);
		deal.setAvailableItems(maxItems);
		deal.setStartTime(LocalDateTime.now());
		deal.setEndTime(LocalDateTime.now().plusHours(durationHours));

		dealRepository.saveDeal(deal);

		return deal;
	}

	public void endDeal(String dealId) {
		dealRepository.removeDeal(dealId);
	}

	public void updateDeal(String dealId, int additonalItems, int additionalDurationHours) {
		Deal deal = dealRepository.getDealById(dealId);
		if(deal != null) {
			deal.setAvailableItems(deal.getAvailableItems() + additonalItems);
			deal.setEndTime(deal.getEndTime().plusHours(additionalDurationHours));
		}
	}

	public void claimDeal(String dealId, String userId) throws DealException {
		Deal deal = dealRepository.getDealById(dealId);
		if(deal != null && deal.getAvailableItems() > 0) {
			deal.setAvailableItems(deal.getAvailableItems() - 1);
		} else {
			throw new DealException("Deal is not available or no more items available");
		}
	}

	public Deal getDealById(String dealId) {
		Deal deal = dealRepository.getDealById(dealId);
		return deal;
	}










}
