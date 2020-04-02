import java.util.Scanner;

public enum ZooClubMenu {

    ADD_MEMBER, ADD_PET_TO_MEMBER, DELETE_PET_FROM_MEMBER, DELETE_MEMBER, DELETE_PET_FROM_ALL_MEMBERS, SHOW, WRITE, ADD_IT_UP, SERIALIZE, DESERIALIZE, EXIT;

    static Scanner scanner = new Scanner(System.in);

    static ZooClubMenu getMenu(){
        System.out.println("Меню: (ADD_MEMBER, ADD_PET_TO_MEMBER, DELETE_PET_FROM_MEMBER, DELETE_MEMBER, DELETE_PET_FROM_ALL_MEMBERS, SHOW, WRITE, ADD_IT_UP, SERIALIZE, DESERIALIZE, EXIT): ");
        return ZooClubMenu.valueOf(scanner.next().toUpperCase());
    }
}

