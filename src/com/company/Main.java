package com.company;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int[] fieldSize = size();
        int row = fieldSize[0];
        int column = fieldSize[1];
        int bomb = fieldSize[2];
        int size = row;
        int marker = bomb;
        int win = 0;
        int[][] minefield = new int[row + 2][column + 2];
        int[][] xField = new int[row + 2][column + 2];;
        int[][] bombCoordinates = new int[bomb][2];
        int[][] choiceCoordinates = new int[bomb][2];
        int [][] printArray = new int[row +2][column + 2];
        int n = 0;




        minefield = placeMines(n, row, column, bomb, minefield, bombCoordinates);
        printX(row,row, column);
        printField(1,size,1, 1, minefield);

        checkBomb(n,size,row, column, bomb, marker, minefield, bombCoordinates, choiceCoordinates,xField, printArray);

       for (int j=0;j<bombCoordinates.length;j++){
           for(int k=0;k<bombCoordinates.length;k++){

               if(Arrays.equals(bombCoordinates[j],choiceCoordinates[j])||Arrays.equals(bombCoordinates[j],choiceCoordinates[k])){
                   win +=1;
                   break;
               }
           }
       }
        if(win == bomb){
            System.out.println("win");
        }else{
            System.out.println("lose");
        }
    }

    public static int[] size(){
        int fieldSize[] = new int[3];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter how many rows: ");
        fieldSize[0] = scanner.nextInt();
        System.out.println("Enter how many Columns: ");
        fieldSize[1] = scanner.nextInt();
        System.out.println("Enter how many bombs: ");
        fieldSize[2] = scanner.nextInt();

        return fieldSize;

    }

    public static int[][] placeMines(int n, int row, int column, int bomb, int[][] field, int[][] bombCoordinates) {


        Random rnd = new Random();

        if (bomb == 0) {
            return field;

        } else {

            int randomRow = rnd.nextInt(row) + 1;
            int randomColumn = rnd.nextInt(column) + 1;

            //already a bomb
            if (field[randomRow][randomColumn] == -1) {
                return placeMines(n, row, column, bomb, field, bombCoordinates);

            } else if (!(field[randomRow][randomColumn] == -1)) {

                field[randomRow][randomColumn] = -1;

                int i = 0;
                int j = 1;
                //adds the bomb coordinates to an array
                bombCoordinates[n][i] = randomRow;
                bombCoordinates[n][j] = randomColumn;

                n += 1;

                if (!(field[randomRow - 1][randomColumn] == -1)) {
                    field[randomRow - 1][randomColumn] += 1;
                }
                if (!(field[randomRow - 1][randomColumn + 1] == -1)) {
                    field[randomRow - 1][randomColumn + 1] += 1;
                }
                if (!(field[randomRow - 1][randomColumn - 1] == -1)) {
                    field[randomRow - 1][randomColumn - 1] += 1;
                }

                if (!(field[randomRow][randomColumn + 1] == -1)) {
                    field[randomRow][randomColumn + 1] += 1;
                }
                if (!(field[randomRow][randomColumn - 1] == -1)) {
                    field[randomRow][randomColumn - 1] += 1;
                }

                if (!(field[randomRow + 1][randomColumn] == -1)) {
                    field[randomRow + 1][randomColumn] += 1;
                }
                if (!(field[randomRow + 1][randomColumn + 1] == -1)) {
                    field[randomRow + 1][randomColumn + 1] += 1;
                }
                if (!(field[randomRow + 1][randomColumn - 1] == -1)) {
                    field[randomRow + 1][randomColumn - 1] += 1;
                }
            }
        }

        return placeMines(n, row, column, (bomb - 1), field, bombCoordinates);
    }

    public static int[][] checkBomb(int n,int size, int row, int column, int bomb, int marker, int[][] minefield, int[][] bombCoordinates, int[][] choiceCoordinates,int[][] xField, int [][] printArray) {



        Scanner scanner = new Scanner(System.in);
        int rowChoice;
        int columnChoice;



        System.out.println("\nWould you like to:\n1. Pick a coordinate\n2. Put a marker down");
        String YorN = scanner.nextLine();


        if (YorN.equals("1")) {

        System.out.println();
        System.out.println("Enter row between 1 and " + row);
        rowChoice = scanner.nextInt();
        System.out.println("Enter column between 1 and " + column);
        columnChoice = scanner.nextInt();

        if(minefield[rowChoice][columnChoice]==-1){
            System.out.println("boom!");
            System.exit(-1);
        }


        //figure out recursion for this

            printArray[rowChoice - 1][columnChoice - 1] = minefield[rowChoice - 1][columnChoice - 1];
            printArray[rowChoice-1][columnChoice] =minefield[rowChoice-1][columnChoice];
            printArray[rowChoice - 1][columnChoice + 1] =minefield[rowChoice - 1][columnChoice + 1];

            printArray[rowChoice][columnChoice - 1] =minefield[rowChoice][columnChoice - 1];
            printArray[rowChoice][columnChoice] =minefield[rowChoice][columnChoice];
            printArray[rowChoice][columnChoice + 1] =minefield[rowChoice][columnChoice + 1];

            printArray[rowChoice + 1][columnChoice-1] =minefield[rowChoice + 1][columnChoice-1];
            printArray[rowChoice + 1][columnChoice] =minefield[rowChoice + 1][columnChoice];
            printArray[rowChoice + 1][columnChoice+ 1] =minefield[rowChoice + 1][columnChoice+ 1];

        xField = printField(1,size,1, 1, printArray);


             checkBomb(n,size,row, column, bomb, marker, minefield, bombCoordinates, choiceCoordinates,xField,printArray);

        } else if (YorN.equals("2")) {

            System.out.println("Pick row between 1 and " + row);
            rowChoice = scanner.nextInt();
            System.out.println("Pick column between 1 and " + column);
            columnChoice = scanner.nextInt();

            compareCoordinates(n,size, row,  column,  bomb, marker,minefield, bombCoordinates,choiceCoordinates,  rowChoice,  columnChoice, xField,printArray);//rename


        } else if (YorN.equals("")){

            checkBomb(n,size,row, column, bomb, marker, minefield, bombCoordinates, choiceCoordinates,xField,printArray);
        }
        return choiceCoordinates;
    }

    public static int[][] compareCoordinates(int n,int size,int row, int column, int bomb,int marker,int[][] minefield, int[][] bombCoordinates, int[][] choiceCoordinates, int rowChoice, int columnChoice, int [][]xField,int [][] printArray) {


        marker -= 1;

                    int a = 0;
                    int b = 1;
                    choiceCoordinates[n][a] = rowChoice;
                    choiceCoordinates[n][b] = columnChoice;
                    n += 1;

            if(marker>0) {
                checkBomb(n,size,row, column, bomb, marker, minefield, bombCoordinates, choiceCoordinates,xField,printArray);
            }

        return choiceCoordinates;
    }




public static int printX(int n, int row, int column){

    if (column ==0){
        System.out.println();
        return 1;
    }
    if(row == 0) {
        System.out.println();
        row =n;
        return printX(n,row,column-1);
    }
    System.out.print("x ");


    return printX(n,row-1,column);
}


public static int[][] printField(int n,int size, int row, int column, int[][] minefield){

        if (row ==size+1){
            return minefield;
        }
        if(column == size+1) {
            System.out.println();
            column =n;
            return printField(n,size,row+1,column,minefield);
        }
        if (minefield[row][column]==-1||minefield[row][column]==0){
            System.out.print("x ");
        }else {

            System.out.print(minefield[row][column] + " ");
        }
        return printField(n,size,row,column+1, minefield);
    }
}







