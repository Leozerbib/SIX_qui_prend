package org.example.mechanic;
import org.example.AI.AI;
import org.example.method;
import org.example.object.card;

import java.util.*;

import static org.example.object.card.Allcarte;
import static org.example.object.card.cartes;


public class Method {
    static Scanner sc = new Scanner(System.in);
    public static card Card0 = new card(0, 0);
    public static int nbr_joueur;
    public static card[][] rangees ;

    public static List<card> main;
    public static List<card> bin;
    public static List<List<card>> joueurs = new ArrayList<>();
    public static List<List<card>> joueursPli = new ArrayList<>();
    public static void regle(){
        method.printTitle("Regle du jeu :");
        System.out.println(
                "Nombre de joueurs : 2 à 10 joueurs\n" +
                        "\n" +
                        "But du jeu : Éviter de ramasser des têtes de bœufs (représentées par des cartes) qui ont une valeur en points. Le joueur avec le moins de points à la fin de la partie gagne.\n" +
                        "\n" +
                        "Déroulement du jeu :\n" +
                        "\n" +
                        "1 . Distribuez 10 cartes à chaque joueur.\n" +
                        "2 . Placez quatre cartes au centre de la table pour commencer le jeu.\n" +
                        "3 . Les joueurs choisissent une carte de leur main et la placent face cachée sur la table.\n" +
                        "4 . Lorsque tous les joueurs ont choisi leur carte, ils la révèlent simultanément.\n" +
                        "5 . Les cartes sont alors placées sur la table en ordre croissant (de la plus petite à la plus grande) en respectant les règles suivantes :\n" +
                        "\n\n     - Les cartes doivent être placées sur la même ligne que la carte qui a la plus petite valeur et doit être inférieure à la carte la plus grande.\n" +
                        "\n     - Si une carte est supérieure à toutes les cartes sur la table, elle doit commencer une nouvelle ligne.\n" +
                        "\n     - Si une carte est égale à une ou plusieurs cartes sur la table, elle doit être placée à droite de ces cartes.\n\n" +
                        "6 . Si un joueur doit placer sa carte sur une ligne qui a déjà six cartes, il doit prendre la ligne complète et toutes les cartes de la ligne sont ajoutées à sa pile de têtes de bœufs.\n" +
                        "7 . Les joueurs répètent ce processus pour chaque tour jusqu'à ce que toutes les cartes de la main de chaque joueur soient jouées.\n" +
                        "8 . La partie se termine lorsque la dernière carte est jouée. Les joueurs comptent alors les points de leurs têtes de bœufs. Chaque tête de bœuf vaut de 1 à 7 points, selon le numéro de la carte. Le joueur avec le moins de points gagne la partie. \n\n");
        method.enterContinue();
        method.clearConsole();
    }

    public static void start(){

        Random random = new Random();
        card.cart();
        // Mélanger les cartes
        Collections.shuffle(cartes);

        System.out.println("combien de joueur etes vous :");
        int joueur= sc.nextInt();
        nbr_joueur=nbr_joueur+1;
        // Distribuer les cartes aux joueurs
        for (int i = 0; i < nbr_joueur; i++) {
            main = new ArrayList<>();
            bin = new ArrayList<>();
            joueurs.add(main);
            joueursPli.add(bin);
        }
        for (int j = 0; j < 10; j++) {
            method.printLine(20);
            for (int i = 0; i < nbr_joueur; i++) {
                joueurs.get(i).add(cartes.get(0));
                cartes.remove(cartes.get(0));
                show(i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            method.printLine(20);
            method.clearConsole();
        }
        for (int i = 0; i <nbr_joueur ; i++) {
            show(i);
        }
        System.out.println();

    }
    public static void GameLogic(){
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        // Jouer au  jeu
        System.out.println();
        init();
        Arrays.toString(rangees);
        while (joueurs.get(nbr_joueur-1).isEmpty()==false){
            game();
        }
        List<Integer> Score = new ArrayList<>();;
        for (int i = 0;i<nbr_joueur;i++){
            int point = 0;
            for (int j = 0; j<joueursPli.get(i).size();j++){
                point += joueursPli.get(i).get(j).getNbr_Taureau();
            }
            Score.add(point);
            System.out.println("Nombre de taureau joueurs " + i + " : " + point);
        }
        method.enterContinue();
        method.clearConsole();
        method.printLine(40);
        afficherElementPlusPetit(Score);
        afficherElementPlusGrand(Score);
        method.printLine(40);

    }
    public static void afficherElementPlusPetit(List<Integer> liste) {
        int min = liste.get(0);
        int position = 0;
        for (int i = 1; i < liste.size(); i++) {
            if (liste.get(i) < min) {
                min = liste.get(i);
                position = i;
            }
        }
        System.out.println("Le joueur " + (position+1) + " à gagné!! avec un score de : " + min );
    }
    public static void afficherElementPlusGrand(List<Integer> liste) {
        int max = liste.get(0);
        int position = 0;
        for (int i = 1; i < liste.size(); i++) {
            if (liste.get(i) > max) {
                max = liste.get(i);
                position = i;
            }
        }
        System.out.println("Le joueur " + (position+1) + " à perdu avec un score de : " + max);
    }
    private static void show(int i){
        System.out.println("\nMain Joueur " + (i + 1) + " : \n");
        for (int j =0;j<joueurs.get(i).size();j++) {
            System.out.println((j+1) + ". num Carte : " + joueurs.get(i).get(j).getNum_card() + "          nbr taureau : " + joueurs.get(i).get(j).getNbr_Taureau());
        }
    }
    public static void Initplateau() {
        rangees = new card[6][4];
        AI.rangeesV = new card[6][4];

        for (int i = 0; i <= rangees.length-1; i++) {
            for (int j = 0; j <= rangees[i].length-1; j++) {
                rangees[i][j] = Card0;
            }
        }
    }
    public static void plateau(){
        String plateau="";
        method.enterContinue();
        method.clearConsole();
        method.printLine(50);
        System.out.println("Pile de carte :");

        int code=0;
        for (int i =0;i<=rangees.length-1;i++){
            String top = " ";
            String middle = "";
            String bottom = "";
            code=0;
            for (int j = 0; j<=rangees[i].length-1; j++){

                if (rangees[i][j].getNum_card()==0){
                    code+=1;
                    top += "   |    col " + (j+1) + "    |";
                    middle += "       | num " + rangees[i][j].getNum_card() + " |";
                    bottom += "       | tau " + rangees[i][j].getNbr_Taureau() + " |  ";
                }
                else {
                    top += "   |    col " + (j+1) + "    |";
                    middle += "       | num " + rangees[i][j].getNum_card() + " |";
                    bottom += "       | tau " + rangees[i][j].getNbr_Taureau() + " |  ";
                }

            }
            if (code==4){
                break;
            }
            else {
                String line = top + "\n" + middle + "\n" + bottom;
                plateau += "\n\n\n" + line;
            }
        }
        System.out.println(plateau);
        method.printLine(50);
    }
    public static void init(){
        Initplateau();
        for (int i =0;i<4;i++){
            rangees[0][i]=cartes.get(0);
            Allcarte.remove(cartes.get(0));
            cartes.remove(cartes.get(0));
        }
        plateau();
    }
    public static int lastcol(int i){
        int lastcol=5;
        while( rangees[lastcol][i].getNum_card()==0){
            lastcol-=1;
        }
        return lastcol;
    }
    public static boolean verif(int i,int choix){
        for (int k = 0; k < 4; k++) {
            int indexLastcol=lastcol(k);
            if (joueurs.get(i).get(choix - 1).getNum_card() > rangees[indexLastcol][k].getNum_card()) {
                return true;
            }
        }
        return false;
    }
    static void turn(int i,int choix){

        int indexRangee = 0;
        int lastcol;
        int indexLastcol=lastcol(0);

        for (int k = 1; k < 4; k++) {
            lastcol = lastcol(k);
            if ((joueurs.get(i).get(choix - 1).getNum_card() > rangees[indexLastcol][indexRangee].getNum_card())) {
                if ((rangees[lastcol][k].getNum_card() > rangees[indexLastcol][indexRangee].getNum_card()) && (rangees[lastcol][k].getNum_card() < joueurs.get(i).get(choix - 1).getNum_card())) {
                    indexRangee = k;
                    indexLastcol = lastcol;
                }
            }

            else{
                indexRangee = k;
                indexLastcol=lastcol;
            }
        }
        if (indexLastcol==4){
            rammasser(i,indexRangee,joueurs.get(i).get(choix - 1));
        }
        else {
            rangees[indexLastcol+1][indexRangee] = joueurs.get(i).get(choix-1);
        }
        Allcarte.remove(joueurs.get(i).get(choix-1));
        joueurs.get(i).remove(joueurs.get(i).get(choix-1));
        plateau();
        System.out.println();
    }
    public static void rammasser(int i,int j,card cardPlay){
        int indexLastcol=lastcol(j);
        for (int k=0;k<=indexLastcol;k++) {
            joueursPli.get(i).add(rangees[k][j]);
            rangees[k][j]=Card0;
        }
        rangees[0][j]=cardPlay;

    }
    public static void game(){
        for (int i = 0; i < nbr_joueur-1; i++) {
            int choix;
            int choix2;
            plateau();
            show(i);
            System.out.print("Joueur " + (i+1) + ", choisissez une carte : ");
            choix = method.scInt("->",joueurs.get(i).size());
            if (verif(i, choix) == true){
                turn(i,choix);
            }
            else{
                System.out.print("Joueur " + (i+1) + ", choisissez une colonne : ");
                choix2 = method.scInt("->",4);
                rammasser(i,choix2-1,joueurs.get(i).get(choix - 1));
                joueurs.get(i).remove(joueurs.get(i).get(choix-1));

            }
        }
        int random = (int) (Math.random()*4);
        if (verif(i, random) == true){
            turn(i,choix);
        }
        else{
            System.out.print("Joueur " + (i+1) + ", choisissez une colonne : ");
            choix2 = method.scInt("->",4);
            rammasser(i,choix2-1,joueurs.get(i).get(choix - 1));
            joueurs.get(i).remove(joueurs.get(i).get(choix-1));

        }

    }

}
