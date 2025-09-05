package com.myticket;

import java.io.Serializable;
import java.util.Random;

public class Ticket implements Serializable
{
	private final int ticketId;
	private int distance;
	private String boardingPoint;
	private String destinationPoint;
	private int seatNo;
	private int cost; 
	private int bookedUserId; // user who booked this ticket

	private static final Random RANDOM = new Random();

	public Ticket(int distance, String boardingPoint, String destinationPoint, int seatNo) {
		this.ticketId = RANDOM.nextInt(1_234_567) + 1; // non-zero id
		this.distance = distance;
		this.boardingPoint = boardingPoint;
		this.destinationPoint = destinationPoint;
		this.seatNo = seatNo;
		this.cost = calculateCost(distance);
		this.bookedUserId = -1; // not booked yet
	}

	private int calculateCost(int distance) {
		return distance * 2; // ₹10 per km
	}

	public int getTicketId() 
	{ return ticketId; }
	public int getDistance() 
	{ return distance; }
	public String getBoardingPoint() 
	{ return boardingPoint; }
	public String getDestinationPoint() 
	{ return destinationPoint; }
	public int getSeatNo() 
	{ return seatNo; }
	public int getCost() 
	{ return cost; }
	public int getBookedUserId() 
	{ return bookedUserId; }

	public void setDistance(int distance) {
		this.distance = distance;
		this.cost = calculateCost(distance);
	}
	public void setBoardingPoint(String boardingPoint) 
	{ this.boardingPoint = boardingPoint; }
	public void setDestinationPoint(String destinationPoint) 
	{ this.destinationPoint = destinationPoint; }
	public void setSeatNo(int seatNo)
	{ this.seatNo = seatNo; }
	public void setBookedUserId(int userId)
	{ this.bookedUserId = userId; }

	public void display() {
		System.out.println("------------ TICKET DETAILS ------------");
		System.out.println("Ticket ID       : " + ticketId);
		System.out.println("Seat No         : " + seatNo);
		System.out.println("Distance (km)   : " + distance);
		System.out.println("Cost (₹)        : " + cost);
		System.out.println("Boarding Point  : " + boardingPoint);
		System.out.println("Destination     : " + destinationPoint);
		System.out.println("Booked By User  : " + (bookedUserId == -1 ? "Not booked" : bookedUserId));
		System.out.println("----------------------------------------");
	}

	@Override
	public String toString() {
		return "Ticket{" +
				"ticketId=" + ticketId +
				", seatNo=" + seatNo +
				", distance=" + distance +
				", cost=" + cost +
				", boarding='" + boardingPoint + '\'' +
				", destination='" + destinationPoint + '\'' +
				", bookedBy=" + bookedUserId +
				'}';
	}
}
