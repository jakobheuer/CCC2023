package com.heiglauer;

import java.io.*;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class LvlThreeBruteforce {
    public static void main(String[] args) {
        String file = "lvl3/Files/level3_5";

        try(BufferedReader reader = new BufferedReader(new FileReader(file+".in"))){
            String currentLine = reader.readLine();
            String[] numbers;
            numbers = currentLine.split(" ");
            int numberOftournaments = Integer.parseInt(numbers[0]);
            int numberOfFighters = Integer.parseInt(numbers[1]);
            try {PrintWriter out = new PrintWriter(new BufferedWriter(
                        new FileWriter(file + ".out")));
                for (int tournamentCounter = 0; tournamentCounter < numberOftournaments; tournamentCounter++) {
                    currentLine = reader.readLine();
                    String[] fighters = currentLine.split(" "); //Splits into 3 Parts
                    String[] parts = fighters[0].split("[^\\d]+"); //Retrieves numbers from parts
                    int rockFighters = Integer.parseInt(parts[0]);
                    parts = fighters[1].split("[^\\d]+");
                    int paperFighters = Integer.parseInt(parts[0]);
                    parts = fighters[2].split("[^\\d]+");
                    int scissorFighters = Integer.parseInt(parts[0]);
                    char[] fighterOrder = new char[numberOfFighters];
                    do {
                        fighterOrder = generateFightOrder(numberOfFighters, rockFighters, scissorFighters, paperFighters);
                    } while (isThereAStone(fighterOrder, numberOfFighters));
                    System.out.println("Tournament " + tournamentCounter + ":");
                    System.out.println(fighterOrder);
                    System.out.println();
                    out.println(fighterOrder);
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

    public static boolean fighterOneWins(char fighter1, char fighter2){
        if(fighter1 == fighter2){
            return true;
        } else if (fighter1 == 'P' && fighter2 == 'R') {
            return true;
        }
        else if (fighter1 =='R' && fighter2 =='S') {
            return true;
        }
        else if (fighter1=='S' && fighter2=='P') {
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean isThereAStone(char[] fighterOrder, int numberOfFighters){
        char[] fightersInNextRound = new char[numberOfFighters/2];
        int nextRoundFighterCounter = 0;
        for(int fighterPairCounter = 0; fighterPairCounter < numberOfFighters; fighterPairCounter += 2){
            fightersInNextRound[nextRoundFighterCounter] = fighterOneWins(fighterOrder[fighterPairCounter],fighterOrder[fighterPairCounter+1]) ? fighterOrder[fighterPairCounter]:fighterOrder[fighterPairCounter+1];
            nextRoundFighterCounter++;
        }
        fighterOrder = new char[numberOfFighters/2];
        fighterOrder = fightersInNextRound;
        fightersInNextRound = new char[numberOfFighters/4];
        nextRoundFighterCounter = 0;
        for(int fighterPairCounter = 0; fighterPairCounter < (numberOfFighters / 2); fighterPairCounter += 2){
            fightersInNextRound[nextRoundFighterCounter] = fighterOneWins(fighterOrder[fighterPairCounter],fighterOrder[fighterPairCounter+1]) ? fighterOrder[fighterPairCounter]:fighterOrder[fighterPairCounter+1];
            if(fightersInNextRound[nextRoundFighterCounter] == 'R')
                return true;
            nextRoundFighterCounter++;
        }
        return false;
    }

    public static char[] generateFightOrder(int numberOfFighters, int rockFighters, int scissorFighters, int paperFighters){
        char[] fighterOrder = new char[numberOfFighters];
        for(int fighterCounter = 0; fighterCounter < numberOfFighters; fighterCounter++){
            if(rockFighters >= 3 && paperFighters >= 1){
                fighterOrder[fighterCounter] = 'R';
                fighterOrder[fighterCounter+1] = 'R';
                fighterOrder[fighterCounter+2] = 'R';
                fighterOrder[fighterCounter+3] = 'P';
                fighterCounter = fighterCounter + 3;
                rockFighters -= 3;
                paperFighters -= 1;
            }
            else{
                for(;;){
                    int int_random = ThreadLocalRandom.current().nextInt() % 3;
                    if(int_random == 0 && rockFighters > 0){
                        fighterOrder[fighterCounter] = 'R';
                        rockFighters -= 1;
                        break;
                    } else if (int_random == 1 && scissorFighters > 0) {
                        fighterOrder[fighterCounter] = 'S';
                        scissorFighters -= 1;
                        break;
                    } else if (int_random == 2 && paperFighters > 0) {
                        fighterOrder[fighterCounter] = 'P';
                        paperFighters -= 1;
                        break;
                    }
                }
            }
        }
        return fighterOrder;
    }
}
