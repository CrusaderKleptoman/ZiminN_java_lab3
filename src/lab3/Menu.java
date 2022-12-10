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
            System.out.println("Неверное значение команды, повторите ввод");
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
                    System.out.println("Команды нет в списке, повторите ввод");
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
                    System.out.println("Команды нет в списке, повторите ввод");
            }

        } while (command != 0);
    }

    private static void InputMatrixMenu() {
        int command = -1;
        Scanner scanner = new Scanner(System.in);
        String fileMatrix = "fileMatrix.txt";
        String fileMatrixBinary = "fileMatrixBinary.bin";
        int n;
        int m;
        do {
            n = 0; m = 0;
            System.out.println("_______________________________________________________________");
            System.out.println("Меню ввода матрицы");
            System.out.println("1 - задать матрицу случайно, предел значений ячеек = [1; 100], числа строк и слобцов = [0; 100]");
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
                    try {
                        System.out.println("Введите число строк");
                        while (n <= 0) {
                            n = Integer.parseInt(scanner.nextLine());
                            if (n <= 0) {
                                System.out.println("Число строк не может быть меньше или равно 0, повторите ввод");
                            }
                        }

                        System.out.println("Введите число столбцов");
                        while (m <= 0) {
                            m = Integer.parseInt(scanner.nextLine());
                            if (m <= 0) {
                                System.out.println("Число столбцов не может быть меньше или равно 0, повторите ввод");
                            }
                        }

                        taskMatrix = new TaskMatrix(n, m);
                        System.out.println("Введите элементы матрицы");
                        for (int i = 0; i < n; i++) {
                            for (int j = 0; j < m; j++) {
                                taskMatrix.setElemMatrix(Integer.parseInt(scanner.nextLine()), i, j);
                            }
                        }
                    }
                    catch (NumberFormatException exception)
                {
                    System.out.println("Неверный формат введённых данных, возвращение в меню \n");
                }

                    break;
                case 3:
                    System.out.println("Введите название файла формата txt. Если хотите прочитать из файла по умолчанию(fileMatrix.txt) нажмите Enter");
                    String fileMatrixName = scanner.nextLine();
                    if (!fileMatrixName.isEmpty())
                    {
                        fileMatrix = fileMatrixName;
                    }
                    try(FileReader fileReader = new FileReader(fileMatrix))
                    {
                        StringBuffer stringBufferMatrix = new StringBuffer("");
                        int matrixInFile;
                        while((matrixInFile = fileReader.read()) != 32)
                        {
                            stringBufferMatrix.append((char)matrixInFile);
                        }
                        n = Integer.parseInt(stringBufferMatrix.toString());


                        stringBufferMatrix.setLength(0);

                        while((matrixInFile = fileReader.read()) != 32)
                        {
                            stringBufferMatrix.append((char)matrixInFile);
                        }
                        m = Integer.parseInt(stringBufferMatrix.toString());

                        stringBufferMatrix.setLength(0);

                        taskMatrix = new TaskMatrix(n, m);

                        for (int i = 0; i < n; i++) {
                            for (int j = 0; j < m; j++) {
                                while((matrixInFile = fileReader.read()) != 32 && matrixInFile != -1)
                                {
                                    stringBufferMatrix.append((char)matrixInFile);
                                }

                                taskMatrix.setElemMatrix(Integer.parseInt(stringBufferMatrix.toString()), i, j);
                                stringBufferMatrix.setLength(0);
                            }
                        }

                        System.out.println("Матрица успешно задана и заполнена");

                    }

                    catch (FileNotFoundException exception)
                    {
                        System.out.println("Файл с данными отсутствует, возвращение в меню \n");
                    }
                    catch (NumberFormatException exception)
                    {
                        System.out.println("Неверный формат данных в файле или внезапный конец файла, невозможно полное заполнение матрицы, возвращение в меню \n");
                    }
                    catch (NegativeArraySizeException exception)
                    {
                        System.out.println("Число строк или столбцов, указанное в файле, меньше 0, невозможно создание матрицы, возвращение в меню \n");
                    }
                    catch (IOException e) {
                        System.out.println("Неизвестная ошибка");
                    }

                    break;
                case 4:
                    System.out.println("Введите название файла формата bin. Если хотите прочитать из файла по умолчанию(fileMatrixBinaryName.bin) нажмите Enter");
                    String fileMatrixBinaryName = scanner.nextLine();
                    if (!fileMatrixBinaryName.isEmpty())
                    {
                        fileMatrix = fileMatrixBinaryName;
                    }
                    try(DataInputStream dataInputStream = new DataInputStream(new FileInputStream(fileMatrixBinary)))
                    {
                        n = dataInputStream.readInt();
                        m = dataInputStream.readInt();
                        taskMatrix = new TaskMatrix(n, m);

                        for (int i = 0; i < n; i++) {
                            for (int j = 0; j < m; j++) {
                                taskMatrix.setElemMatrix(dataInputStream.readInt(), i, j);
                            }
                        }

                        System.out.println("Матрица успешно задана и заполнена");
                    }
                    catch (FileNotFoundException exception)
                    {
                        System.out.println("Файл с данными отсутствует, возвращение в меню \n");
                    }
                    catch (NumberFormatException exception)
                    {
                        System.out.println("Неверный формат данных в файле или внезапный конец файла, невозможно заполнение матрицы, возвращение в меню \n");
                    }
                    catch (NegativeArraySizeException exception)
                    {
                        System.out.println("Число строк или столбцов, указанное в файле, меньше 0, невозможно создание матрицы, возвращение в меню \n");
                    }

                    catch (IOException e) {
                        System.out.println("Неизвестная ошибка");
                    }

                    break;
                case 0:
                    break;
                default:
                    System.out.println("Команды нет в списке, повторите ввод");
            }

        } while (command != 0);
    }

    private static void SaveMatrixMenu() {
        int command = -1;
        Scanner scanner = new Scanner(System.in);
        String fileMatrix = "fileMatrix.txt";
        String fileMatrixBinary = "fileMatrixBinary.bin";
        do {
            System.out.println("_______________________________________________________________");
            System.out.println("Меню сохранения матрицы в файл");
            System.out.println("1 - сохранить в бинарный файл");
            System.out.println("2 - сохранить в обычный текстовый файл");
            System.out.println("0 - вернуться в меню работы с матрицей");

            command = readCommand();

            switch (command) {
                case 1:
                    System.out.println("Введите название файла формата bin. Если хотите сохранить в файл по умолчанию(fileMatrixBinaryName.bin) нажмите Enter");
                    String fileMatrixBinaryName = scanner.nextLine();
                    if (!fileMatrixBinaryName.isEmpty())
                    {
                        fileMatrix = fileMatrixBinaryName;
                    }
                    try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(fileMatrixBinary)))
                    {
                        dataOutputStream.writeInt(taskMatrix.getRowsAmount());
                        dataOutputStream.writeInt(taskMatrix.getColumnsAmount(0));
                        for (int i = 0; i < taskMatrix.getRowsAmount(); i++) {
                            for (int j = 0; j < taskMatrix.getColumnsAmount(i); j++) {
                                dataOutputStream.writeInt(taskMatrix.getElem(i, j));
                            }
                        }
                    }
                    catch (NullPointerException exception)
                    {
                        System.out.println("Матрица ещё не была введена, перейдите в меню ввода матрицы и задайте матрицу");
                    }
                    catch (IOException ex)
                    {
                        System.out.println("Неизвестная ошибка");
                    }
                    break;
                case 2:
                    System.out.println("Введите название файла формата txt. Если хотите сохранить в файл по умолчанию(fileMatrix.txt) нажмите Enter");
                    String fileMatrixName = scanner.nextLine();
                    if (!fileMatrixName.isEmpty())
                    {
                        fileMatrix = fileMatrixName;
                    }
                    try (FileWriter fileWriter = new FileWriter(fileMatrix, false)){
                        fileWriter.write(Integer.toString(taskMatrix.getRowsAmount()));
                        fileWriter.write(" ");
                        fileWriter.write(Integer.toString(taskMatrix.getColumnsAmount(0)));

                        for (int i = 0; i < taskMatrix.getRowsAmount(); i++) {
                            for (int j = 0; j < taskMatrix.getColumnsAmount(i); j++) {
                                fileWriter.write(" ");
                                fileWriter.write(Integer.toString(taskMatrix.getElem(i, j)));
                            }
                        }
                        fileWriter.flush();
                    }
                    catch (NullPointerException exception)
                    {
                        System.out.println("Матрица ещё не была введена, перейдите в меню ввода матрицы и задайте матрицу");
                    }
                    catch (IOException ex)
                    {
                        System.out.println("Неизвестная ошибка");
                    }

                    break;
                case 0:
                    break;
                default:
                    System.out.println("Команды нет в списке, повторите ввод");
            }

        } while (command != 0);
    }

    private static void TaskMatrixMenu() {
        int command = -1;
        do {
            System.out.println("_______________________________________________________________");
            System.out.println("Меню работы с задачами матрицы");
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
                    System.out.println("Команды нет в списке, повторите ввод");
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
                    System.out.println("Команды нет в списке, повторите ввод");
            }

        } while (command != 0);
    }

    private static void InputTextMenu()
    {
        int command = -1;
        Scanner scanner = new Scanner(System.in);
        String fileText = "fileText.txt";
        String fileTextBinary = "fileTextBinary.bin";
        StringBuffer stringBufferText;
        do {
            System.out.println("_______________________________________________________________");
            System.out.println("Меню ввода текста");
            System.out.println("1 - ввести текст через консоль");
            System.out.println("2 - прочитать текст из файла");
            System.out.println("3 - прочитать текст из бинарного файла");
            System.out.println("0 - вернуться в меню работы с текстом");

            command = readCommand();

            switch (command){
                case 1:
                    String text = scanner.nextLine();
                    taskText = new TaskText(text);
                    break;
                case 2:
                    System.out.println("Введите название файла формата txt. Если хотите прочитать из файла по умолчанию(fileText.txt) нажмите Enter");
                    String fileTextName = scanner.nextLine();
                    if (!fileTextName.isEmpty())
                    {
                        fileText = fileTextName;
                    }
                    try(FileReader fileReader = new FileReader(fileText))
                    {
                        stringBufferText = new StringBuffer("");
                        int symbolInText;
                        while((symbolInText = fileReader.read()) != -1)
                        {
                            stringBufferText.append((char)symbolInText);
                        }
                        taskText = new TaskText(stringBufferText.toString());
                    }

                    catch (FileNotFoundException exception)
                    {
                        System.out.println("Файл с данными отсутствует, возвращение в меню \n");
                    }
                    catch (IOException ioException)
                    {
                        System.out.println("Неизвестная ошибка");
                    }
                    break;

                case 3:
                    System.out.println("Введите название файла формата bin. Если хотите прочитать из файла по умолчанию(fileTextBinaryName.bin) нажмите Enter");
                    String fileTextBinaryName = scanner.nextLine();
                    if (!fileTextBinaryName.isEmpty())
                    {
                        fileText = fileTextBinaryName;
                    }
                    try(DataInputStream dataInputStream = new DataInputStream(new FileInputStream(fileTextBinary)))
                    {
                        String textInFile = dataInputStream.readUTF();
                        stringBufferText = new StringBuffer("");
                        while(!textInFile.isEmpty())
                        {
                            stringBufferText.append(textInFile);
                            textInFile = dataInputStream.readUTF();
                        }

                        taskText = new TaskText(stringBufferText.toString());


                    }

                    catch (FileNotFoundException exception)
                    {
                        System.out.println("Файл с данными отсутствует, возвращение в меню \n");
                    }
                    catch (IOException ioException)
                    {
                        System.out.println("Неизвестная ошибка");
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Команды нет в списке, повторите ввод");
            }

        } while(command != 0);
    }

    private static void SaveTextMenu()
    {
        Scanner scanner = new Scanner(System.in);
        String fileText = "fileText.txt";
        String fileTextBinary = "fileTextBinary.bin";
        String[] outputText;
        int command = -1;
        do {
            System.out.println("_______________________________________________________________");
            System.out.println("Меню сохранения текста в файл");
            System.out.println("2 - сохранить в бинарный файл");
            System.out.println("3 - сохранить в обычный текстовый файл");
            System.out.println("0 - вернуться в меню работы с текстом");

            command = readCommand();

            switch (command){
                case 1:
                    System.out.println("Введите название файла формата bin. Если хотите прочитать из файла по умолчанию(fileTextBinaryName.bin) нажмите Enter");
                    String fileTextBinaryName = scanner.nextLine();
                    if (!fileTextBinaryName.isEmpty())
                    {
                        fileText = fileTextBinaryName;
                    }
                    try(DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(fileTextBinary)))
                    {
                        outputText = taskText.getText();
                        for (int i = 0; i < outputText.length; i++) {
                            dataOutputStream.writeUTF(outputText[i]);
                            dataOutputStream.writeUTF(" ");
                        }

                    }
                    catch (NullPointerException exception)
                    {
                        System.out.println("Текст ещё не был введён, перейдите в меню ввода текста и задайте текст");
                    }
                    catch (IOException ioException)
                    {
                        System.out.println("Неизвестная ошибка");
                    }
                    break;
                case 2:
                    System.out.println("Введите название файла формата txt. Если хотите прочитать из файла по умолчанию(fileText.txt) нажмите Enter");
                    String fileTextName = scanner.nextLine();
                    if (!fileTextName.isEmpty())
                    {
                        fileText = fileTextName;
                    }
                    try(FileWriter fileWriter = new FileWriter(fileText, false))
                    {
                        outputText = taskText.getText();
                        for (int i = 0; i < outputText.length; i++) {
                            fileWriter.write(outputText[i]);
                            fileWriter.write(" ");
                        }

                        fileWriter.flush();
                    }
                    catch (NullPointerException exception)
                    {
                        System.out.println("Текст ещё не был введён, перейдите в меню ввода текста и задайте текст");
                    }
                    catch (IOException ioException)
                    {
                        System.out.println("Неизвестная ошибка");
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Команды нет в списке, повторите ввод");
            }

        } while(command != 0);
    }

    private static void TaskTextMenu()
    {
        int command = -1;
        do {
            System.out.println("_______________________________________________________________");
            System.out.println("Меню работы с задачами текста");
            System.out.println("1 - вывести условие задачи");
            System.out.println("2 - выполнить задачу");
            System.out.println("3 - вывести текст в консоль");
            System.out.println("0 - вернуться в меню работы с текстом");

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
                    System.out.println("Команды нет в списке, повторите ввод");
            }

        } while(command != 0);
    }
}

