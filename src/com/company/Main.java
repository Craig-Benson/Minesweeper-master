package com.company;

import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int row =10;
        int column =10;
        int bomb =50;
//        System.out.println("Enter how many rows: ");
//        int row = scanner.nextInt();
//        System.out.println("Enter how many Columns: ");
//        int column = scanner.nextInt();
//        System.out.println("Enter how many bombs: ");
//        int bomb =scanner.nextInt();

        int[][] minefield = new int[row+2][column+2];

        minefield = placeMines(row,column,bomb,minefield);

//        System.out.println("Enter row between 1 and " +row);
//        int rowChoice = scanner.nextInt()-1;
//        System.out.println("Enter column between 1 and " +row);
//        int columnChoice = scanner.nextInt()-1;

        for(int i=1;i<=row;i++){
            System.out.println();
            for(int j =1;j<=column;j++){
                System.out.print(minefield[i][j]+" ");

            }
        }



    }

    public static int[][] placeMines(int row, int column, int bomb, int[][] field){


        Random rnd = new Random();

        if (bomb== 0) {
            return  field;

        }else{

            int randomRow = rnd.nextInt(row-1)+1;
            int randomColumn = rnd.nextInt(column-1)+1;

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

//    public  static int[][] surroundMines(int row,int column,int[][] field){
//
//
//
//        if(field[row-1][column]!=-1){field[row-1][column]+=1;}
//        if(field[row-1][column+1]!=-1){field[row-1][column+1]+=1;}
//        if(field[row-1][column-1]!=-1){field[row-1][column-1]+=1;}
//
//        if(field[row][column+1]!=-1){field[row][column+1]+=1;}
//        if(field[row][column-1]!=-1){field[row][column-1]+=1;}
//
//        if(field[row+1][column]!=-1){field[row+1][column]+=1;}
//        if(field[row+1][column+1]!=-1){field[row+1][column+1]+=1;}
//        if(field[row+1][column-1]!=-1){field[row+1][column-1]+=1;}
//
//
//
//
////                += field[row-1][column]== -1 ? field[row-1][column] = -1 : 1;
////        field[row-1][column+1] +=field[row-1][column+1]== -1 ? field[row-1][column+1]= -1: 1;
////        field[row-1][column-1] +=field[row-1][column-1]== -1 ? field[row-1][column-1]= -1: 1;
////
////        field[row][column+1] += field[row][column+1]== -1 ? field[row][column+1]= -1: 1;
////        field[row][column-1] += field[row][column-1]== -1 ? field[row][column-1]= -1: 1;
////
////        field[row+1][column] +=field[row+1][column]== -1 ? field[row+1][column]= -1: 1;
////        field[row+1][column+1] +=field[row+1][column+1]== -1 ? field[row+1][column+1]= -1 : 1;
////        field[row+1][column-1] +=field[row+1][column-1]== -1 ? field[row+1][column-1]= -1 : 1;
//
//        return field;
//    }


}

