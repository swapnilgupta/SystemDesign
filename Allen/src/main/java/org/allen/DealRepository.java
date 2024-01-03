package org.allen;

import java.util.*;

public class DealRepository {

	private final Map<String, Deal> deals = new HashMap<>();

	public void saveDeal(Deal deal) {
		deals.put(deal.getDealId(), deal);
	}

	public Deal getDealById(String dealId) {
		return deals.get(dealId);
	}

	public void updateDeal(Deal deal) {
		deals.put(deal.getDealId(), deal);
	}

	public void removeDeal(String dealId) {
		deals.remove(dealId);
	}

}
