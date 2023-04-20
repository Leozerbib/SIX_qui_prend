package org.example.mechanic;
import org.example.method;
import org.example.object.card;

import java.util.*;

import static org.example.object.card.cartes;


public class Method {
    static Scanner sc = new Scanner(System.in);
    public static int nbr_joueur;
    static card[][] rangees ;

    public static List<card> main;
    static List<List<card>> joueurs = new ArrayList<>();
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
        nbr_joueur= sc.nextInt();
        // Distribuer les cartes aux joueurs
        for (int i = 0; i < nbr_joueur; i++) {
            main = new ArrayList<>();
            joueurs.add(main);
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
    private static void show(int i){
        System.out.println("\nMain Joueur " + (i + 1) + " : \n");
        for (int j =0;j<joueurs.get(i).size();j++) {
            System.out.println(" num Carte : " + joueurs.get(i).get(j).getNum_card() + "          nbr taureau : " + joueurs.get(i).get(j).getNbr_Taureau());
        }
    }
    public static void Initplateau() {
        rangees = new card[6][nbr_joueur];
        for (int i = 0; i <= rangees.length-1; i++) {
            for (int j = 0; j <= rangees[i].length-1; j++) {
                rangees[i][j] = new card(0, 0);
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
            if (code==nbr_joueur){
                break;
            }
            else {
                String line = top + "\n" + middle + "\n" + bottom;
                plateau += "\n\n\n" + line;
            }
        }
        System.out.println(plateau);
        method.printLine(50);
        method.enterContinue();
        method.clearConsole();
    }
    public static void init(){
        Initplateau();
        for (int i =0;i<nbr_joueur;i++){
            System.out.print("Joueur " + (i+1) + ", choisissez une carte : ");
            int choice = method.scInt("->",joueurs.get(i).size());
            rangees [0][i]=joueurs.get(i).get(choice-1);
            String plateau = "";
            for (int j = 1;j<=i+1;j++){
                plateau += " [?] ";
            }
            System.out.println(plateau);
            joueurs.get(i).remove(joueurs.get(i).get(choice-1));

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
    public static void game(){
        for (int i = 0; i < nbr_joueur; i++) {
            int choix;
            show(i);
            System.out.print("Joueur " + (i+1) + ", choisissez une carte : ");
            choix = method.scInt("->",joueurs.get(i).size()-1);
            int indexRangee = 0;
            int lastcol;
            int indexLastcol=lastcol(0);
                for (int k = 1; k < nbr_joueur; k++) {
                    lastcol = lastcol(k);
                    if ((rangees[lastcol][k].getNum_card() < rangees[indexLastcol][indexRangee].getNum_card()) && (rangees[lastcol][k].getNum_card() < joueurs.get(i).get(choix-1).getNum_card())) {
                        indexRangee = k;
                        indexLastcol=lastcol;
                    }
                }
            rangees[indexLastcol+1][indexRangee] = joueurs.get(i).get(choix-1);
            joueurs.get(i).remove(joueurs.get(i).get(choix-1));
            plateau();
            System.out.println();
        }

    }
    public static void GameLogic(){
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        // Jouer au  jeu
        System.out.println();
        init();
        game();


        // Calculer les points
        int[] points = new int[4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                int difference = Math.abs(joueurs.get(i).get(j).getNbr_Taureau() - rangees[0][j].getNbr_Taureau());
                for (int k = 1; k < 4; k++) {
                    int nouvelleDifference = Math.abs(joueurs.get(i).get(j).getNbr_Taureau() - rangees[k][j].getNbr_Taureau());
                    if (nouvelleDifference < difference) {
                        difference = nouvelleDifference;
                    }
                }
                points[i] += difference;
            }
        }

        // Afficher les résultats
        for (int i = 0; i < 4; i++) {
            System.out.println("Joueur " + (i+1) + " : " + points[i] + " points");
        }
    }
}

