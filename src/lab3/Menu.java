package lab3;

import java.io.*;
import java.util.Scanner;

public class Menu {

    private static TaskMatrix taskMatrix;
    private static TaskText taskText;

    private static int readCommand() {
        Scanner scanner = new Scanner(System.in);
        int command = -1;
        try {
            command = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException exception) {
            System.out.println("Неправильный формат команды, повторите ввод");
            command = readCommand();
        }
        return command;
    }

    public static void MainMenu() {
        int command = -1;
        do {
            System.out.println("_______________________________________________________________");
            System.out.println("Главное меню");
            System.out.println("1 - перейти к работе с матрицей");
            System.out.println("2 - перейти к работе с текстом");
            System.out.println("0 - закрыть программу");
            command = readCommand();

            switch (command) {
                case 1:
                    MainMatrixMenu();
                    break;
                case 2:
                    MainTextMenu();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Неверная команда, повторите ввод");
            }

        } while (command != 0);
    }

    private static void MainMatrixMenu() {
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

            switch (command) {
                case 1:
                    InputMatrixMenu();
                    break;
                case 2:
                    try {
                        taskMatrix.writeMatrix();
                    } catch (NullPointerException exception) {
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

        } while (command != 0);
    }

    private static void InputMatrixMenu() {
        int command = -1;
        int n;
        int m;
        do {
            System.out.println("_______________________________________________________________");
            System.out.println("1 - задать матрицу случайно, предел значений ячеек, числа строк и слобцов = [1; 100]");
            System.out.println("2 - ввести матрицу через консоль");
            System.out.println("3 - прочитать матрицу из файла");
            System.out.println("4 - прочитать матрицу из бинарного файла");
            System.out.println("0 - вернуться в меню работы с матрицей");

            command = readCommand();

            switch (command) {
                case 1:
                    taskMatrix = new TaskMatrix();
                    taskMatrix.setRandomMatrix();
                    break;
                case 2:
                    System.out.println("Введите число строк");
                    Scanner scanner = new Scanner(System.in);
                    n = Integer.parseInt(scanner.nextLine());

                    System.out.println("Введите число столбцов");
                    m = Integer.parseInt(scanner.nextLine());
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
                    try(DataInputStream dataInputStream = new DataInputStream(new FileInputStream("fileMatrix.txt")))
                    {
                        n = dataInputStream.readInt();
                        m = dataInputStream.readInt();
                        taskMatrix = new TaskMatrix(n, m);

                        for (int i = 0; i < n; i++) {
                            for (int j = 0; j < m; j++) {
                                taskMatrix.setElemMatrix(dataInputStream.readInt(), i, j);
                            }
                        }

                    }
                    catch (FileNotFoundException exception)
                    {
                        System.out.printf("Файл не найден \n");
                    }
                    catch(IOException ex){

                        System.out.println(ex.getMessage());
                    }


                    break;
                case 0:
                    break;
                default:
                    System.out.println("Неверная команда, повторите ввод");
            }

        } while (command != 0);
    }

    private static void SaveMatrixMenu() {
        int command = -1;
        String fileName = "fileMatrix.txt";
        do {
            System.out.println("_______________________________________________________________");
            System.out.println("Меню сохранения матрицы в файл");
            System.out.println("1 - задать название файла");
            System.out.println("2 - сохранить в бинарный файл");
            System.out.println("3 - сохранить в обычный текстовый файл");
            System.out.println("0 - вернуться в меню работы с матрицей");

            command = readCommand();

            switch (command) {
                case 1:

                    break;
                case 2:
                    try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(fileName)))
                    {
                        dataOutputStream.writeInt(taskMatrix.getRowsAmount());
                        dataOutputStream.writeInt(taskMatrix.getColumnsAmount(0));
                        for (int i = 0; i < taskMatrix.getRowsAmount(); i++) {
                            for (int j = 0; j < taskMatrix.getColumnsAmount(i); j++) {
                                dataOutputStream.writeInt(taskMatrix.getElem(i, j));
                            }
                        }
                    }
                    catch (IOException ex)
                    {
                        System.out.println("Ошибка");
                    }
                    break;
                case 3:
                    try (FileWriter fileWriter = new FileWriter(fileName, false)){
                        fileWriter.write(Integer.toString(taskMatrix.getRowsAmount()));
                        fileWriter.write(" ");
                        fileWriter.write(Integer.toString(taskMatrix.getColumnsAmount(0)));
                        fileWriter.write(" ");

                        for (int i = 0; i < taskMatrix.getRowsAmount(); i++) {
                            for (int j = 0; j < taskMatrix.getColumnsAmount(i); j++) {
                                fileWriter.write(Integer.toString(taskMatrix.getElem(i, j)));
                                fileWriter.write(" ");
                            }
                        }
                        fileWriter.flush();
                    }
                    catch (IOException ex)
                    {
                        System.out.println("Ошибка");
                    }

                    break;
                case 4:

                    break;
                case 0:
                    break;
                default:
                    System.out.println("Неверная команда, повторите ввод");
            }

        } while (command != 0);
    }

    private static void TaskMatrixMenu() {
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

            switch (command) {
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
                    } catch (NullPointerException exception) {
                        System.out.println("Матрица ещё не была введена, перейдите в меню ввода матрицы и задайте матрицу");
                    }
                    break;
                case 3:
                    try {
                        taskMatrix.delZeroElem();
                    } catch (NullPointerException exception) {
                        System.out.println("Матрица ещё не была введена, перейдите в меню ввода матрицы и задайте матрицу");
                    }
                    break;
                case 4:
                    try {
                        taskMatrix.getOneDimMatr();
                    } catch (NullPointerException exception) {
                        System.out.println("Матрица ещё не была введена, перейдите в меню ввода матрицы и задайте матрицу");
                    }
                    break;
                case 5:
                    try {
                        taskMatrix.writeMatrix();
                    } catch (NullPointerException exception) {
                        System.out.println("Матрица ещё не была введена, перейдите в меню ввода матрицы и задайте матрицу");
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Неверная команда, повторите ввод");
            }

        } while (command != 0);
    }

    private static void MainTextMenu() {
        int command = -1;
        do {
            System.out.println("_______________________________________________________________");
            System.out.println("Меню работы с текстом");
            System.out.println("1 - перейти к вводу текста");
            System.out.println("2 - вывести текст в консоль");
            System.out.println("3 - сохранить текст в файл");
            System.out.println("4 - перейти к работе с задачами");
            System.out.println("0 - вернуться в главное меню");
            command = readCommand();

            switch (command) {
                case 1:
                    InputTextMenu();
                    break;
                case 2:
                    try {
                        String[] outputText = taskText.getText();
                        for (int i = 0; i < outputText.length; i++) {
                            System.out.print(outputText[i] + " ");
                        }
                        System.out.println();
                    } catch (NullPointerException exception) {
                        System.out.println("Текст ещё не был введён, перейдите в меню ввода текста и задайте текст");
                    }
                    break;
                case 3:
                    SaveTextMenu();
                    break;
                case 4:
                    TaskTextMenu();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Неверная команда, повторите ввод");
            }

        } while (command != 0);
    }

    private static void InputTextMenu()
    {
        int command = -1;
        String fileText = "fileText.txt";
        do {
            System.out.println("_______________________________________________________________");
            System.out.println("1 - ввести текст через консоль");
            System.out.println("2 - прочитать текст из файла");
            System.out.println("3 - прочитать текст из бинарного файла");
            System.out.println("0 - вернуться в меню работы с матрицей");

            command = readCommand();

            switch (command){
                case 1:
                    Scanner scanner = new Scanner(System.in);
                    //scanner.nextLine();
                    String text = scanner.nextLine();
                    taskText = new TaskText(text);
                    break;
                case 2:
                    try(FileReader fileReader = new FileReader(fileText))
                    {
                        StringBuffer stringBufferText = new StringBuffer("");
                        int symbolInText;
                        while((symbolInText = fileReader.read()) != -1)
                        {
                            stringBufferText.append((char)symbolInText);
                        }
                        taskText = new TaskText(stringBufferText.toString());

                    }
                    catch (IOException ioException)
                    {
                        System.out.println("Ошибка");
                    }
                    break;
                case 3:

                    break;
                case 0:
                    break;
                default:
                    System.out.println("Неверная команда, повторите ввод");
            }

        } while(command != 0);
    }


    private static void SaveTextMenu()
    {
        String fileText = "fileText.txt";
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
                    String[] outputText = taskText.getText();
                    try(FileWriter fileWriter = new FileWriter(fileText, false))
                    {
                        for (int i = 0; i < outputText.length; i++) {
                            fileWriter.write(outputText[i]);
                            fileWriter.write(" ");
                        }

                        fileWriter.flush();
                    }
                    catch (IOException ioException)
                    {
                        System.out.println("Ошибка");
                    }
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

    private static void TaskTextMenu()
    {
        int command = -1;
        do {
            System.out.println("_______________________________________________________________");
            System.out.println("1 - вывести условие задачи");
            System.out.println("2 - выполнить задачу");
            System.out.println("3 - вывести текст в консоль");
            System.out.println("0 - вернуться в меню работы с матрицей");

            command = readCommand();

            switch (command){
                case 1:
                    System.out.println("Задача");
                    System.out.println("Найти количество слов в предложении стоящих между самым коротким и самым длинным словом");
                    break;
                case 2:
                    try {
                        System.out.printf("Количество слов между самым длинным словом и самым коротким = %d \n", taskText.getNumOfWord());
                    }
                    catch (NullPointerException exception)
                    {
                        System.out.println("Текст ещё не был введён, перейдите в меню ввода текста и задайте текст");
                    }
                    break;
                case 3:
                    try {
                        taskText.getText();
                    }
                    catch (NullPointerException exception)
                    {
                        System.out.println("Текст ещё не был введён, перейдите в меню ввода текста и задайте текст");
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Неверная команда, повторите ввод");
            }

        } while(command != 0);
    }
}

