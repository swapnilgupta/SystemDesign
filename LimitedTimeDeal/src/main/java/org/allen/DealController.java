package org.allen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/deal")
public class DealController {
	@Autowired
	private final DealService dealService = new DealServiceImpl();

	@PostMapping("/create")
	public Deal createDeal(@RequestBody Deal deal, @RequestParam int maxItems, @RequestParam int maxDurationHours) {
		dealService.createDeal(deal.getPrice(), maxItems, maxDurationHours);
		return deal;
	}

	@PostMapping("/end")
	public void endDeal(@RequestBody String dealId) {
		dealService.endDeal(dealId);
	}

	@PostMapping("/update")
	public void updateDeal(@RequestBody String dealId, @RequestParam int additonalItems, @RequestParam int additionalDurationHours) {
		dealService.updateDeal(dealId, additonalItems, additionalDurationHours);
	}

	@PostMapping("/claim")
	public void claimDeal(@RequestBody String dealId, @RequestParam String userId) throws DealException {
		try {
			dealService.claimDeal(dealId, userId);
		} catch (DealException e) {
			System.out.println("Deal could not be claimed: " + e.getMessage());
		}
	}
}
