package org.allen;

public class Main {
	public static void main(String[] args) {
		DealService dealService = new DealServiceImpl();

		// example creating the deal
		Deal deal = dealService.createDeal(30.0, 5, 1);

		System.out.println("Deal created: " + deal.getDealId());

		// example updating the deal

		dealService.updateDeal(deal.getDealId(), 3, 1);
		System.out.println("Deal updated: " + dealService.getDealById(deal.getDealId()).getDealId());

		// now claim the deal

		try {
			dealService.claimDeal(deal.getDealId(), "U1");
			System.out.println("Deal claimed: " + dealService.getDealById(deal.getDealId()).getDealId());
		} catch (DealException e) {
			System.out.println("Deal could not be claimed: " + e.getMessage());
		}
	}
}