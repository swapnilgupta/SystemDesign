package org.allen;

public interface DealService {
	Deal createDeal(double price, int maxItems, int maxDurationHours);
	void endDeal(String dealId);

	void updateDeal(String dealId, int additonalItems, int additionalDurationHours);

	void claimDeal(String dealId, String userId) throws DealException;

	Deal getDealById(String dealId);
}
