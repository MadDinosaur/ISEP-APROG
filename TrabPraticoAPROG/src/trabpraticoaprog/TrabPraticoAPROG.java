package trabpraticoaprog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class TrabPraticoAPROG {

    static Scanner sc = new Scanner(System.in);

    static final int MAX_TEAMS = 50;
    static final int NUM_COLUMNS = 6;

    public static void main(String[] args) throws FileNotFoundException, IOException {
        //declaração de arrays para armazenar informação
        char[] groups = new char[MAX_TEAMS];
        String[] teams = new String[MAX_TEAMS];
        int[][] games = new int[MAX_TEAMS][NUM_COLUMNS];
        /* Matriz que guarda a informação dos jogos da seguinte forma:
        coluna 0 - nº de jogos
        coluna 1 - nº de vitórias
        coluna 2 - nº de empates
        coluna 3 - nº de derrotas
        coluna 4 - nº de golos marcados
        coluna 5 - nº de golos sofridos
         */
        int[] teamScores = new int[MAX_TEAMS];
        String[] teamsGoalsLost = new String[MAX_TEAMS];

        //alinea 1  
        int size = readFile(groups, teams, games);

        switch (menu()) {
            case 1:
                //alinea 2
                size = fillArray(groups, teams, games, size);
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
                moreGoalsLost(teams, games, size, teamsGoalsLost);
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
        }

        //aliena 3 e 4
        //order(getteamScores(teams, games, teamScores, size), groups, size, teams, games);
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
                + "Que opção pretende? ");
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
            for (int i = 0; i < games[0].length; i++) {
                games[numLines][i] = Integer.parseInt(line[i + 2]);
            }
            numLines++;
        }
        fileScan.close();
        //retorna para entrar como comprimento do array
        return numLines;
    }

    //alinea 2
    public static int fillArray(char[] groups, String[] teams, int[][] games, int size) throws FileNotFoundException, IOException {
        System.out.printf("Por favor introduza a informação no formato%nGrupo,Equipa,Jogos,Vitorias,Empates,Derrotas,GolosMarcados,GolosSofridos%n");
        sc.nextLine();
        String[] line = sc.nextLine().split(",");

        //verificar se a equipa introduzida já existe
        boolean validTeam = true;
        do {
            for (int i = 0; i < size; i++) {
                validTeam = true;
                if (teams[i].equalsIgnoreCase(line[1].trim())) {
                    System.out.println("Equipa já existente. Insira novamente a informação.");
                    validTeam = false;
                    line = sc.nextLine().split(",");
                    sc.nextLine();
                }
            }
        } while (validTeam == false);

        groups[size] = line[0].trim().charAt(0);
        teams[size] = line[1].trim();
        for (int i = 0; i < games[0].length; i++) {
            games[size][i] = Integer.parseInt(line[i + 2].trim());
        }
        return (++size);
    }

    //alinea 3
    public static int[] getteamScores(String[] teams, int[][] games, int[] teamScores, int size) throws FileNotFoundException {
        for (int i = 0; i < size; i++) {
            teamScores[i] = games[i][1] * 3 + games[i][2];
        }
        return teamScores;
    }

    //alinea 4
    public static void order(int[] teamScores, char[] groups, int size, String[] teams, int[][] games) throws FileNotFoundException {
        //percorrer grupos e ordená-los
        orderGroups(groups, size, teamScores, teams, games);
        //sameGroup(groups, size, teamScores, teams, games);
        System.out.println(Arrays.toString(teamScores));
        System.out.println(Arrays.toString(teams));
        System.out.println(Arrays.toString(groups));
    }

    //ordenar grupos
    public static void orderGroups(char[] groups, int size, int[] teamScores, String[] teams, int[][] games) {
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (groups[j] < groups[i]) {
                    exchangeInfo(groups, teamScores, teams, games, i, j);
                }
            }
        }
        System.out.println(Arrays.toString(groups));
        sameGroup(groups, size, teamScores, teams, games);

    }

    public static void sameGroup(char[] groups, int size, int[] teamScores, String[] teams, int[][] games) {
        char currentGroup = 'A';
        for (int i = 0; i < size; i++) {
            if (groups[i] == currentGroup) {
                verifyCriteria(groups, size, teamScores, teams, games);
            } else {
                ++currentGroup;
            }
        }
    }

    public static void verifyCriteria(char[] groups, int size, int[] teamScores, String[] teams, int[][] games) {
        //ordenar dentro de cada grupo
        for (int idx1 = 0; idx1 < size - 1; idx1++) {
            for (int idx2 = idx1 + 1; idx2 < size; idx2++) {
                //verificar pontuação
                if (teamScores[idx2] > teamScores[idx1]) {
                    exchangeInfo(groups, teamScores, teams, games, idx1, idx2);
                } else {
                    //verificar maior nº golos marcados
                    if (teamScores[idx2] == teamScores[idx1]) {
                        if (games[idx2][4] > games[idx1][4]) {
                            exchangeInfo(groups, teamScores, teams, games, idx1, idx2);
                        } else {
                            if (games[idx2][4] == games[idx1][4]) {
                                //verificar menor nº de golos sofridos
                                if (games[idx2][5] < games[idx1][5]) {
                                    exchangeInfo(groups, teamScores, teams, games, idx1, idx2);
                                } else {
                                    if (games[idx2][4] == games[idx1][4]) {
                                        //comparar 1 letra
                                        if (teams[idx2].charAt(0) < teams[idx2].charAt(0)) {
                                            exchangeInfo(groups, teamScores, teams, games, idx1, idx2);
                                        } else {
                                            //caso de a 1 letra ser igual
                                            if (teams[idx2].charAt(0) == teams[idx1].charAt(0)) {
                                                if (teams[idx2].charAt(1) > teams[idx1].charAt(1)) {
                                                    exchangeInfo(groups, teamScores, teams, games, idx1, idx2);
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
    public static void exchangeInfo(char[] groups, int[] teamScores, String[] teams, int[][] games, int idx1, int idx2) {
        //trocar grupos
        char auxGroups = groups[idx1];
        groups[idx1] = groups[idx2];
        groups[idx2] = auxGroups;
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
        int numberMaxGoals = games[0][4];
        //ciclo for para determinar qual é o maior nº de golos marcados por uma equipa
        for (int i = 1; i < size; i++) {
            if (games[i][4] > numberMaxGoals) {
                numberMaxGoals = games[i][4];
            }
        }
        //percorrer array para listar equipas cujo nº de golos = nº max
        for (int j = 0; j < size; j++) {
            if (games[j][4] == numberMaxGoals) {
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
            if (games[i][5] == numGoals) {
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
    public static void moreGoalsLost(String[] teams, int[][] games, int size, String[] teamsGoalsLost) throws FileNotFoundException {
        int numTeams = moreScored(games, size, teams, teamsGoalsLost);
        if (numTeams == 0) {
            System.out.println("Não há equipas com mais golos sofridos do que marcados.");
        } else {
            String[] sortedTeams = new String[numTeams];
            for (int i = 0; i < numTeams; i++) {
                sortedTeams[i] = teamsGoalsLost[i];
            }
            Arrays.sort(sortedTeams);
            for (int i = 0; i < numTeams; i++) {
                System.out.println(sortedTeams[i]);
            }
        }
    }

    public static int moreScored(int[][] games, int size, String[] teams, String[] teamsGoalsLost) {
        int numTeamsGoalsLost = 0;
        for (int i = 0; i < size; i++) {
            //verificar se equipa tem + golos sofridos e preencher array de equipas com + golos sofridos
            if (games[i][5] > games[i][4]) {
                teamsGoalsLost[numTeamsGoalsLost] = teams[i];
                numTeamsGoalsLost++;
            }
        }
        return numTeamsGoalsLost; //devolve o nº de equipas com mais golos sofridos que marcados
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
            gameSum += games[i][0];
            winSum += games[i][1];
            tieSum += games[i][2];
            lossSum += games[i][3];
            goalsScoredSum += games[i][4];
            goalsLostSum += games[i][5];
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

// rever 4
