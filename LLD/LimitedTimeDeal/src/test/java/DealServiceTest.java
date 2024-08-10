import org.allen.Deal;
import org.allen.DealService;
import org.allen.DealServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DealServiceTest {
	private final DealService dealService = new DealServiceImpl();


	@Test
	void createDeal() {
		// example creating the deal
		Deal deal = dealService.createDeal(30.0, 5, 1);
		assertEquals(30.0, deal.getPrice());
		assertEquals(5, deal.getAvailableItems());

		System.out.println("Deal created: " + deal.getDealId());
	}

	@Test
	void endDeal() {
		// example creating the deal
		Deal deal = dealService.createDeal(30.0, 5, 1);
		assertEquals(30.0, deal.getPrice());
		assertEquals(5, deal.getAvailableItems());

		System.out.println("Deal created: " + deal.getDealId());

		// example updating the deal
		dealService.endDeal(deal.getDealId());
		assertEquals(null, dealService.getDealById(deal.getDealId()));
	}

	@Test
	void updateDeal() {
		// example creating the deal
		Deal deal = dealService.createDeal(30.0, 5, 1);
		assertEquals(30.0, deal.getPrice());
		assertEquals(5, deal.getAvailableItems());

		System.out.println("Deal created: " + deal.getDealId());

		// example updating the deal
		dealService.updateDeal(deal.getDealId(), 3, 1);
		assertEquals(8, dealService.getDealById(deal.getDealId()).getAvailableItems());
		assertEquals(30.0, dealService.getDealById(deal.getDealId()).getPrice());

		System.out.println("Deal updated: " + dealService.getDealById(deal.getDealId()).getDealId());
	}

	@Test
	void claimDeal() {
		// example creating the deal
		Deal deal = dealService.createDeal(30.0, 5, 1);
		assertEquals(30.0, deal.getPrice());
		assertEquals(5, deal.getAvailableItems());

		System.out.println("Deal created: " + deal.getDealId());

		// example updating the deal
		dealService.updateDeal(deal.getDealId(), 3, 1);
		assertEquals(8, dealService.getDealById(deal.getDealId()).getAvailableItems());
		assertEquals(30.0, dealService.getDealById(deal.getDealId()).getPrice());

		System.out.println("Deal updated: " + dealService.getDealById(deal.getDealId()).getDealId());

		// now claim the deal
		try {
			dealService.claimDeal(deal.getDealId(), "U1");
			assertEquals(7, dealService.getDealById(deal.getDealId()).getAvailableItems());
			System.out.println("Deal claimed: " + dealService.getDealById(deal.getDealId()).getDealId());
		} catch (Exception e) {
			System.out.println("Deal could not be claimed: " + e.getMessage());
		}
	}


}
