package com.myticket;

import java.util.ArrayList;
import java.util.Iterator;

public class TicketBookingSystem 
{
	private  ArrayList<User> users = new ArrayList<User>();
	private  ArrayList<Ticket> tickets = new ArrayList<Ticket>(); // global list

	// Create user
	public void createUser(User u) {
		if (getUserById(u.getUserId()) != null) {
			System.out.println("User with ID " + u.getUserId() + " already exists. Choose another ID.");
			return;
		}
		users.add(u);
		System.out.println("User created: " + u.getName() + " (ID: " + u.getUserId() + ")");
	}

	// Show all users
	public void showUsers() {
		if (users.isEmpty()) {
			System.out.println("No users found.");
			return;
		}
		for (User u : users) {
			u.displayUser();
		}
	}

	// Find a user by id
	public User getUserById(int userId) {
		for (User u : users) {
			if (u.getUserId() == userId) {
				return u;
			}
		}
		return null;
	}

	// Book ticket by passing userId and Ticket object
	public void bookTicket(int userId, Ticket t) {
		User u = getUserById(userId);
		if (u == null) {
			System.out.println("Invalid User ID. Create user before booking.");
			return;
		}

		// link ticket to user
		t.setBookedUserId(userId);
		u.addTicket(t);

		// add to global list
		tickets.add(t);

		System.out.println("Ticket booked successfully for user " + u.getName() + ". Ticket ID: " + t.getTicketId());
	}

	// Get ticket from global list by ticketId
	public Ticket getTicketById(int ticketId) {
		for (Ticket t : tickets) {
			if (t.getTicketId() == ticketId) {
				return t;
			}
		}
		return null;
	}

	// Show journey by ticket id — finds ticket in global list and the exact user who booked it
	public void showJourneyByTicketId(int ticketId) {
		Ticket t = getTicketById(ticketId);
		if (t == null) {
			System.out.println("No ticket found with ID: " + ticketId);
			return;
		}

		User u = getUserById(t.getBookedUserId());
		if (u == null) {
			System.out.println("The user who booked this ticket (id=" + t.getBookedUserId() + ") no longer exists.");
			System.out.println("Ticket details:");
			t.display();
			return;
		}

		// ensure that the ticket actually belongs to the user (safety check)
		Ticket userTicket = u.getTicketById(ticketId);
		if (userTicket == null) {
			System.out.println("Data mismatch: ticket found in global list but not in user's list. (ticketId=" + ticketId + ")");
			return;
		}

		System.out.println("---- USER ----");
		u.displayUser();
		System.out.println("---- TICKET ----");
		t.display();
	}

	// Show all tickets for a user (search by userId)
	public void searchTicketsByUserId(int userId) {
		User u = getUserById(userId);
		if (u == null) {
			System.out.println("No user found with ID: " + userId);
			return;
		}
		System.out.println("Tickets for user: " + u.getName());
		ArrayList<Ticket> userTickets = u.getTickets();
		if (userTickets.isEmpty()) {
			System.out.println("No tickets booked by this user.");
			return;
		}
		for (Ticket t : userTickets) {
			t.display();
		}
	}

	// Change boarding point for a particular ticket (must belong to the user)
	public void changeBoardingPoint(int userId, int ticketId, String newBoardingPoint) {
		User u = getUserById(userId);
		if (u == null) {
			System.out.println("No user found with ID: " + userId);
			return;
		}
		Ticket t = u.getTicketById(ticketId);
		if (t == null) {
			System.out.println("No ticket with ID " + ticketId + " found for user " + u.getName());
			return;
		}
		t.setBoardingPoint(newBoardingPoint);
		System.out.println("Boarding point updated for ticket " + ticketId + " to: " + newBoardingPoint);
	}

	// Change destination point for a particular ticket (must belong to the user)
	public void changeDestinationPoint(int userId, int ticketId, String newDestinationPoint) {
		User u = getUserById(userId);
		if (u == null) {
			System.out.println("No user found with ID: " + userId);
			return;
		}
		Ticket t = u.getTicketById(ticketId);
		if (t == null) {
			System.out.println("No ticket with ID " + ticketId + " found for user " + u.getName());
			return;
		}
		t.setDestinationPoint(newDestinationPoint);
		System.out.println("Destination updated for ticket " + ticketId + " to: " + newDestinationPoint);
	}

	// Cancel ticket by userId and ticketId (removes from both user and global lists)
	public void cancelTicket(int userId, int ticketId) {
		User u = getUserById(userId);
		if (u == null) {
			System.out.println("No user found with ID: " + userId);
			return;
		}

		boolean removedFromUser = u.removeTicketById(ticketId);
		if (!removedFromUser) {
			System.out.println("User does not have a ticket with ID: " + ticketId);
			return;
		}

		// remove from global tickets list
		Iterator<Ticket> it = tickets.iterator();
		while (it.hasNext()) {
			Ticket t = it.next();
			if (t.getTicketId() == ticketId) {
				it.remove();
				System.out.println("Ticket " + ticketId + " cancelled successfully for user " + u.getName());
				return;
			}
		}
		// If not found in global list (shouldn't happen), still report success
		System.out.println("Ticket removed from user's list (not found in global list).");
	}

	// Delete user account by id and cascade-delete their tickets
	public void deleteAccount(int userId) {
		Iterator<User> userIt = users.iterator();
		while (userIt.hasNext()) {
			User u = userIt.next();
			if (u.getUserId() == userId) {
				// remove user's tickets from global list
				ArrayList<Ticket> userTickets = u.getTickets();
				for (Ticket ut : userTickets) {
					// remove each ticket from global tickets
					Iterator<Ticket> tIt = tickets.iterator();
					while (tIt.hasNext()) {
						Ticket g = tIt.next();
						if (g.getTicketId() == ut.getTicketId()) {
							tIt.remove();
							break;
						}
					}
				}
				userIt.remove();
				System.out.println("User " + u.getName() + " deleted. Their tickets (if any) were also removed.");
				return;
			}
		}
		System.out.println("No user found with ID: " + userId);
	}

	// View all tickets (global) with user info
	public void viewAllTickets() {
		if (tickets.isEmpty()) {
			System.out.println("No tickets booked yet.");
			return;
		}
		for (Ticket t : tickets) {
			User u = getUserById(t.getBookedUserId());
			if (u != null) {
				u.displayUser();
			} else {
				System.out.println("User (id=" + t.getBookedUserId() + ") not found.");
			}
			t.display();
		}
	}
}
