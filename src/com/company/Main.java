package com.company;

import java.util.ArrayList;
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


        //for testing prints minefield
        for (int i = 1; i <= row; i++) {
            System.out.println();
            for (int j = 1; j <= column; j++) {
                System.out.print(minefield[i][j]+" ");

            }

        }

        //prints minefield x
        for (int i = 1; i <= row; i++) {
            System.out.println();
            for (int j = 1; j <= column; j++) {
                System.out.print("x"+" ");

            }

        }


        checkBomb(n,row, column, bomb, marker, minefield, bombCoordinates, choiceCoordinates);

       for (int j=0;j<bombCoordinates.length;j++){
           for(int k=0;k<bombCoordinates.length;k++){
            int a =0;
            int b=1;

               if(Arrays.equals(bombCoordinates[j],choiceCoordinates[j])||Arrays.equals(bombCoordinates[j],choiceCoordinates[k])){
                   win +=1;
                   break;
               }
           }
       }
        if(win ==bomb){
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

    public static int[][] checkBomb(int n,int row, int column, int bomb, int marker, int[][] field, int[][] bombCoordinates, int[][] choiceCoordinates) {



        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("Enter row between 1 and " + row);
        int rowChoice = scanner.nextInt();
        System.out.println("Enter column between 1 and " + row);
        int columnChoice = scanner.nextInt();
        
        //printing map after pick ---------------------------------------------------------

        if (!(field[rowChoice - 1][columnChoice] == -1)) {
            System.out.println(field[rowChoice - 1][columnChoice]);
        }
        if (!(field[row - 1][column + 1] == -1)) {
            field[row - 1][column + 1] += 1;
        }
        if (!(field[row - 1][column - 1] == -1)) {
            field[row - 1][column - 1] += 1;
        }

        if (!(field[row][column + 1] == -1)) {
            field[row][column + 1] += 1;
        }
        if (!(field[row][column - 1] == -1)) {
            field[row][column - 1] += 1;
        }

        if (!(field[row + 1][column] == -1)) {
            field[row + 1][column] += 1;
        }
        if (!(field[row + 1][column + 1] == -1)) {
            field[row + 1][column + 1] += 1;
        }
        if (!(field[row + 1][column - 1] == -1)) {
            field[row + 1][column - 1] += 1;
        }

        System.out.println("Would you like to put a marker down?");
        scanner.nextLine();
        System.out.println("Y or N");
        String YorN = scanner.nextLine().toLowerCase();


        if (YorN.equals("n") && field[rowChoice][columnChoice] == -1) {
            System.out.println("Boom!");
            System.exit(-1);
        } else if (YorN.equals("y")||YorN.equals("n") && field[rowChoice][columnChoice] != -1) {

            compareCoordinates(n, row,  column,  bomb, marker,field, bombCoordinates,choiceCoordinates,  rowChoice,  columnChoice);//rename


        } else {
            System.out.println(field[rowChoice][columnChoice]);
            checkBomb(n,row, column, bomb, marker, field, bombCoordinates, choiceCoordinates);
        }
        return choiceCoordinates;
    }

    public static int[][] compareCoordinates(int n,int row, int column, int bomb,int marker,int[][] field, int[][] bombCoordinates, int[][] choiceCoordinates, int rowChoice, int columnChoice) {


        marker -= 1;

                    int a = 0;
                    int b = 1;
                    choiceCoordinates[n][a] = rowChoice;
                    choiceCoordinates[n][b] = columnChoice;
                    n += 1;

            if(marker>0) {
                checkBomb(n,row, column, bomb, marker, field, bombCoordinates, choiceCoordinates);
            }

        return choiceCoordinates;
    }


}



