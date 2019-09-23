package com.company;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter how many rows: ");
        int row = scanner.nextInt();
        System.out.println("Enter how many Columns: ");
        int column = scanner.nextInt();
        System.out.println("Enter how many bombs: ");
        int bomb = scanner.nextInt();
        int marker = bomb;
        int win = 0;
        int[][] minefield = new int[row + 2][column + 2];
        int[][] bombCoordinates = new int[bomb][2];
        int[][] choiceCoordinates = new int[bomb][2];
        int n = 0;




        minefield = placeMines(n, row, column, bomb, minefield, bombCoordinates);

//        //for testing prints minefield with x for mines
//        for (int i = 1; i <= row; i++) {
//            System.out.println();
//            for (int j = 1; j <= column; j++) {
//                if(minefield[i][j]!=-1) {
//                    System.out.print(minefield[i][j] + " ");
//                }else{
//                    System.out.print("x" + " ");
//            }
//            }
//
//        }
//
//        //prints minefield x
//        System.out.println();
//        for (int i = 1; i <= row; i++) {
//            System.out.println();
//            for (int j = 1; j <= column; j++) {
//                System.out.print("x"+" ");
//
//            }
//
//        }
//for testing prints minefield with x for mines
        printX(row,row, column);
        //printField(row,row, column, minefield);




        checkBomb(n,row, column, bomb, marker, minefield, bombCoordinates, choiceCoordinates);

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
        }
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

    public static int[][] checkBomb(int n,int row, int column, int bomb, int marker, int[][] minefield, int[][] bombCoordinates, int[][] choiceCoordinates) {

int [][] printArray = new int[3][3];

        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("Enter row between 1 and " + row);
        int rowChoice = scanner.nextInt();
        System.out.println("Enter column between 1 and " + row);
        int columnChoice = scanner.nextInt();

        //if row and column != -1 add them to an array, print the array anything not in the array print x, else boom--------------------------------------------------------------------------------------
        int i =0;

        //figure out recursion for this

            printArray[n][i] = minefield[rowChoice - 1][columnChoice - 1];
            i+=1;
            printArray[n][i] =minefield[rowChoice-1][columnChoice];
            i+=1;
            printArray[n][i] =minefield[rowChoice - 1][columnChoice + 1];
            i=0;

            n+=1;
            printArray[n][i] =minefield[rowChoice][columnChoice - 1];
            i+=1;
            printArray[n][i] =minefield[rowChoice][columnChoice];
            i+=1;
            printArray[n][i] =minefield[rowChoice][columnChoice + 1];
            i=0;

            n+=1;
            printArray[n][i] =minefield[rowChoice + 1][columnChoice-1];
            i+=1;
            printArray[n][i] =minefield[rowChoice + 1][columnChoice];
            i+=1;
            printArray[n][i] =minefield[rowChoice + 1][columnChoice+ 1];
            i=0;



// print the 6 around the position and print them,
        for (int j = 0; j <= printArray.length-1; j++) {

            for (int k = 0; k <= printArray.length-1; k++) {
                if (printArray[j][k] != -1) {
                    System.out.print(printArray[j][k] + " ");
                } else {
                    System.out.print("x" + " ");
                }
            }
                System.out.println();


        }


        System.out.println("Would you like to put a marker down?");
        scanner.nextLine();
        System.out.println("Y or N");
        String YorN = scanner.nextLine().toLowerCase();


        if (YorN.equals("n") && minefield[rowChoice][columnChoice] == -1) {
            System.out.println("Boom!");
            System.exit(-1);
        } else if (YorN.equals("y")||YorN.equals("n") && minefield[rowChoice][columnChoice] != -1) {

            compareCoordinates(n, row,  column,  bomb, marker,minefield, bombCoordinates,choiceCoordinates,  rowChoice,  columnChoice);//rename


        } else {
            System.out.println(minefield[rowChoice][columnChoice]);
            checkBomb(n,row, column, bomb, marker, minefield, bombCoordinates, choiceCoordinates);
        }
        return choiceCoordinates;
    }

    public static int[][] compareCoordinates(int n,int row, int column, int bomb,int marker,int[][] minefield, int[][] bombCoordinates, int[][] choiceCoordinates, int rowChoice, int columnChoice) {


        marker -= 1;

                    int a = 0;
                    int b = 1;
                    choiceCoordinates[n][a] = rowChoice;
                    choiceCoordinates[n][b] = columnChoice;
                    n += 1;

            if(marker>0) {
                checkBomb(n,row, column, bomb, marker, minefield, bombCoordinates, choiceCoordinates);
            }

        return choiceCoordinates;
    }




public static int printX(int n, int row, int column){

    if (column ==0){
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


    public static int printField(int n, int row, int column, int[][] minefield){

        if (column ==0){
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
}







