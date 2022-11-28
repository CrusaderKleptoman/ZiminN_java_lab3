package lab3;

import java.util.Scanner;

public class Menu {

    private static TaskMatrix taskMatrix;
    private static TaskText taskText;
    private static int readCommand()
    {
        Scanner scanner = new Scanner(System.in);
        int command = -1;
        try
        {
            command = Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException exception)
        {
            System.out.println("Неправильный формат команды, повторите ввод");
            command = readCommand();
        }
        return command;
    }
    public static void MainMenu()
    {
        int command = -1;
        do {
            System.out.println("_______________________________________________________________");
            System.out.println("Главное меню");
            System.out.println("1 - перейти к работе с матрицей");
            System.out.println("2 - перейти к работе с текстом");
            System.out.println("0 - закрыть программу");
            command = readCommand();

            switch (command){
                case 1:
                    MainMatrixMenu();
                    break;
                case 2:
                    TextMenu();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Неверная команда, повторите ввод");
            }

        } while(command != 0);
    }

    private static void MainMatrixMenu()
    {
        int command = -1;
        do {
            System.out.println("_______________________________________________________________");
            System.out.println("Меню работы с матрицей");
            System.out.println("1 - перейти к вводу матрицы");
            System.out.println("2 - вывести матрицу в консоль");
            System.out.println("3 - сохранить матрицу в файл");
            System.out.println("4 - перейти к работе с задачами");
            System.out.println("0 - вернуться в главное меню");
            command = readCommand();

            switch (command){
                case 1:
                    InputMatrixMenu();
                    break;
                case 2:
                    try {
                        taskMatrix.writeMatrix();
                    }
                    catch (NullPointerException exception)
                    {
                        System.out.println("Матрица ещё не была введена, перейдите в меню ввода матрицы и задайте матрицу");
                    }
                    break;
                case 3:
                    SaveMatrixMenu();
                    break;
                case 4:
                    TaskMatrixMenu();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Неверная команда, повторите ввод");
            }

        } while(command != 0);
    }

    private static void InputMatrixMenu()
    {
        int command = -1;
        do {
            System.out.println("_______________________________________________________________");
            System.out.println("1 - задать матрицу случайно, предел значений 100");
            System.out.println("2 - ввести матрицу через консоль");
            System.out.println("3 - прочитать матрицу из файла");
            System.out.println("4 - прочитать матрицу из бинарного файла");
            System.out.println("0 - вернуться в меню работы с матрицей");

            command = readCommand();

            switch (command){
                case 1:
                    taskMatrix = new TaskMatrix();
                    taskMatrix.setRandomMatrix();
                    break;
                case 2:
                    System.out.println("Введите число строк");
                    Scanner scanner = new Scanner(System.in);
                    int n = Integer.parseInt(scanner.nextLine());

                    System.out.println("Введите число столбцов");
                    int m = Integer.parseInt(scanner.nextLine());
                    taskMatrix = new TaskMatrix(n, m);

                    System.out.println("Введите элементы матрицы");
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < m; j++) {
                            taskMatrix.setElemMatrix(Integer.parseInt(scanner.nextLine()), i, j);
                        }
                    }
                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 0:
                    break;
                default:
                    System.out.println("Неверная команда, повторите ввод");
            }

        } while(command != 0);
    }
    private static void SaveMatrixMenu()
    {
        int command = -1;
        do {
            System.out.println("_______________________________________________________________");
            System.out.println("Меню сохранения матрицы в файл");
            System.out.println("1 - задать название файла");
            System.out.println("2 - сохранить в бинарный файл");
            System.out.println("3 - сохранить в обычный текстовый файл");
            System.out.println("0 - вернуться в меню работы с матрицей");

            command = readCommand();

            switch (command){
                case 1:

                    break;
                case 2:
                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 0:
                    break;
                default:
                    System.out.println("Неверная команда, повторите ввод");
            }

        } while(command != 0);
    }

    private static void TaskMatrixMenu()
    {
        int command = -1;
        do {
            System.out.println("_______________________________________________________________");
            System.out.println("1 - вывести условия задач");
            System.out.println("2 - выполнить задачу A");
            System.out.println("3 - выполнить задачу B");
            System.out.println("4 - выполнить задачу C");
            System.out.println("5 - вывести матрицу в консоль");
            System.out.println("0 - вернуться в меню работы с матрицей");

            command = readCommand();

            switch (command){
                case 1:
                    System.out.println("Задача A");
                    System.out.println("На основе двумерной числовой матрицы необходимо сгенерировать новую матрицу, в которой элементы равны произведению соответствующих элементов исходной матрицы на сумму элементов исходной матрицы");
                    System.out.println("Задача B");
                    System.out.println("Удалить из матрицы все нулевые элементы");
                    System.out.println("Задача C");
                    System.out.println("Сгенерировать новую одномерную матрицу из элементов двумерной преобразованной матрицы, расположив элементы в порядке возрастания");
                    break;
                case 2:
                    try {
                        taskMatrix.getUpdMatr();
                    }
                    catch (NullPointerException exception)
                    {
                        System.out.println("Матрица ещё не была введена, перейдите в меню ввода матрицы и задайте матрицу");
                    }
                    break;
                case 3:
                    try {
                        taskMatrix.delZeroElem();
                    }
                    catch (NullPointerException exception)
                    {
                        System.out.println("Матрица ещё не была введена, перейдите в меню ввода матрицы и задайте матрицу");
                    }
                    break;
                case 4:
                    try {
                        taskMatrix.getOneDimMatr();
                    }
                    catch (NullPointerException exception)
                    {
                        System.out.println("Матрица ещё не была введена, перейдите в меню ввода матрицы и задайте матрицу");
                    }
                    break;
                case 5:
                    try {
                        taskMatrix.writeMatrix();
                    }
                    catch (NullPointerException exception)
                    {
                        System.out.println("Матрица ещё не была введена, перейдите в меню ввода матрицы и задайте матрицу");
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Неверная команда, повторите ввод");
            }

        } while(command != 0);
    }

    private static void TextMenu()
    {

    }


}
