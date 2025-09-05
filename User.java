package com.myticket;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class User implements Serializable {
    private final int userId;
    private final String name;
    private final String email;
    private final long phone;
    private final char gender;

    // per-user tickets
    private final ArrayList<Ticket> tickets = new ArrayList<Ticket>();

    public User(int userId, String name, String email, long phone, char gender) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
    }

    public int getUserId()
    { return userId; }
    public String getName() 
    { return name; }
    public String getEmail()
    { return email; }
    public long getPhone() 
    { return phone; }
    public char getGender() 
    { return gender; }

    // add ticket to this user's list
    public void addTicket(Ticket t) {
        tickets.add(t);
    }

    // remove a ticket by ticketId from this user's list
    public boolean removeTicketById(int ticketId) {
        Iterator<Ticket> it = tickets.iterator();
        while (it.hasNext()) {
            Ticket t = it.next();
            if (t.getTicketId() == ticketId) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    // get ticket by id from this user's list
    public Ticket getTicketById(int ticketId) {
        for (Ticket t : tickets) {
            if (t.getTicketId() == ticketId) {
                return t;
            }
        }
        return null;
    }

    // return copy of tickets list (so caller can't modify directly)
    public ArrayList<Ticket> getTickets() {
        ArrayList<Ticket> copy = new ArrayList<Ticket>();
        for (Ticket t : tickets) {
            copy.add(t);
        }
        return copy;
    }

    public void displayUser() {
        System.out.println("------------ USER DETAILS ------------");
        System.out.println("User ID : " + userId);
        System.out.println("Name    : " + name);
        System.out.println("Email   : " + email);
        System.out.println("Phone   : " + phone);
        System.out.println("Gender  : " + gender);
        System.out.println("--------------------------------------");
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", name='" + name + '\'' + ", email='" + email + '\'' + '}';
    }
}

