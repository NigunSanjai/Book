import java.util.*;
class Theatre{
    String name="";
    String location="";
    String fare="";
    ArrayList<Screens> screens = new ArrayList<>();

}
class Screens{
    String name="";
    ArrayList<Shows> shows=new ArrayList<>();

}
class Shows{
    String time="";
    String movie_name="";
    String no_of_tickets="";
    ArrayList<ArrayList<Integer>> seat_view = new ArrayList<>();

}
class App_admin {
    ArrayList<String> letters = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"));
    Scanner sc = new Scanner(System.in);
    String username = "XYZ";
    String pass = "123";
    ArrayList<App_user> list_of_user = new ArrayList<>();
    ArrayList<Theatre> list_of_theatre = new ArrayList<>();
    HashMap<String,ArrayList<String>> loc_mov = new HashMap<>();
    HashMap<String,ArrayList<Theatre>> mov_tht = new HashMap<>();


    public void create_theatre() {
        Theatre theatre = new Theatre();
        list_of_theatre.add(theatre);
        System.out.println("Enter theatre name");
        theatre.name = sc.nextLine();
        System.out.println("Enter theatre location");
        theatre.location = sc.nextLine();
        System.out.println("Enter the general fare");
        theatre.fare = sc.nextLine();
        System.out.println("Enter number of Screens");
        String scr = sc.nextLine();
        for (int i = 0; i < Integer.parseInt(scr); i++) {
            Screens screen = new Screens();
            theatre.screens.add(screen);
            System.out.println("Enter the screen name");
            screen.name = sc.nextLine();
            System.out.println("Enter no of shows per day");
            String no_of_s = sc.nextLine();
            for (int j = 0; j < Integer.parseInt(no_of_s); j++) {
                Shows show = new Shows();
                screen.shows.add(show);
                System.out.println("Enter show timing");
                show.time = sc.nextLine();
                System.out.println("Enter the movie name");
                show.movie_name = sc.nextLine();
                if(loc_mov.isEmpty()) loc_mov.put(theatre.location,new ArrayList<>(Arrays.asList(show.movie_name)));
                if(!loc_mov.containsKey(theatre.location)) loc_mov.put(theatre.location,new ArrayList<>(Arrays.asList(show.movie_name)));
                if(!loc_mov.get(theatre.location).contains(show.movie_name)) loc_mov.get(theatre.location).add(show.movie_name);

                if(mov_tht.isEmpty()) mov_tht.put(show.movie_name,new ArrayList<>(Arrays.asList(theatre)));
                if(!mov_tht.containsKey(show.movie_name)) mov_tht.put(show.movie_name, new ArrayList<>(Arrays.asList(theatre)));
                if(!mov_tht.get(show.movie_name).contains(theatre)) mov_tht.get(show.movie_name).add(theatre);
                System.out.println("Enter the number of rows (Less than 26)");
                String rows = sc.nextLine();
                System.out.println("Enter the number of cols (Less than 26)");
                String cols = sc.nextLine();
                show.no_of_tickets = String.valueOf(Integer.parseInt(rows) * Integer.parseInt(cols));
                for (int k = 0; k < Integer.parseInt(rows); k++) {
                    ArrayList<Integer> arr = new ArrayList<>();
                    for (int l = 0; l < Integer.parseInt(cols); l++) {
                        arr.add(0);
                    }
                    show.seat_view.add(arr);
                }
            }
        }
    }
}
class App_user{
    String username = "";
    String name = "";
    String pass="";

    ArrayList<ArrayList<String>> booked_tickets= new ArrayList<>();

}

public class BookMe {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        App_admin admin = new App_admin();
        boolean global_exit = true;
        boolean admin_exit = true;
        boolean user_exit = true;
        while (global_exit) {
            System.out.println("1.Admin");
            System.out.println("2.User");
            System.out.println("3.Exit");
            String o = sc.nextLine();

            if (Integer.parseInt(o) == 1) {
                while (admin_exit) {
                    while (true) {
                        System.out.println("Enter your Username");
                        String u_uname = sc.nextLine();
                        System.out.println("Enter your password");
                        String u_pass = sc.nextLine();
                        if (admin.username.equals(u_uname) && admin.pass.equals(u_pass)) break;
                        System.out.println("Invalid Authentication try again");
                    }
                    System.out.println();
                    while (true) {
                        System.out.println("1.REGISTER THEATRE");
                        System.out.println("2.VIEW THEATRE");
                        System.out.println("3.GO TO MAIN MENU");

                        String op = sc.nextLine();
                        if (op.equals("1")) {
                            admin.create_theatre();
                        } else if (op.equals("2")) {

                        } else if (op.equals("3")) {
                            admin_exit = false;
                            break;
                        }
                    }
                }
            }
            else if (Integer.parseInt(o) == 2) {
                while (user_exit) {
                    System.out.println("1.Sign in");
                    System.out.println("2.Sign up");
                    System.out.println("3.Exit");
                    String u_o = sc.nextLine();
                    App_user curr_user = new App_user();
                    if (Integer.parseInt(u_o) == 1) {
                        if (admin.list_of_user.isEmpty()) System.out.println("No user exist");
                        else {
                            boolean u_valid = false;
                            while (!u_valid) {
                                System.out.println("Enter your username");
                                String u_n = sc.nextLine();
                                System.out.println("Enter your password");
                                String u_p = sc.nextLine();
                                for (App_user user : admin.list_of_user) {
                                    if (user.username.equals(u_n) && user.pass.equals(u_p)) {
                                        curr_user = user;
                                        u_valid = true;
                                        break;
                                    }
                                }
                                admin.list_of_user.add(curr_user);
                                if (!u_valid) System.out.println("Invalid Authentication try again");
                            }
                            while (true) {
                                System.out.println("----------------------WELCOME TO BOOKNOW---------------------");
                                System.out.println("Enter the operation you wish to perform");
                                System.out.println("1.BOOK TICKETS");
                                System.out.println("2.VIEW BOOKED TICKETS");
                                System.out.println("3.CANCEL TICKETS");
                                String user_function = sc.nextLine();
                                if (user_function.equals("1")) {
                                    System.out.println("Enter your desired movie location");
                                    String u_d_l = sc.nextLine();
                                    System.out.println("List of movies streaming nearby");
                                    for (String i : admin.loc_mov.get(u_d_l)) {
                                        System.out.println(i);
                                    }
                                    System.out.println("Enter the movie you wish to watch");
                                    String u_w_m = sc.nextLine();
                                    System.out.println("List of theatres streaming your movie");
                                    for (Theatre theatre : admin.mov_tht.get(u_w_m)) {
                                        System.out.println(theatre.name);
                                    }
                                    System.out.println("Select the theatre to proceed");
                                    String u_s_t = sc.nextLine();
                                    System.out.println("Avaliable show timings");
                                    for (Theatre theatre : admin.list_of_theatre) {
                                        if (theatre.name.equals(u_s_t)) {
                                            for (Screens screen : theatre.screens) {
                                                for (Shows show : screen.shows) {
                                                    if (show.movie_name.equals(u_w_m)) {
                                                        System.out.println(show.time);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    System.out.println("Enter the Preferred Show timing");
                                    String u_p_t = sc.nextLine();
                                    System.out.println("Screen View");
                                    for (Theatre theatre : admin.list_of_theatre) {
                                        if (theatre.name.equals(u_s_t)) {
                                            for (Screens screen : theatre.screens) {
                                                for (Shows show : screen.shows) {
                                                    if (show.movie_name.equals(u_w_m)) {
                                                        if (show.time.equals(u_p_t)) {
                                                            for (int i = 0; i < show.seat_view.size(); i++) {
                                                                System.out.print(admin.letters.get(i) + ": ");
                                                                for (int j = 0; j < show.seat_view.get(i).size(); j++) {
                                                                    System.out.print(show.seat_view.get(i).get(j));
                                                                }
                                                                System.out.println();
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    String scr_name = new String();
                                    System.out.println("Enter the total number of seats required");
                                    String no_s = sc.nextLine();
                                    boolean status = true;
                                    ArrayList<String> booked_tickets = new ArrayList<>();
                                    if (status) {
                                        for (Theatre theatre : admin.list_of_theatre) {
                                            if (theatre.name.equals(u_s_t)) {
                                                for (Screens screen : theatre.screens) {
                                                    if (status) {
                                                        for (Shows show : screen.shows) {
                                                            if (show.movie_name.equals(u_w_m)) {
                                                                if (show.time.equals(u_p_t)) {
                                                                    for (int i = 0; i < Integer.parseInt(no_s); i++) {
                                                                        System.out.println("Enter the row ");
                                                                        String row = sc.nextLine();
                                                                        System.out.println("Enter the seat number required");
                                                                        String no = sc.nextLine();
                                                                        if (show.seat_view.get(admin.letters.indexOf(row)).get(Integer.parseInt(no)) == 1) {
                                                                            System.out.println("Seat already booked!!");
                                                                            i--;
                                                                        } else {
                                                                            show.seat_view.get(admin.letters.indexOf(row)).set(Integer.parseInt(no), 1);
                                                                            booked_tickets.add(row + "-" + no);
                                                                        }
                                                                    }
                                                                    status = false;
                                                                    scr_name = screen.name;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    String ls = String.join(",", booked_tickets);
                                    curr_user.booked_tickets.add(new ArrayList<>(Arrays.asList(u_d_l, u_s_t, u_w_m, u_p_t, scr_name, no_s, ls)));
                                    System.out.println("Sucessfully booked!!");
                                } else if (user_function.equals("2")) {
                                    for (ArrayList<String> arr : curr_user.booked_tickets) {
                                        System.out.println("---------------TICKET INFO------------------");
                                        System.out.println("LOCATION : " + arr.get(0));
                                        System.out.println("THEATRE NAME : " + arr.get(1));
                                        System.out.println("MOVIE NAME : " + arr.get(2));
                                        System.out.println("SHOW TIMING : " + arr.get(3));
                                        System.out.println("SCREEN NAME : " + arr.get(4));
                                        System.out.println("NUMBER OF SCREENS : " + arr.get(5));
                                        System.out.println("SEAT ALLOCATION: " + arr.get(6));

                                    }
                                } else if (user_function.equals("3")) {
                                    int index = 0;
                                    for(ArrayList<String> arr : curr_user.booked_tickets){
                                        System.out.println("TICKET ID : "+ String.valueOf(index+1));
                                        System.out.print("LOCATION : " + arr.get(0));
                                        System.out.print("THEATRE NAME : " + arr.get(1));
                                        System.out.print("MOVIE NAME : " + arr.get(2));
                                        System.out.print("SHOW TIMING : " + arr.get(3));
                                        System.out.print("SCREEN NAME : " + arr.get(4));
                                        System.out.print("NUMBER OF SCREENS : " + arr.get(5));
                                        System.out.print("SEAT ALLOCATION: " + arr.get(6));
                                        System.out.println();
                                        index++;
                                    }
                                    System.out.println("Enter the ticket id you wish to delete");
                                    String c_t_id = sc.nextLine();
                                    int ind = Integer.valueOf(c_t_id)-1;
                                    ArrayList<String> arr = curr_user.booked_tickets.get(ind);
                                    for (Theatre theatre : admin.list_of_theatre) {
                                        if (theatre.name.equals(arr.get(1))) {
                                            for (Screens screen : theatre.screens) {
                                                if (screen.name.equals(arr.get(4))) {
                                                    for (Shows show : screen.shows) {
                                                        if (show.movie_name.equals(arr.get(2))) {
                                                            if (show.time.equals(arr.get(3))) {
                                                                String[] spi = arr.get(6).split(",");
                                                                for (int i = 0; i < spi.length; i++) {
                                                                    String[] ind_spi = spi[i].split("-");
                                                                    String row = ind_spi[0];
                                                                    String no = ind_spi[1];
                                                                    show.seat_view.get(admin.letters.indexOf(row)).set(Integer.parseInt(no), 0);

                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    curr_user.booked_tickets.remove(ind);
                                }
                            }
                        }
                    }
                                 else if (Integer.parseInt(u_o) == 2) {
                                    App_user user = new App_user();
                                    admin.list_of_user.add(user);
                                    System.out.println("Enter Name");
                                    user.name = sc.nextLine();
                                    System.out.println("Enter username");
                                    user.username = sc.nextLine();
                                    System.out.println("Enter password");
                                    user.pass = sc.nextLine();
                                }

                            }
                        }
                }
            }
        }




