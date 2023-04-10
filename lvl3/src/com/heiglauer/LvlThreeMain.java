package com.heiglauer;
import java.util.concurrent.ThreadLocalRandom;
import java.io.*;

public class LvlThreeMain {
    public static void main(String[] args) {
        String file = "lvl3/Files/level3_example";
        try (BufferedReader reader = new BufferedReader(new FileReader(file + ".in"))) {
            String currentLine = reader.readLine();
            String numbers[];
            numbers = currentLine.split(" ");
            int numberOftournaments = Integer.parseInt(numbers[0]);
            int numberOfFighters = Integer.parseInt(numbers[1]);

            for (int tournamentCounter = 0; tournamentCounter < numberOftournaments; tournamentCounter++) {

                currentLine = reader.readLine();
                String fighters[] = currentLine.split(" ");
                String[] parts = fighters[0].split("[^\\d]+");
                int rockFighters = Integer.parseInt(parts[0]);
                parts = fighters[1].split("[^\\d]+");
                int paperFighters = Integer.parseInt(parts[0]);
                parts = fighters[2].split("[^\\d]+");
                int scissorFighters = Integer.parseInt(parts[0]);
                int numberOfFightersInRoundTwo = numberOfFighters / 4;
                int numberOfFightersInRoundOne = numberOfFighters / 2;
                char[] fightersInRoundTwo = new char[numberOfFightersInRoundTwo];
                for (int fighterNumber = 0; fighterNumber < numberOfFightersInRoundTwo; fighterNumber++){
                    int int_random = ThreadLocalRandom.current().nextInt() % 2;
                    if (int_random == 0) {
                        if (paperFighters >=1) {
                            fightersInRoundTwo[fighterNumber] = 'P';
                            paperFighters = paperFighters - 1;
                        }
                        else{
                            fightersInRoundTwo[fighterNumber] = 'S';
                            scissorFighters = paperFighters - 1;
                        }
                    }
                    else{
                        if (scissorFighters >=1) {
                        fightersInRoundTwo[fighterNumber] = 'S';
                        scissorFighters = paperFighters - 1;
                        }
                        else {
                            fightersInRoundTwo[fighterNumber] = 'P';
                            paperFighters = paperFighters - 1;
                        }
                    }
                }
                System.out.println(fightersInRoundTwo);
                System.out.println("Left: S"+scissorFighters+" P"+paperFighters+" R"+rockFighters);
                System.out.println("\n Round One");
                //------------ Round One -----------
                char[] fightersInRoundOne = new char[numberOfFightersInRoundOne];
                int fighterRoundTwoCounter = 0;
                for(int fighterNumber=0; fighterNumber < numberOfFightersInRoundOne; fighterNumber=fighterNumber+2){
                    if(fightersInRoundTwo[fighterRoundTwoCounter] == 'S'){
                        if(scissorFighters>=1){
                            fightersInRoundOne[fighterNumber] = 'S';
                            fightersInRoundOne[fighterNumber+1] = 'S';
                            scissorFighters -= 1;
                        }
                        else{
                            fightersInRoundOne[fighterNumber] = 'S';
                            fightersInRoundOne[fighterNumber+1] = 'P';
                            paperFighters -= 1;
                        }
                    }
                    else{ //Paper
                        if(rockFighters >=1){
                            fightersInRoundOne[fighterNumber] = 'P';
                            fightersInRoundOne[fighterNumber+1] = 'R';
                            rockFighters -= 1;
                        }
                        else{
                            fightersInRoundOne[fighterNumber] = 'P';
                            fightersInRoundOne[fighterNumber+1] = 'P';
                            paperFighters -= 1;
                        }
                    }
                }
                System.out.println(fightersInRoundOne);
                System.out.println("Left: S"+scissorFighters+" P"+paperFighters+" R"+rockFighters);
                System.out.println("\n Round Zero");
                //------------ Finale -----------
                char[] fightersInRoundZero = new char[numberOfFighters];
                int fighterRoundOneCounter = 0;
                for(int fighterNumber=0; fighterNumber < numberOfFighters; fighterNumber=fighterNumber+2){
                    if(fightersInRoundOne[fighterRoundOneCounter] == 'S'){
                        if(scissorFighters>=1){
                            fightersInRoundZero[fighterNumber] = 'S';
                            fightersInRoundZero[fighterNumber+1] = 'S';
                            scissorFighters -= 1;
                        }
                        else{
                            fightersInRoundZero[fighterNumber] = 'S';
                            fightersInRoundZero[fighterNumber+1] = 'P';
                            paperFighters -= 1;
                        }
                    } else if (fightersInRoundOne[fighterRoundOneCounter] == 'P') {
                        if(rockFighters >=1){
                            fightersInRoundZero[fighterNumber] = 'P';
                            fightersInRoundZero[fighterNumber+1] = 'R';
                            rockFighters -= 1;
                        }
                        else{
                            fightersInRoundZero[fighterNumber] = 'P';
                            fightersInRoundZero[fighterNumber+1] = 'P';
                            paperFighters -= 1;
                        }
                    }
                    else{ //Rock
                        if(scissorFighters >=1){
                            fightersInRoundZero[fighterNumber] = 'R';
                            fightersInRoundZero[fighterNumber+1] = 'S';
                            scissorFighters -= 1;
                        }
                        else{
                            fightersInRoundZero[fighterNumber] = 'R';
                            fightersInRoundZero[fighterNumber+1] = 'R';
                            rockFighters -= 1;
                        }
                    }
                    fighterRoundOneCounter++;
                }
                System.out.println(fightersInRoundZero);
                System.out.println("Left: S"+scissorFighters+" P"+paperFighters+" R"+rockFighters);
                System.out.println("Next\n");
            }
        }
        catch (IOException e) {
            System.out.println("File Error");
            e.printStackTrace();
        }
    }
}
