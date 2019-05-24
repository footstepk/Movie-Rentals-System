package models.customer;

import utilities.Global;

/**
 * MembershipCard is a class that represents a membership card which holds by a customer.
 * 
 * It should reflects the customer membership status
 *
 * It also extends class Customer.
 * @author Kok Heng
 *
 */

public class MembershipCard extends Customer {
	/** Define enum class which has 4 properties. It refered as:
	 * PREMIUM
	 * MUSIC
	 * MUSIC_VIDEO
	 * LIVE_VIDEO
	 *
	 */
	public enum Membership {
		MUSIC("Music only"),
		MUSIC_VIDEO("Music video only"),
		LIVE_VIDEO("Live video only"),
		PREMIUM("Full subscription");

		String membership;
		private Membership(String membership) {
			this.membership = membership;
		}
	}

	/** Reference the global data field @file utilities/Global.java */ 

	/** Construct an empty MembershipCard constructor. */
	public MembershipCard() {
	}

	/** Constructs a MembershipCard to represents the MembershipCard.
	 * @param super the parent class of membership card.
	 * @param id the membership card id.
	 * @param subscribe the membership subscription plan.
	 * @param currentPoint the current point in the card.
	 * @param rewardPoint the reward point in the membership card.
	 */
	public MembershipCard(int customerID, String first, String last, String phone,
			int id, String subscribe, int currentPoint, int rewardPoint) {
		super(customerID, first, last, phone); // inherited from class Customer.
		Global.MEMBERSHIPCARD_ID = id;
		Global.SUBSCRIBED = subscribe;
		Global.CURRENT_POINT = currentPoint;
		Global.REWARD_POINT = rewardPoint;
	}

	/** Constructs membership card constructor to specified its properties. */
	public MembershipCard(int id, String subscribe, int currentPoint, int rewardPoint) {
		Global.MEMBERSHIPCARD_ID = id;
		Global.SUBSCRIBED = subscribe;
		Global.CURRENT_POINT = currentPoint;
		Global.REWARD_POINT = rewardPoint;
	}

	/*
	 * Get membership card id.
	 * @return the membership card id.
	 */
	public int getMembershipCardID() {
		return Global.MEMBERSHIPCARD_ID;
	}

	/*
	 * Get the subscribed plan.
	 * @return subscribed to represents the subscription plan.
	 */
	public String getSubscribe() {
		return Global.SUBSCRIBED;
	}

	/*
	 * Get the membership card current point.
	 * @return the current point in the membership card.
	 */
	public int getCurrentPoint() {
		return Global.CURRENT_POINT;
	}

	/*
	 * Get the membership card reward point.
	 * @return the reward point in the membership card.
	 */
	public int getRewardPoint() {
		return Global.REWARD_POINT;
	}
	/*
	 * Set the membership card info to the standard screen.
	 */
	public void setMembershipCardInfo() {
		super.setCustomerInfo();
		System.out.printf("MembershipCard ID: %-5d\nSubscription: %-5s\n"
				+ "Current Point: %-5d\nReward Point: %-5d\n"
				+ "Customer ID: %-5d\nRegistration Status: %-5s\n", Global.MEMBERSHIPCARD_ID, Global.SUBSCRIBED, Global.CURRENT_POINT, Global.REWARD_POINT, Global.CUSTOMER_ID, Global.MESSAGE);
	}
}
