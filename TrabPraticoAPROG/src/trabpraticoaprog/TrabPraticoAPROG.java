package trabpraticoaprog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TrabPraticoAPROG {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        //declaração de arrays para armazenar informação
        char[] groups = new char[50];
        String[] teams = new String[50];
        int[][] games = new int[6][50];
        /* Matriz que guarda a informação dos jogos da seguinte forma:
        linha 0 - nº de jogos
        linha 1 - nº de vitórias
        linha 2 - nº de empates
        linha 3 - nº de derrotas
        linha 4 - nº de golos marcados
        linha 5 - nº de golos sofridos
        */
        int[] teamScores = new int[50];

        //alinea 1  
        int size = readFile(groups, teams, games);

        //alinea 2
       // fillArray(groups, teams, games, size);

        //alinea 3
        //getTeamScores(teams, games, teamScores, size);

        //alinea 7
        listGoalsLost(teams, games, size);
        
        //alinea 11
        //generateStatistics(games, size);
    }

    public static int menu() {
        System.out.printf("%nMenu:%n"
                + "1. Adicionar informação de nova equipa.%n" //alinea2
                + "2. Ver classificação das equipas por grupo.%n" //alinea5
                + "3. Ver equipas com mais golos marcados.%n" //alinea6
                + "4. Ver equipas com um dado número de golos sofridos.%n" //alinea7
                + "5. Ver equipas com mais golos sofridos que marcados.%n" //alinea8
                + "6. Ver primeiros classificados por grupo.%n" //alinea9
                + "7. Ver informação de uma dada equipa.%n" //alinea10
                + "8. Gerar estatísticas.%n" //alinea11
                + "9. Remover 3º e 4º classificados dos grupos.%n" //alinea12
                + "10. Gerar informações da fase seguinte do campeonato.%n" //alinea13
                + "11. Gerar jogos da fase final.%n" //alinea14
                + "12. Sair%n%n"
                + "Que opção pretende?");
        int option = sc.nextInt();
        return option;
        /* Método ainda não implementado.
        Quando todas as alíneas estiverem feitas irá inserir-se um switch case na main para executar o menu.
         */
    }

    //alinea 1
    public static int readFile(char[] groups, String[] teams, int[][] games) throws Exception {
        Scanner fileScan = new Scanner(new File("../PracticalWork.csv"));
        fileScan.nextLine(); //descarta linha do cabeçalho
        int numLines = 0; //conta as linhas do documento
        while (fileScan.hasNextLine()) {
            String[] line = fileScan.nextLine().split(",");
            groups[numLines] = line[0].charAt(0);
            teams[numLines] = line[1];
            for(int i = 0; i<games.length; i++){
                games[i][numLines] = Integer.parseInt(line[i+2]);
            }
            numLines++;
        }
        fileScan.close();
        //retorna para entrar como comprimento do array
        return numLines;
    }

    //alinea 2
    public static void fillArray(char[] groups, String[] teams, int[][] games, int size) throws Exception {
        System.out.println("Introduza equipa");
        String teamName = sc.nextLine();
        teams[0] = teamName;
        for (int i = 1; i < size; i++) {
            groups[i] = (char) System.in.read();
            //System.out.println("Introduza equipa");
            teams[i] = sc.nextLine();
            //garantir que não existem equipas com nomes iguais
            if (teams[i].equals(teams[i - 1])) {
                //System.out.println("Nome já usado. Introduza outro nome");
                teams[i] = sc.nextLine();
            }
            for (int k = 0; k<games.length; k++){
                games[k][i] = sc.nextInt();
            }
            sc.nextLine();
        }
    }

    //alinea 3
    public static void getTeamScores(String[] teams, int[][] games, int[] teamScores, int size) {
        for (int i = 0; i < size; i++) {
            teamScores[i] = games[1][i] * 3 + games[2][i];
        }
    }

    //alinea 7
    public static void listGoalsLost(String[] teams, int[][] games, int size) {
        System.out.println("Insira o nº de golos sofridos.");
        int numGoals = sc.nextInt();
        boolean numGoalsExists = false;
        for (int i = 0; i < size; i++) {
            if (games[5][i] == numGoals) {
                System.out.println(teams[i]);
                numGoalsExists = true;
            }
        }
        if (numGoalsExists == false) {
            System.out.println("Nenhuma equipa sofreu " + numGoals + " golos.");
        }
    }

    //alinea 11
    public static void generateStatistics(int[][] games, int size) throws FileNotFoundException {
        int gameSum = 0, winSum = 0, tieSum = 0, lossSum = 0, goalsScoredSum = 0, goalsLostSum = 0;
        for (int i = 0; i < size; i++) {
           gameSum += games[0][i];
           winSum += games[1][i];
           tieSum += games[2][i];
           lossSum += games[3][i];
           goalsScoredSum += games[4][i];
           goalsLostSum += games[5][i];
        }
        File statFile = new File("../Statistics.txt");
        PrintWriter printWriter = new PrintWriter(statFile);
        printWriter.printf("Total de jogos=%d%n",gameSum);
        printWriter.printf("Total de vitórias=%d%n",winSum);
        printWriter.printf("Total de empates=%d%n",tieSum);
        printWriter.printf("Total de derrotas=%d%n",lossSum);
        printWriter.printf("Total de golos marcados=%d%n",goalsScoredSum);
        printWriter.printf("Total de golos sofridos=%d%n",goalsLostSum);
        printWriter.printf("Média de golos marcados por jogo=%.1f%n",(double)goalsScoredSum/gameSum);
        printWriter.printf("Média de golos sofridos por jogo=%.1f%n",(double)goalsLostSum/gameSum);   
        printWriter.close();
    }
}
