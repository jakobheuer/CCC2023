package com.heiglauer;

import java.io.*;

public class LvlTwoMain {
    public static void main(String[] args) {
        String file ="lvl2/Files/level2_5";

        try(BufferedReader reader = new BufferedReader(new FileReader(file+".in"))){
            String currentLine = reader.readLine();
            String numbers[];
            numbers = currentLine.split(" ");
            int numberOftournaments = Integer.parseInt(numbers[0]);
            int numberOfFighters = Integer.parseInt(numbers[1]);

            try{PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file+".out")));
                //Logik
                for(int tournamentCounter = 0; tournamentCounter < numberOftournaments; tournamentCounter++){
                    currentLine = reader.readLine();
                    System.out.println("Round: "+tournamentCounter+", Fighters:"+numberOfFighters);
                    char[] fighterChars = currentLine.toCharArray();
                    char[] fightersInRoundTwo = new char[numberOfFighters/2];
                    int fightersInRoundTwoIterator=0;
                    for(int fighterPair = 0; fighterPair < numberOfFighters; fighterPair=fighterPair+2){
                        if(fighterOneWins(Character.toString(fighterChars[fighterPair]),Character.toString(fighterChars[fighterPair+1]))){
                            fightersInRoundTwo[fightersInRoundTwoIterator] = fighterChars[fighterPair];
                        }
                        else{
                            fightersInRoundTwo[fightersInRoundTwoIterator] = fighterChars[fighterPair+1];
                        }
                        fightersInRoundTwoIterator++;
                    }
                    for(int fighterPair = 0; fighterPair < numberOfFighters/2; fighterPair=fighterPair+2){
                        if(fighterOneWins(Character.toString(fightersInRoundTwo[fighterPair]),Character.toString(fightersInRoundTwo[fighterPair+1]))){
                            out.print(fightersInRoundTwo[fighterPair]);
                        }
                        else{
                            out.print(fightersInRoundTwo[fighterPair+1]);
                        }
                    }
                    out.print("\n");
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

    public static boolean fighterOneWins(String fighter1, String fighter2){
        if(fighter1.equals(fighter2)){
            return true;
        } else if (fighter1.equals("P") && fighter2.equals("R")) {
            return true;
        }
        else if (fighter1.equals("R") && fighter2.equals("S")) {
            return true;
        }
        else if (fighter1.equals("S") && fighter2.equals("P")) {
            return true;
        }
        else{
            return false;
        }
    }
}
