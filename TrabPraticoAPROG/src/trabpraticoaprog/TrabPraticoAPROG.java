package trabpraticoaprog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TrabPraticoAPROG {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException, IOException {
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

        /*  switch (menu()) {
            case 1:
                //alinea 2
                fillArray(groups, teams, games, size);
                break;
            case 2:
                //alinea 5
                listPosition();
                break;
            case 3:
                //alinea 6
                maxGoals(teams, games, size);
                break;
            case 4:
                //alinea 7
                listGoalsLost(teams, games, size);
                break;
            case 5:
                //alinea 8
                moreGoalsLost(teams, games, size);
                break;
            case 6:
                //alinea 9
                firstPositions();
                break;
            case 7:
                //alinea10
                listInfo(teams, games, groups, size);
                break;
            case 8:
                //alinea 11
                generateStatistics(games, size);
                break;
            case 9:
                //alinea 12
                deleteTeams(teams, groups, games);
                break;
            case 10:
                //alinea 13
                System.out.println("Nao implementado");
                break;
            case 11:
                //alinea 14
                System.out.println("Nao implementado");
                break;
            case 12:
                break;
            default:
                System.out.println("Opção inválida");
        }*/
        //aliena 3 e 4
        order(getteamScores(teams, games, teamScores, size), groups, size, teams, games);
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
    }

    public static void printHeader() {
        System.out.printf("| Grp | Pos | Equipa          | Pts| J  | V  | E  | D  | GM | GS | GD |%n");
        System.out.printf("|=====|=====|=================|====|====|====|====|====|====|====|====|%n");
    }

    public static void printRow(char grp, int pos, String equipa, int pts, int j, int v, int e, int d, int gm, int gs, int gd) {
        System.out.printf("|%-5s|%5d|%-17s|%4d|%4d|%4d|%4d|%4d|%4d|%4d|%4d|", grp, pos, equipa, pts, j, v, e, d, gm, gs, gd);
    }

    //alinea 1
    public static int readFile(char[] groups, String[] teams, int[][] games) throws FileNotFoundException {
        Scanner fileScan = new Scanner(new File("../PracticalWork.csv"));
        fileScan.nextLine(); //descarta linha do cabeçalho
        int numLines = 0; //conta as linhas do documento
        while (fileScan.hasNextLine()) {
            String[] line = fileScan.nextLine().split(","); //cortar pelas vírgulas
            groups[numLines] = line[0].charAt(0);
            teams[numLines] = line[1];
            for (int i = 0; i < games.length; i++) {
                games[i][numLines] = Integer.parseInt(line[i + 2]);
            }
            numLines++;
        }
        fileScan.close();
        //retorna para entrar como comprimento do array
        return numLines;
    }

    //alinea 2
    //ainda tenho de rever este
    public static void fillArray(char[] groups, String[] teams, int[][] games, int size) throws FileNotFoundException, IOException {
        //System.out.println("Introduza equipa");
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
            for (int k = 0; k < games.length; k++) {
                games[k][i] = sc.nextInt();
            }
            sc.nextLine();
        }
    }

    //alinea 3
    public static int[] getteamScores(String[] teams, int[][] games, int[] teamScores, int size) throws FileNotFoundException {
        for (int i = 0; i < size; i++) {
            teamScores[i] = games[1][i] * 3 + games[2][i];
        }
        return teamScores;
    }

    //alinea 4
    public static void order(int[] teamScores, char[] groups, int size, String[] teams, int[][] games) throws FileNotFoundException {
        //percorrer grupos e ordená-los
        char reference = groups[0], aux, group1, group2;
        //esta parte ainda estou a tentar descobrir como fazer
        for (int i = 1; i < size; i++) {
            if (groups[i] < reference) {
                aux = reference;
                group1 = groups[i];
                group2 = reference;
            } else {
                if (groups[i] == reference) {

                }
            }
        }
        //ordenar dentro de cada grupo
        for (int idx1 = 0; idx1 < size - 1; idx1++) {
            for (int idx2 = idx1 + 1; idx2 < size; idx2++) {
                //verificar pontuação
                if (teamScores[idx2] > teamScores[idx1]) {
                    exchangeInfo(teamScores, teams, games, idx1, idx2);
                } else {
                    //verificar maior nº golos marcados
                    if (teamScores[idx2] == teamScores[idx1]) {
                        if (games[4][idx2] > games[4][idx1]) {
                            exchangeInfo(teamScores, teams, games, idx1, idx2);
                        } else {
                            if (games[4][idx2] == games[4][idx1]) {
                                if (games[5][idx2] < games[5][idx1]) {
                                    exchangeInfo(teamScores, teams, games, idx1, idx2);
                                } else {
                                    if (games[4][idx2] == games[4][idx1]) {
                                        //comparar 1 letra
                                        if (teams[idx2].charAt(0) < teams[idx2].charAt(0)) {
                                            exchangeInfo(teamScores, teams, games, idx1, idx2);
                                        } else {
                                            //caso de a 1 letra ser igual
                                            if (teams[idx2].charAt(0) == teams[idx1].charAt(0)) {
                                                if (teams[idx2].charAt(1) > teams[idx1].charAt(1)) {
                                                    exchangeInfo(teamScores, teams, games, idx1, idx2);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    //método para trocar as linhas completas
    public static void exchangeInfo(int[] teamScores, String[] teams, int[][] games, int idx1, int idx2) {
        //trocar pontuações
        int auxScore = teamScores[idx1];
        teamScores[idx1] = teamScores[idx2];
        teamScores[idx2] = auxScore;
        //trocar equipas
        String auxTeams = teams[idx1];
        teams[idx1] = teams[idx2];
        teams[idx2] = auxTeams;
        //trocar resto das informações
        int[] auxGames = games[idx1];
        games[idx1] = games[idx2];
        games[idx2] = auxGames;
    }

    //alinea 5 - em desenvolvimento
    public static void listPosition() {
        printHeader();
    }

    //alinea 6
    public static void maxGoals(String[] team, int[][] games, int size) throws FileNotFoundException {
        int numberMaxGoals = games[4][0];
        //ciclo for para determinar qual é o maior nº de golos marcados por uma equipa
        for (int i = 1; i < size; i++) {
            if (games[4][i] > numberMaxGoals) {
                numberMaxGoals = games[4][i];
            }
        }
        //percorrer array para listar equipas cujo nº de golos = nº max
        for (int j = 0; j < size; j++) {
            if (games[4][j] == numberMaxGoals) {
                System.out.println(team[j]);
            }
        }
    }

    //alinea 7
    public static void listGoalsLost(String[] teams, int[][] games, int size) throws FileNotFoundException {
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

    //alinea 8
    //compor 8, definir métodos
    public static void moreGoalsLost(String[] teams, int[][] games, int size) throws FileNotFoundException {
        String[] teamGoalsLost = new String[size];
        String aux = "";
        int qtt = 0;
        if (moreScored(games, size, teams) == true) {
            if (qtt != 0) { //se qtt!=0, então equipa tem + golos sofridos
                for (int a = 0; a < teamGoalsLost.length - 1; a++) {
                    for (int b = a + 1; b < teamGoalsLost.length; b++) {
                        String team1 = teamGoalsLost[a];
                        String team2 = teamGoalsLost[b];
                        //comparar 1 letra
                        if (team2.charAt(0) > team1.charAt(0)) {
                            aux = team1;
                            teamGoalsLost[a] = team2;
                            teamGoalsLost[b] = aux;
                        } else {
                            //caso de a 1 letra ser igual
                            if (team2.charAt(0) == team1.charAt(0)) {
                                if (team2.charAt(1) > team1.charAt(1)) {
                                    aux = team1;
                                    teamGoalsLost[a] = team2;
                                    teamGoalsLost[b] = team1;
                                }
                            }
                        }
                    }
                }
            }
            for (int k = 0; k < teamGoalsLost.length; k++) {
                System.out.println("Equipas com mais golos sofridos do que golos marcados:" + "/n" + teamGoalsLost[k]);
            }
        } else {
            System.out.println("Não há equipas com mais golos sofridos do que golos marcados.");
        }
    }

    public static boolean moreScored(int[][] games, int size, String[] teams) {
        boolean moreScored = false;
        String[] teamGoalsLost = new String[size];
        for (int i = 0; i < size; i++) {
            //verificar se equipa tem + golos sofridos e preencher array de equipas com +golos sofridos
            if (games[5][i] > games[4][i]) {
                for (int j = 0; j < size; j++) {
                    teamGoalsLost[j] = teams[i];
                    moreScored = true;
                }
            }
        }
        return moreScored;
    }

    //alinea 9 - em desenvolvimento
    public static void firstPositions() {
        printHeader();

    }

    //alinea 10
    public static void listInfo(String[] teams, int[][] games, char[] groups, int size) {
        for (int i = 0; i < size; i++) {

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
        printWriter.printf("Total de jogos=%d%n", gameSum);
        printWriter.printf("Total de vitórias=%d%n", winSum);
        printWriter.printf("Total de empates=%d%n", tieSum);
        printWriter.printf("Total de derrotas=%d%n", lossSum);
        printWriter.printf("Total de golos marcados=%d%n", goalsScoredSum);
        printWriter.printf("Total de golos sofridos=%d%n", goalsLostSum);
        printWriter.printf("Média de golos marcados por jogo=%.1f%n", (double) goalsScoredSum / gameSum);
        printWriter.printf("Média de golos sofridos por jogo=%.1f%n", (double) goalsLostSum / gameSum);
        printWriter.close();
    }

    //alinea 12
    public static void deleteTeams(String[] teams, char[] groups, int[][] games) {

    }

    //alinea 13 - em desenvolvimento
    public static void generateFinalStage(char[] groups, int[] positions, String[] teams, int[] teamScores) throws FileNotFoundException {
        File finalStageFile = new File("../FinalStage.csv");
        PrintWriter printWriter = new PrintWriter(finalStageFile);
        printWriter.printf("%s,%d,%s,%d", groups, positions, teams, teamScores); //inserir num 'for looop' i=nº equipas na fase seguinte
    }
}
