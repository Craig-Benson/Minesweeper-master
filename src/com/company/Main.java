package com.company;

import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

//        int row =5;
//        int column =7;
//        int bomb =7;

        System.out.println("Enter how many rows: ");
        int row = scanner.nextInt();
        System.out.println("Enter how many Columns: ");
        int column = scanner.nextInt();
        System.out.println("Enter how many bombs: ");
        int bomb =scanner.nextInt();
        int marker = bomb;

        int[][] minefield = new int[row+2][column+2];

        minefield = placeMines(row,column,bomb,minefield);

        for(int i=1;i<=row;i++){
            System.out.println();
            for(int j =1;j<=column;j++){
                System.out.print(minefield[i][j]+" ");

            }
        }

        checkBomb(row,column,marker,minefield);

    }

    public static int[][] placeMines(int row, int column, int bomb, int[][] field){


        Random rnd = new Random();

        if (bomb== 0) {
            return  field;

        }else{

            int randomRow = rnd.nextInt(row)+1;
            int randomColumn = rnd.nextInt(column)+1;

            //already a bomb
            if (field[randomRow][randomColumn] == -1){
                return placeMines(row, column, bomb, field);

            }else if(!(field[randomRow][randomColumn] == -1)){

                field[randomRow][randomColumn] = -1;

                if(!(field[randomRow-1][randomColumn]==-1)){field[randomRow-1][randomColumn]+=1;}
                if(!(field[randomRow-1][randomColumn+1]==-1)){field[randomRow-1][randomColumn+1]+=1;}
                if(!(field[randomRow-1][randomColumn-1]==-1)){field[randomRow-1][randomColumn-1]+=1;}

                if(!(field[randomRow][randomColumn+1]==-1)){field[randomRow][randomColumn+1]+=1;}
                if(!(field[randomRow][randomColumn-1]==-1)){field[randomRow][randomColumn-1]+=1;}

                if(!(field[randomRow+1][randomColumn]==-1)){field[randomRow+1][randomColumn]+=1;}
                if(!(field[randomRow+1][randomColumn+1]==-1)){field[randomRow+1][randomColumn+1]+=1;}
                if(!(field[randomRow+1][randomColumn-1]==-1)){field[randomRow+1][randomColumn-1]+=1;}


            }


        }

        return placeMines(row, column, (bomb-1), field);
    }

public static int checkBomb(int row,int column,int marker,int[][] field){

    int correct=0;

    Scanner scanner = new Scanner(System.in);

    System.out.println("Enter row between 1 and " +row);
    int rowChoice = scanner.nextInt();
    System.out.println("Enter column between 1 and " +row);
    int columnChoice = scanner.nextInt();

    System.out.println("Would you like to put a marker down?");
    System.out.println("Y or N");
    String YorN = scanner.nextLine().toLowerCase();


    if(YorN.equals("n")&&field[rowChoice][columnChoice]==-1){
        System.out.println("Boom!");
        return 0;
    }else if(YorN.equals("y")&&field[rowChoice][columnChoice]==-1) {

        marker -= 1;
        if (marker<=0){
            System.out.println("no markers left, check if you are correct?");

            correct += 1;
    }else{

            System.out.println(field[rowChoice][columnChoice]);
            checkBomb(row, column,marker, field);
        }
    }

    return field[rowChoice][columnChoice];

}

}

