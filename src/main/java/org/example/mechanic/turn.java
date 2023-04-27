package org.example.mechanic;

import org.example.AI.AI;
import org.example.method;
import org.example.object.card;

import static org.example.joueurs.joueurs.joueurs;
import static org.example.joueurs.joueurs.joueursPli;
import static org.example.mechanic.Method.*;
import static org.example.object.card.Allcarte;

public class turn {
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


             {
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
    public static void rammasser(int i, int j, card cardPlay){
        int indexLastcol=lastcol(j);
        for (int k=0;k<=indexLastcol;k++) {
            joueursPli.get(i).add(rangees[k][j]);
            rangees[k][j]=Card0;
        }
        rangees[0][j]=cardPlay;

    }
    public static void game(){
        for (int i = 0; i < joueurs.size(); i++) {
            plateau();
            if(joueurs.get(i)!= AI.ordimain){
                int choix;
                int choix2;
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
            else {
                AI.arbre(50);
                int random =AI.Savemouv.get(0);
                int randomcol=AI.Savemouv.get(1);
                if (verif(i, random) == true) {
                    turn(i, random);
                } else {
                    rammasser(i, randomcol, joueurs.get(i).get(random));
                    joueurs.get(i).remove(joueurs.get(i).get(random));
                }
                System.out.println("bonjour");
            }
        }

    }
}
