import java.util.*;

class Room {
    int roomNumber;
    boolean isAvailable;
    double pricePerNight;

    Room(int roomNumber, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.pricePerNight = pricePerNight;
        this.isAvailable = true;
    }

    void bookRoom() {
        isAvailable = false;
    }

    void releaseRoom() {
        isAvailable = true;
    }
}

class Reservation {
    String guestName;
    Room room;

    Reservation(String guestName, Room room) {
        this.guestName = guestName;
        this.room = room;
    }
}

public class HotelReservationSystem {
    public static void main(String[] args) {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(101, 100.0));
        rooms.add(new Room(102, 150.0));

        List<Reservation> reservations = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. View Available Rooms");
            System.out.println("2. Make Reservation");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.println("\nAvailable Rooms:");
                for (Room room : rooms) {
                    if (room.isAvailable) {
                        System.out.println("Room " + room.roomNumber + " - $" + room.pricePerNight);
                    }
                }
            } else if (choice == 2) {
                System.out.print("Enter guest name: ");
                sc.nextLine(); // Consume newline
                String guestName = sc.nextLine();

                System.out.print("Enter room number to book: ");
                int roomNumber = sc.nextInt();

                Room selectedRoom = null;
                for (Room room : rooms) {
                    if (room.roomNumber == roomNumber && room.isAvailable) {
                        selectedRoom = room;
                        break;
                    }
                }

                if (selectedRoom != null) {
                    selectedRoom.bookRoom();
                    Reservation reservation = new Reservation(guestName, selectedRoom);
                    reservations.add(reservation);
                    System.out.println("Reservation confirmed for " + guestName + " in Room " + roomNumber);
                    System.out.println("Total cost: $" + selectedRoom.pricePerNight);
                } else {
                    System.out.println("Room not available.");
                }
            } else if (choice == 3) {
                System.out.println("Exiting...");
                break;
            }
        }
        sc.close();
    }
}
