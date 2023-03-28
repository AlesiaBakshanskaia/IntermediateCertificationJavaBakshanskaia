package raffleOfToys;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    startWork();
    }

    private static void startWork() {
        ArrayList<Toy> allToysForRaffle = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.printf("%nДля выбора необходимого действия введите соответсвующий номер пункта меню: %n" +
                    " 0. Завершить работу приложения%n" +
                    " 1. Создать список игрушек для розыгрыша%n" +
                    " 2. Изменить название игрушки для розыгрыша%n" +
                    " 3. Изменить количество игрушек для розыгрыша конкретного вида игрушек%n" +
                    " 4. Сформировать список разыгранных игрушек%n");

            String inputString = in.nextLine();

            if (inputString.isBlank()) {
                System.out.println("Строка не должна быть пустой. Введите данные\n");
                continue;
            }

            if (inputString.equalsIgnoreCase("0")) {
                System.out.println("Работа завершена.\n");
                break;
            }

            if (inputString.equalsIgnoreCase("1")) {
                getListAllToys(allToysForRaffle);
                continue;
            }

//            if (inputString.equalsIgnoreCase("2")) {
//                changeNameToy(allToysForRaffle);
//                continue;
//            }
//
//            if (inputString.equalsIgnoreCase("3")) {
//                changeNumberToys(allToysForRaffle);
//                continue;
//            }

            if (inputString.equalsIgnoreCase("4")) {
                startRaffle(allToysForRaffle);

            } else {
                System.out.println("Такого пункта меню не существует. Сделайте выбор еще раз\n");
            }
        }
    }

    private static void startRaffle(ArrayList<Toy> allToysForRaffle) {

    }

    private static void getListAllToys(ArrayList<Toy> allToysForRaffle) {
        Scanner in = new Scanner(System.in);
        System.out.printf("%nСколько видов игрушек вы хотите разыграть? Введите цифру: %n");
        String numToysTypes = in.nextLine();

        try {
            int numberToysTypes = Integer.parseInt(numToysTypes);
            if (numberToysTypes > 0) {
                for (int i = 0; i < numberToysTypes; i++) {
                    int id = i + 1;
                    System.out.printf("%nВведите название игрушки для розыгрыша: %n");
                    String name = in.nextLine();
                    System.out.printf("%nВведите количество данных игрушек для розыгрыша: %n");
                    String number = in.nextLine();
                    try {
                        int numberToys = Integer.parseInt(number);
                        Toy toy = new Toy(id, name, numberToys);
                        allToysForRaffle.add(toy);
                    } catch (NumberFormatException e) {
                        System.out.printf("%nВы ввели число не корректно.%n");
                    }
                }
                for (Toy item : allToysForRaffle){
                    item.printToy();
                }
            }
        } catch (NumberFormatException e) {
            System.out.printf("%nВы ввели число не корректно.%n");
        }

    }
}
