package trabpraticoaprog;

import java.util.Scanner;

public class TrabPraticoAPROG {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
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
        //alinea 2
        int size = readFile(groups, teams, games, wins, ties, losses, goalsScored, goalsLost);
        fillArray(groups, teams, games, wins, ties, losses, goalsScored, goalsLost, size);
    }

    //alinea 1
    public static int readFile(char[] groups, String[] teams, int[] games, int[] wins, int[] ties, int[] losses, int[] goalsScored, int[] goalsLost) throws Exception {
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
        //retorna para entrar como comprimento do array
        return numLines;
    }

    public static void fillArray(char[] groups, String[] teams, int[] games, int[] wins, int[] ties, int[] losses, int[] goalsScored, int[] goalsLost, int size) throws Exception{
        //System.out.println("Introduza equipa");
        String teamName = sc.nextLine();
        teams[0] = teamName;
        for (int i = 1; i < size; i++) {
            groups[i] = (char)System.in.read();
            //System.out.println("Introduza equipa");
            teams[i] = sc.nextLine();
            //garantir que não existem equipas com nomes iguais
            if (teams[i].equals(teams[i-1])) {
            //System.out.println("Nome já usado. Introduza outro nome");
                teams[i] = sc.nextLine();
            }
            games[i] = sc.nextInt();
            wins[i] = sc.nextInt();
            ties[i] = sc.nextInt();
            losses[i] = sc.nextInt();
            goalsScored[i] = sc.nextInt();
            goalsLost[i] = sc.nextInt();
            sc.nextLine();
        }
    }
}
