package trabpraticoaprog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class TrabPraticoAPROG {

    static Scanner sc = new Scanner(System.in,"ISO-8859-1");

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

        //alinea 1  
        int size = readFile(groups, teams, games);
        getTeamScores(teams, games, teamScores, size);
        order(teamScores, groups, size, teams, games);

        switch (menu()) {
            case 1:
                //alinea 1
                readFile(groups, teams, games);
                break;
            case 2:
                size = fillArray(groups, teams, games, size);
                break;
            case 3:
                //alinea3
                getTeamScores(teams, games, teamScores, size);
                break;
            case 4:
                //alinea4
                getTeamScores(teams, games, teamScores, size);
                order(teamScores, groups, size, teams, games);
                break;
            case 5:
                //alinea 5
                listPositions(groups, teams, teamScores, games, size);
                break;
            case 6:
                //alinea 6
                maxGoals(groups, teams, teamScores, games, size);
                break;
            case 7:
                //alinea 7
                listGoalsLost(groups, teams, teamScores, games, size);
                break;
            case 8:
                //alinea 8
                mostGoalsLost(groups, teams, teamScores, games, size);
                break;
            case 9:
                //alinea 9
                firstPositions(groups, teams, games, size, teamScores);
                break;
            case 10:
                //alinea10
                listInfo(teams, games, groups, teamScores, size);
                break;
            case 11:
                //alinea 11
                generateStatistics(games, size);
                break;
            case 12:
                //alinea 12
                size = deleteTeams(teams, groups, games, teamScores, size);
                break;
            case 13:
                //alinea 13
                size = deleteTeams(teams, groups, games, teamScores, size);
                generateFinalStage(groups, teams, teamScores, size);
                break;
            case 14:
                //alinea 14
                size = deleteTeams(teams, groups, games, teamScores, size);
                generateFinalGames(groups, teams, size);
                break;
            case 15:
                break;
            default:
                System.out.println("Opção inválida");
        }
    }

    public static int menu() {
        System.out.printf("%nMenu:%n"
                + "1. Ler a informação disponível no ficheiro de texto.%n"//alinea1
                + "2. Adicionar informação de nova equipa.%n" //alinea2
                + "3. Calcular e armazenar em memória a pontuação de todas as equipas.%n"//alinea3
                + "4. Calcular e armazenar em memória a classificação de todas as equipas nos respetivos grupos.%n"//alinea4
                + "5. Ver classificação das equipas por grupo.%n" //alinea5
                + "6. Ver equipas com mais golos marcados.%n" //alinea6
                + "7. Ver equipas com um dado número de golos sofridos.%n" //alinea7
                + "8. Ver equipas com mais golos sofridos que marcados.%n" //alinea8
                + "9. Ver primeiros classificados por grupo.%n" //alinea9
                + "10. Ver informação de uma dada equipa.%n" //alinea10
                + "11. Gerar estatísticas.%n" //alinea11
                + "12. Remover 3º e 4º classificados dos grupos.%n" //alinea12
                + "13. Gerar informações da fase seguinte do campeonato.%n" //alinea13
                + "14. Gerar jogos da fase final.%n" //alinea14
                + "15. Sair%n%n"
                + "Que opção pretende? ");
        int option = sc.nextInt();
        return option;
    }

    public static void printHeader() {
        System.out.printf("| Grp | Pos | Equipa          | Pts| J  | V  | E  | D  | GM | GS | GD |%n");
        System.out.printf("|=====|=====|=================|====|====|====|====|====|====|====|====|%n");
    }

    public static void printRow(char group, int position, String team, int teamScore, int[] games) {
        System.out.printf("|%-5s|%5d|%-17s|%4d|%4d|%4d|%4d|%4d|%4d|%4d|%4d|%n", group, position, team, teamScore, games[0], games[1], games[2], games[3], games[4], games[5], games[4] - games[5]);
    }

    //retorna a classificação da equipa com índice <index>
    public static int getPosition(char[] groups, int index) {
        int position = 1;
        while (index > 0 && groups[index] == groups[index - 1]) {
            position++;
            index--;
        }
        return position;
    }

    //alinea 1 - completa
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

    //alinea 2 - completa
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

    //alinea 3 - completa
    public static void getTeamScores(String[] teams, int[][] games, int[] teamScores, int size) throws FileNotFoundException {
        for (int i = 0; i < size; i++) {
            teamScores[i] = games[i][1] * 3 + games[i][2];
        }
    }

    //alinea 4 - completa mas não funciona para grupos com menos que 4 equipas...
    public static void order(int[] teamScores, char[] groups, int size, String[] teams, int[][] games) {
        int idx1 = 0, idx2 = idx1 + 1, groupSize = idx1 + 4; //groupSize - limite até ao qual o grupo não muda (cada grupo tem 4 equipas)

        //ordenar por grupos
        orderGroups(groups, size, teamScores, teams, games);

        //ordena em cadeia enquanto estiver dentro do mesmo grupo
        while (groupSize <= size && groups[idx1] == groups[idx2]) {
            for (idx1 = idx1; idx1 < groupSize - 1; idx1++) {
                for (idx2 = idx1 + 1; idx2 < groupSize; idx2++) {
                    //ordena as pontuações
                    if (teamScores[idx2] > teamScores[idx1]) {
                        exchangeInfo(groups, teamScores, teams, games, idx1, idx2);
                    }
                    //ordena conforme mais golos marcados
                    if (teamScores[idx2] == teamScores[idx1] && games[idx2][4] > games[idx1][4]) {
                        exchangeInfo(groups, teamScores, teams, games, idx1, idx2);
                    }
                    //ordena conforme menos golos sofridos
                    if (teamScores[idx2] == teamScores[idx1] && games[idx2][4] == games[idx1][4] && games[idx2][5] < games[idx1][5]) {
                        exchangeInfo(groups, teamScores, teams, games, idx1, idx2);
                    }
                    //ordena alfabeticamente
                    if (teamScores[idx2] == teamScores[idx1] && games[idx2][4] == games[idx1][4] && games[idx2][5] == games[idx1][5] && teams[idx2].compareTo(teams[idx1]) < 0) {
                        exchangeInfo(groups, teamScores, teams, games, idx1, idx2);
                    }
                }
            }

            groupSize += 4; //aumenta o limite de 4 em 4 para iniciar um novo grupo
            idx1++; //"salta" uma posição para mudar de grupo
        }
    }

    public static void exchangeInfo(char[] groups, int[] teamScores, String[] teams, int[][] games, int idx1, int idx2) {
        int[] auxGames = new int[games[0].length];
        //trocar pontos
        int auxScore = teamScores[idx1];
        teamScores[idx1] = teamScores[idx2];
        teamScores[idx2] = auxScore;
        //trocar inteiros
        auxGames = games[idx1];
        games[idx1] = games[idx2];
        games[idx2] = auxGames;

        //trocar grupos
        char auxGroups = groups[idx1];
        groups[idx1] = groups[idx2];
        groups[idx2] = auxGroups;
        //trocar pais
        String auxTeams = teams[idx1];
        teams[idx1] = teams[idx2];
        teams[idx2] = auxTeams;
    }

    public static void orderGroups(char[] groups, int size, int[] teamScores, String[] teams, int[][] games) {

        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (groups[j] < groups[i]) {
                    exchangeInfo(groups, teamScores, teams, games, i, j);
                }
            }
        }
    }

    //alinea 5 - completa
    public static void listPositions(char[] groups, String[] teams, int[] teamScores, int[][] games, int size) {
        printHeader();
        for (int i = 0; i < size; i++) {
            printRow(groups[i], getPosition(groups, i), teams[i], teamScores[i], games[i]);
        }
    }

    //alinea 6 - completa
    public static void maxGoals(char[] groups, String[] teams, int[] teamScores, int[][] games, int size) {
        int numberMaxGoals = games[0][4];
        //ciclo for para determinar qual é o maior nº de golos marcados por uma equipa
        for (int i = 1; i < size; i++) {
            if (games[i][4] > numberMaxGoals) {
                numberMaxGoals = games[i][4];
            }
        }
        //percorrer array para listar equipas cujo nº de golos = nº max
        printHeader();
        for (int j = 0; j < size; j++) {
            if (games[j][4] == numberMaxGoals) {
                printRow(groups[j], getPosition(groups, j), teams[j], teamScores[j], games[j]);
            }
        }
    }

    //alinea 7 - completa
    public static void listGoalsLost(char[] groups, String[] teams, int[] teamScores, int[][] games, int size) throws FileNotFoundException {
        System.out.println("Insira o nº de golos sofridos.");
        int numGoals = sc.nextInt();
        boolean numGoalsExists = false;
        boolean isHeaderPrinted = false;
        for (int i = 0; i < size; i++) {
            if (games[i][5] == numGoals) {
                while (isHeaderPrinted == false) {
                    printHeader();
                    isHeaderPrinted = true;
                }
                printRow(groups[i], getPosition(groups, i), teams[i], teamScores[i], games[i]);
                numGoalsExists = true;
            }
        }
        if (numGoalsExists == false) {
            System.out.println("Nenhuma equipa sofreu " + numGoals + " golos.");
        }
    }

    //alinea 8 - completa
    public static void mostGoalsLost(char[] groups, String[] teams, int[] teamScores, int[][] games, int size) {
        String[] teamsGoalsLost = new String[MAX_TEAMS];
        int[] teamIndex = new int[size];
        int numTeams = mostScored(games, size, teams, teamsGoalsLost, teamIndex);
        if (numTeams == 0) {
            System.out.println("Não há equipas com mais golos sofridos do que marcados.");
        } else {
            String[] sortedTeams = new String[numTeams]; //criação de novo array apenas com equipas com + golos sofridos que marcados
            for (int i = 0; i < numTeams; i++) {
                sortedTeams[i] = teamsGoalsLost[i];
            }
            sortAlphabetically(sortedTeams, teamIndex, numTeams);
            printHeader();
            for (int i = 0; i < numTeams; i++) {
                int index = teamIndex[i];
                printRow(groups[index], getPosition(groups, index), teams[index], teamScores[index], games[index]);
            }
        }
    }

    //metodo para  verificar quais as equipas com mais golos sofridos
    public static int mostScored(int[][] games, int size, String[] teams, String[] teamsGoalsLost, int[] teamIndex) {
        int numTeamsGoalsLost = 0;
        for (int i = 0; i < size; i++) {
            //verificar se equipa tem + golos sofridos e preencher array de equipas com + golos sofridos
            if (games[i][5] > games[i][4]) {
                teamsGoalsLost[numTeamsGoalsLost] = teams[i];
                teamIndex[numTeamsGoalsLost] = i;
                numTeamsGoalsLost++;
            }
        }
        return numTeamsGoalsLost; //devolve o nº de equipas com mais golos sofridos que marcados
    }

    //metodo para ordenar alfabeticamente as equipas
    public static void sortAlphabetically(String[] sortedTeams, int[] teamIndex, int numTeams) {
        for (int idx1 = 0; idx1 < numTeams - 1; idx1++) {
            for (int idx2 = 1; idx2 < numTeams; idx2++) {
                if (sortedTeams[idx2].compareTo(sortedTeams[idx1]) < 0) {
                    //Troca a equipa
                    String auxTeams = sortedTeams[idx1];
                    sortedTeams[idx1] = sortedTeams[idx2];
                    sortedTeams[idx2] = auxTeams;
                    //Troca o índice
                    int auxIndex = teamIndex[idx1];
                    teamIndex[idx1] = teamIndex[idx2];
                    teamIndex[idx2] = auxIndex;
                }
            }
        }
    }

    //alinea 9 - completa
    public static void firstPositions(char[] groups, String[] teams, int[][] games, int size, int[] teamScores) {
        printHeader();
        for (int i = 0; i < size; i++) {
            if (getPosition(groups, i) == 1) {
                printRow(groups[i], getPosition(groups, i), teams[i], teamScores[i], games[i]);
            }
        }
    }

    //alinea 10 - completa (falta verificar acentos)
    public static void listInfo(String[] teams, int[][] games, char[] groups, int[] teamScores, int size) {
        System.out.println("Qual a equipa que quer listar?");
        sc.nextLine();
        String userDefinedTeam = sc.nextLine().trim();
        int index = 0;
        while (index < size && !teams[index].equalsIgnoreCase(userDefinedTeam)) {
            index++;
        }
        if (index == size) {
            System.out.println("Não foi possível encontrar a equipa introduzida.");
        } else {
            printHeader();
            printRow(groups[index], getPosition(groups, index), teams[index], teamScores[index], games[index]);
        }
    }

    //alinea 11 - completa
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

    //alinea 12 - completa
    public static int deleteTeams(String[] teams, char[] groups, int[][] games, int[] teamScores, int size) {
        System.out.println(Arrays.toString(groups));
        for (int i = 2; i < size; i++) {
            if (getPosition(groups, i) == 3) { //procura o 3º classificado
                size = delete(groups, teams, games, teamScores, size, i); //apaga o 3º e 4º classificados
                i++; //salta para o 3º classificado do próximo grupo
            }
        }
        System.out.println(Arrays.toString(groups));
        return size;
    }

    public static int delete(char[] groups, String[] teams, int[][] games, int[] teamScores, int size, int index) {
        for (int i = index; i < size; i++) {
            groups[i] = groups[i + 2];
            teams[i] = teams[i + 2];
            games[i] = games[i + 2];
            teamScores[i] = teamScores[i + 2];
        }
        groups[size - 1] = groups[size - 2] = ' ';
        teams[size - 1] = teams[size - 2] = "";
        for (int j = 0; j < games[0].length; j++) {
            games[size - 1][j] = games[size - 2][j] = 0;
        }
        teamScores[size - 1] = teamScores[size - 2] = 0;
        size -= 2;
        return size;
    }

    //alinea 13 - completa
    public static void generateFinalStage(char[] groups, String[] teams, int[] teamScores, int size) throws FileNotFoundException {
        File finalStageFile = new File("../FinalStage.csv");
        PrintWriter printWriter = new PrintWriter(finalStageFile);
        for (int i = 0; i < size; i++) {
            printWriter.printf("%s, %d, %s, %d%n", groups[i], getPosition(groups, i), teams[i], teamScores[i]);
        }
        printWriter.close();
    }

    //alinea 14 - o que fazer nos grupos com 2?
    public static void generateFinalGames(char[] groups, String[] teams, int size) throws FileNotFoundException {
        File finalGamesFile = new File("../FinalStageGames.txt");
        PrintWriter printWriter = new PrintWriter(finalGamesFile);
        for (int i = 0; i < size; i += 4) {
            printWriter.printf("%s,%d,%s - %s,%d,%s%n", groups[i], getPosition(groups, i), teams[i], groups[i + 3], getPosition(groups, i + 3), teams[i + 3]);
            printWriter.printf("%s,%d,%s - %s,%d,%s%n", groups[i + 1], getPosition(groups, i + 1), teams[i + 1], groups[i + 2], getPosition(groups, i + 2), teams[i + 2]);
        }
        printWriter.close();
    }
}
