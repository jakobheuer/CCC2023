package com.heiglauer;

import java.io.*;

public class LvlOneMain {
    public static void main(String[] args) {
        String file ="lvl1/Files/level1_5";

        try(BufferedReader reader = new BufferedReader(new FileReader(file+".in"))){
            int lines = Integer.parseInt(reader.readLine());
            String[][] singleChar = new String[lines][2];
            try{
                PrintWriter out = new PrintWriter(new BufferedWriter(
                        new FileWriter(file+".out")));

                for(int i = 0; i < lines; ++i){
                    String currentLine = reader.readLine();
                    singleChar[i] = currentLine.split("(?!^)");
                    if(singleChar[i][0].equals(singleChar[i][1])){
                        System.out.println(singleChar[i][0]);
                        out.println(singleChar[i][0]);
                    } else if (singleChar[i][0].equals("P") && singleChar[i][1].equals("R")) {
                        System.out.println("P");
                        out.println("P");
                    }
                    else if (singleChar[i][0].equals("R") && singleChar[i][1].equals("S")) {
                        System.out.println("R");
                        out.println("R");
                    }
                    else if (singleChar[i][0].equals("S") && singleChar[i][1].equals("P")) {
                        System.out.println("S");
                        out.println("S");
                    }
                    else{
                        System.out.println(singleChar[i][1]);
                        out.println(singleChar[i][1]);
                    }
                }
                out.close();
            }
            catch (IOException e){
                System.out.println("File Error");
                e.printStackTrace();
            }
        }
        catch (IOException e) {
            System.out.println("File Error");
            e.printStackTrace();
        }
    }
}
