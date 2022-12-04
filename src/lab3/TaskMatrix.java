package lab3;

import java.util.Random;

public class TaskMatrix {
    private int[][] matrix;

    TaskMatrix()
    {
        this.matrix = new int[1][1];
    }
    TaskMatrix(int rows, int columns) {this.matrix = new int[rows][columns];}

    TaskMatrix(TaskMatrix taskMatrix)
    {
        this.matrix = copyMatrix(taskMatrix.matrix);
    }
    TaskMatrix(int[][] matrix) {this.matrix = copyMatrix(matrix);}

    public static int[][] copyMatrix(int[][] matrix)
    {
        int[][] copiedMatrix = new int[matrix[0].length][];

        for (int i = 0; i < matrix.length; i++) {
            copiedMatrix[i] = new int[matrix[i].length];
            for (int j = 0; j < matrix[i].length; j++) {
                copiedMatrix[i][j] = matrix[i][j];
            }
        }
        return copiedMatrix;
    }

    public void setElemMatrix(int elemMatrix, int rows, int columns) {this.matrix[rows][columns] = elemMatrix;}

    public int getRowsAmount(){return matrix.length;}
    public int getColumnsAmount(int n){return matrix[n].length;}
    public int getElem(int n, int m){return matrix[n][m];}

    public void setRandomMatrix()
    {
        Random random = new Random();
        this.matrix = new int[random.nextInt(100) + 1][random.nextInt(100) + 1];
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[i].length; j++) {
                setElemMatrix(random.nextInt(101), i, j);
            }
        }

    }

    public void writeMatrix()
    {
        int elementAmount = 0;
        int maxColumns = 0;
        for (int i = 0; i < getRowsAmount(); i++) {
            for (int j = 0; j < getColumnsAmount(i); j++) {
                System.out.print(getElem(i, j) + "\t");
                elementAmount++;
                if (maxColumns < j) maxColumns = j;
            }
            System.out.println();
        }
        System.out.println("Число строк = " + getRowsAmount());
        System.out.println("Число столбцов = " + (maxColumns + 1));
        System.out.println("Число элементов = " + elementAmount);
    }

    public void getUpdMatr() {
        int sum = 0;

        for (int i = 0; i < getRowsAmount(); i++) {
            for (int j = 0; j < getColumnsAmount(i); j++) {
                sum += getElem(i, j);
            }
        }

        for (int i = 0; i < getRowsAmount(); i++) {
            for (int j = 0; j < getColumnsAmount(i); j++) {
                this.matrix[i][j] = getElem(i, j) * sum;
            }
        }
    }

    public void delZeroElem() {
        int[][] newMatrix = new int[getRowsAmount()][];
        int count;
        for (int i = 0; i < getRowsAmount(); i++) {
            count = 0;
            for (int j = 0; j < getColumnsAmount(i); j++) {
                if (this.matrix[i][j] != 0) {
                    count++;
                }
            }
            newMatrix[i] = new int[count];
        }

        for (int i = 0; i < getRowsAmount(); i++) {
            count = 0;
            for (int j = 0; j < getColumnsAmount(i); j++) {
                if (this.matrix[i][j] != 0) {
                    newMatrix[i][count] = matrix[i][j];
                    count++;
                }

            }
        }

        this.matrix = newMatrix;
    }

    public void getOneDimMatr() {
        int count = 0;
        for (int i = 0; i < getRowsAmount(); i++) {
            for (int j = 0; j < getColumnsAmount(i); j++) {
                count++;
            }
        }

        int[][] oneDimensionMatrix = new int[1][count];

        count = 0;
        for (int i = 0; i < getRowsAmount(); i++) {
            for (int j = 0; j < getColumnsAmount(i); j++) {
                oneDimensionMatrix[0][count] = this.matrix[i][j];
                count++;
            }
        }
        for (int i = 0; i < oneDimensionMatrix[0].length - 1; i++) {
            for (int j = oneDimensionMatrix[0].length - 1; j > i; j--) {
                if (oneDimensionMatrix[0][j - 1] > oneDimensionMatrix[0][j])
                {
                    int swap = oneDimensionMatrix[0][j - 1];
                    oneDimensionMatrix[0][j - 1] = oneDimensionMatrix[0][j];
                    oneDimensionMatrix[0][j] = swap;
                }
            }
        }

        this.matrix = oneDimensionMatrix;
    }
}
