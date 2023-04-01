package raffleOfToys;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        startWork();
    }

    private static void startWork() {
        System.out.printf("%nСоздайте список игрушек для розыгрыша%n");
        ArrayList<Toy> allToys = getListAllToys();
        printAll(allToys);

        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.printf("%nДля выбора необходимого действия введите соответсвующий номер пункта меню: %n" +
                    " 0. Завершить работу приложения%n" +
                    " 1. Добавить новую игрушку в список%n" +
                    " 2. Изменить название игрушки для розыгрыша%n" +
                    " 3. Изменить количество игрушек для розыгрыша конкретного вида игрушек%n" +
                    " 4. Начать розыгрыш игрушек%n");

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
                inputNewToy(allToys);
                printAll(allToys);
                continue;
            }

            if (inputString.equalsIgnoreCase("2")) {
                changeNameToy(allToys);
                printAll(allToys);
                continue;
            }

            if (inputString.equalsIgnoreCase("3")) {
                changeAmountToys(allToys);
                printAll(allToys);
                continue;
            }

            if (inputString.equalsIgnoreCase("4")) {
                ArrayList<String> allToysForRaffle = getListForRaffle(allToys);
                ArrayList<String> listOfGifts = new ArrayList<>();

                while (true) {
                    System.out.printf("%nДля выбора необходимого действия введите соответсвующий номер пункта меню: %n" +
                            " 1. Разыграть игрушку%n" +
                            " 2. Вывести список разыгранных игрушек%n");

                    String inputString2 = in.nextLine();

                    if (inputString2.isBlank()) {
                        System.out.println("Строка не должна быть пустой. Введите данные\n");
                        continue;
                    }

                    if (inputString2.equalsIgnoreCase("1")) {
                        startRaffle(allToysForRaffle, listOfGifts);
                        continue;
                    }

                    if (inputString2.equalsIgnoreCase("2")) {
                        writeStringIntoFile("Итоги очередного розыгрыша игрушек:",false);
                        printListGifts(listOfGifts);
                    } else {
                        System.out.println("Такого пункта меню не существует. Сделайте выбор еще раз\n");
                    }
                }

            } else {
                System.out.println("Такого пункта меню не существует. Сделайте выбор еще раз\n");
            }
        }
    }

    private static ArrayList<String> getListForRaffle(ArrayList<Toy> allToys) {
        ArrayList<String> allToysForRaffle = new ArrayList<>();
        for (Toy item : allToys) {
            String toyName = item.getNameOfToy();
            int toyAmount = item.getNumberOfPrizes();
            for (int i = 0; i < toyAmount; i++) {
                allToysForRaffle.add(toyName);
            }
        }
        return allToysForRaffle;
    }
    private static void startRaffle(ArrayList<String> allToysForRaffle, ArrayList<String> listOfGifts) {
        if (allToysForRaffle.size() == 0) {
            System.out.println("Все игрушки разыграны! Перейдите к печати списка");
        } else {
            Random random = new Random();
            String gift = allToysForRaffle.remove(random.nextInt(allToysForRaffle.size()));
            listOfGifts.add(gift);
            System.out.printf("В этом туре выпала %s%n", gift);
            System.out.println("Список разыгранных игрушек на данный момент:");
            for (String item : listOfGifts) {
                System.out.println(item);
            }
        }
    }

    private static void printListGifts(ArrayList<String> listOfGifts) {
        if (listOfGifts.size() == 0) {
            System.out.println("Вы не разыграли ни одной игрушки!");
        } else {
            for (int i = 0; i < listOfGifts.size(); i++) {
                String temp = String.valueOf(i + 1);
                System.out.printf("Подарок %s -> игрушка %s%n", temp, listOfGifts.get(i));
                writeStringIntoFile("Подарок " + temp + " -> игрушка " + listOfGifts.get(i), true);
            }
            System.exit(0);
        }
    }

    private static void writeStringIntoFile(String str, boolean flag) {
        try (FileWriter fw = new FileWriter("src/main/java/raffleOfToys/ListOfGifts.txt", flag)) {
            fw.write(str);
            fw.append('\n');
            fw.flush();
        } catch (IOException ex) {
            System.out.println("Запись в файл не удалась!");
        }
    }

    private static ArrayList<Toy> getListAllToys() {
        ArrayList<Toy> allToys = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.printf("Сколько видов игрушек вы хотите разыграть? Введите цифру: %n");
            String numToysTypes = in.nextLine();
            try {
                int numberToysTypes = Integer.parseInt(numToysTypes);
                if (numberToysTypes > 0) {
                    for (int i = 0; i < numberToysTypes; i++) {
                        inputNewToy(allToys);
                    }
                    break;
                } else {
                    System.out.printf("%nВы ввели некорректное число.%n");
                }
            } catch (NumberFormatException e) {
                System.out.printf("%nВы ввели некорректные данные.%n");
            }
        }
        return allToys;
    }

    private static void printAll(ArrayList<Toy> allToys) {
        System.out.println("Список игрушек для розыгрыша: ");
        for (Toy item : allToys) {
            item.printToy();
        }
    }

    private static void inputNewToy(ArrayList<Toy> allToys) {
        System.out.printf("%nВведите название новой игрушки: %n");
        String toyName = nameFromUser();
        int toyAmount = amountFromUser();
        Toy toy = new Toy(allToys.size() + 1, toyName, toyAmount);
        allToys.add(toy);
    }


    private static int amountFromUser() {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.printf("%nВведите количество игрушек: %n");
            String amount = in.nextLine();
            try {
                int amountToys = Integer.parseInt(amount);
                if (amountToys > 0) {
                    return amountToys;
                } else {
                    System.out.printf("%nВы ввели некорректное число.%n");
                }
            } catch (NumberFormatException e) {
                System.out.printf("%nВы ввели некорректные данные.%n");
            }
        }
    }

    private static void changeAmountToys(ArrayList<Toy> allToys) {
        System.out.printf("%nВведите название игрушки, количество которой хотите изменить: %n");
        String toyName = nameFromUser();
        int count = 0;
        for (Toy item : allToys) {
            if (item.getNameOfToy().equalsIgnoreCase(toyName)) {
                int amountToy = amountFromUser();
                item.setNumberOfPrizes(amountToy);
                count++;
            }
        }
        if (count == 0) {
            System.out.printf("Такой игрушки не найдено%n");
        }
    }


    private static String nameFromUser() {
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        while (name.isBlank()) {
            System.out.printf("Строка не должна быть пустой. Попробуйте ещё раз!%n");
            System.out.printf("%nВведите название игрушки: %n");
            name = in.nextLine();
        }
        return name;
    }

    private static void changeNameToy(ArrayList<Toy> allToys) {
        System.out.printf("Введите название игрушки, которое хотите изменить: %n");
        String toyName = nameFromUser();
        int count = 0;
        for (Toy item : allToys) {
            if (item.getNameOfToy().equalsIgnoreCase(toyName)) {
                System.out.printf("Введите новое название игрушки: %n");
                String toyNewName = nameFromUser();
                item.setNameOfToy(toyNewName);
                count++;
            }
        }
        if (count == 0) {
            System.out.printf("Такой игрушки не найдено%n");
        }
    }
}
