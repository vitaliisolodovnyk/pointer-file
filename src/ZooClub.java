import java.io.*;
import java.util.*;

public class ZooClub {

    Scanner scanner = new Scanner(System.in);
    private Map<Person, List<Pet>> map;
    private List<Pet> pets;

    public ZooClub() {
        this.map = new HashMap<>();
    }

    public Map<Person, List<Pet>> getMap() {
        return map;
    }

    void showMenu(ZooClub zooClub) throws IOException, ClassNotFoundException {
        switch (ZooClubMenu.getMenu()){
            case ADD_MEMBER:
                System.out.println("Введіть ім`я та вік члена клубу: ");
                addPerson(new Person(scanner.next(), scanner.nextInt()));
                break;
            case ADD_PET_TO_MEMBER:
                System.out.println("Введіть ім`я та вік персони, якій бажаєте додати тварину: ");
                addPetToPerson(new Person(scanner.next(), scanner.nextInt()));
                break;
            case DELETE_PET_FROM_MEMBER:
                System.out.println("Введіть ім`я та вік члена клубу, з якого бажаєте видалити тварину: ");
                deletePetFromPerson(new Person(scanner.next(), scanner.nextInt()));
                break;
            case DELETE_MEMBER:
                System.out.println("Введіть ім`я та вік члена клубу, якого бажаєте видалити: ");
                deletePerson(new Person(scanner.next(), scanner.nextInt()));
                break;
            case DELETE_PET_FROM_ALL_MEMBERS:
                System.out.println("Введіть ім`я та вагу тварини, яку бажаєте видалити зі всіх членів клубу: ");
                deletePetFromAll(new Pet(scanner.next(), scanner.nextInt()));
                break;
            case SHOW:
                showZooClub();
                break;
            case WRITE:
                System.out.println("Введіть ім`я файлу: ");
                String fileName = scanner.next();
                writeToFile(new File(fileName), zooClub);
                break;
            case ADD_IT_UP:
                System.out.println("Введіть ім`я файлу в який дописати: ");
                String fileName2 = scanner.next();
                writeToFile(new File(fileName2), zooClub);
                break;
            case SERIALIZE:
                System.out.println("Введіть ім`я файлу для серіалізації: ");
                String serialize = scanner.next();
                serialize(zooClub, new File(serialize));
                break;
            case DESERIALIZE:
                System.out.println("Введіть ім`я файлу для десеріалізації: ");
                String deserialize = scanner.next();
                deserialize(new File(deserialize));
            case EXIT:
                System.exit(0);
        }
    }

    void addPerson(Person person){
        map.put(person, new LinkedList<Pet>());
        System.out.println("Ви додали: " + person);
    }

    void addPetToPerson(Person person){
        if (map.containsKey(person)) {
            System.out.println("Введіть ім`я та вагу тварини: ");
            map.get(person)
                    .add(new Pet(scanner.next(), scanner.nextInt()));
        }
    }

    void deletePetFromPerson(Person person){
        if (map.containsKey(person)){
            map.get(person).remove(new Pet(scanner.next(), scanner.nextInt()));
        }
    }

    void deletePerson(Person person){
        if (map.containsKey(person)){
            map.remove(person);
            System.out.println("Ви видалили: " + person);
        }
    }

    void deletePetFromAll(Pet pet){
        for (Map.Entry<Person, List<Pet>> entry : map.entrySet()) {
            entry.getValue().remove(pet);
            System.out.println("Ви видалили: " + pet);
        }
    }

    void showZooClub(){
        System.out.println(getMap());
    }

    void writeToFile(File file, ZooClub zooClub) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write(zooClub.toString());
    }

    void addItUp(File file, ZooClub zooClub) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
        bufferedWriter.newLine();
        bufferedWriter.write(zooClub.toString());
    }

    void serialize(ZooClub zooClub, File file) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        objectOutputStream.writeObject(zooClub);
    }

    void deserialize(File file) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
        ZooClub zooClub = (ZooClub) objectInputStream.readObject();
    }

}
