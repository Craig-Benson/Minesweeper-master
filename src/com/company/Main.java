package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
       // HashMap<Integer,Integer> coordinates = new HashMap<>();
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
int[][] coordinates = new int[bomb][2];
        int n=0;
        minefield = placeMines(n,row,column,bomb,minefield,coordinates);

        for(int i=1;i<=row;i++){
            System.out.println();
            for(int j =1;j<=column;j++){
                System.out.print(minefield[i][j]+" ");

            }
        }

        checkBomb(row,column,marker,minefield);

    }

    public static int[][] placeMines(int n,int row, int column, int bomb, int[][] field, int[][] coordinates){


        Random rnd = new Random();

        if (bomb== 0) {
            return  field;

        }else{

            int randomRow = rnd.nextInt(row)+1;
            int randomColumn = rnd.nextInt(column)+1;

            //already a bomb
            if (field[randomRow][randomColumn] == -1){
                return placeMines(n,row, column, bomb, field,coordinates);

            }else if(!(field[randomRow][randomColumn] == -1)){

                field[randomRow][randomColumn] = -1;
//add to array



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

        return placeMines(n,row, column, (bomb-1), field,coordinates);
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
        //add correct choice to array

    }else{

            System.out.println(field[rowChoice][columnChoice]);
            checkBomb(row, column,marker, field);
        }
    return correct;
}



}



