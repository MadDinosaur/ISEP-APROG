package trabpraticoaprog;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TrabPraticoAPROG {

    public static void main(String[] args) throws FileNotFoundException {
        //declaração de arrays para armazenar informação
        char[] groups = new char[50];
        String[] teams = new String[50];
        int[] games = new int[50];
        int[] wins = new int[50];
        int[] ties = new int[50];
        int[] losses = new int[50];
        int[] goalsScored = new int[50];
        int[] goalsLost = new int[50];
        
        //alinea 1  
        readFile(groups, teams, games, wins, ties, losses, goalsScored, goalsLost);
    }

    //alinea 1
    public static void readFile(char[] groups, String[] teams, int[] games, int[] wins, int[] ties, int[] losses, int[] goalsScored, int[] goalsLost) throws FileNotFoundException {
        Scanner fileScan = new Scanner(new File("../PracticalWork.csv"));
        fileScan.nextLine(); //descarta linha do cabeçalho
        int numLines = 0; //conta as linhas do documento
        while (fileScan.hasNextLine()) {
            String[] line = fileScan.nextLine().split(",");
            groups[numLines] = line[0].charAt(0);
            teams[numLines] = line[1];
            games[numLines] = Integer.parseInt(line[2]);
            wins[numLines] = Integer.parseInt(line[3]);
            ties[numLines] = Integer.parseInt(line[4]);
            losses[numLines] = Integer.parseInt(line[5]);
            goalsScored[numLines] = Integer.parseInt(line[6]);
            goalsLost[numLines] = Integer.parseInt(line[7]);
            numLines++;
        }
        fileScan.close();
    }
}
