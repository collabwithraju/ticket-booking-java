package com.myticket;

import java.util.Scanner;

public class MainClass
{
	public static void main(String[] args) {

		Scanner sc=new Scanner(System.in);
		TicketBookingSystem t=new TicketBookingSystem();
		boolean isStart=true;
		while(isStart)
		{
			System.out.println("Enter The Choice: \n 1.CreateUser \n 2.ShowUsers \n 3.BookTicket \n 4.ShowJourneyDetails \n 5.SearchTicketByUserId \n 6.ChangeBoardingPoint \n 7.CancelTicket \n 8.DeleteAccount \n 9.Exit ");
			int ch=sc.nextInt();
			switch(ch)
			{
			case 1:
			{
				System.out.println("Enter The UserId");
				int user_id=sc.nextInt();
				System.out.println("Enter The UserName");
				String userName=sc.next();
				System.out.println("Enter The UserEmail_ID");
				String email=sc.next();
				System.out.println("Enter The MobileNo");
				long mobileNo=sc.nextLong();
				System.out.println("Enter The Gender");
				char gender=sc.next().charAt(0);
				t.createUser(new User(user_id, userName, email, mobileNo, gender));

			}
			break;
			case 2:
			{
				t.showUsers();	
			}
			break;
			case 3:
			{
				System.out.println("Enter The UserId To Book Ticket");
				int user_id=sc.nextInt();
				System.out.println("Enter The Travel Distance In KM");
				int distance=sc.nextInt();
				System.out.println("Enter The BoardingPoint");
				String boardingPoint=sc.next();
				System.out.println("Enter The DestinationPoint");
				String destinationPoint=sc.next();
				System.out.println("Select The SeatNo");
				int seatNo=sc.nextInt();
				t.bookTicket(user_id, new Ticket(distance, boardingPoint, destinationPoint, seatNo));
			}
			break;
			case 4:
			{
				System.out.println("Enter The Ticket Id");
				int ticketId=sc.nextInt();
				t.showJourneyByTicketId(ticketId);

			}
			break;
			case 5:
			{
				System.out.println("Enter The User_Id To Search Ticket");
				int userId=sc.nextInt();
				t.searchTicketsByUserId(userId);

			}
			break;
			case 6:
			{
				System.out.println("Enter The User_Id ");
				int userId=sc.nextInt();
				System.out.println("Enter The Ticket Id");
				int ticketId=sc.nextInt();
				System.out.println("Enter The New BoardingPoint");
				String boardingPoint=sc.next();
				t.changeBoardingPoint(userId, ticketId, boardingPoint);

			}
			break;
			case 7:
			{
				System.out.println("Enter The User_ID to Cancel Ticket");
				int user_id=sc.nextInt();
				System.out.println("Enter The TicketID");
				int ticketId=sc.nextInt();
				t.cancelTicket(user_id, ticketId);

			}
			break;
			case 8:
			{
				System.out.println("Enter The User_ID To Delete Account");
				int user_id=sc.nextInt();
				t.deleteAccount(user_id);
			}
			break;
			case 9:
			{
				isStart=false;
				System.out.println("Thank You...");
			}
			break;
			default:System.out.println("Enter The Valid Choice...");
			}
		}
	}
}



