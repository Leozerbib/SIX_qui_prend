package org.example.AI;

import org.example.method;
import org.example.object.card;

import java.util.*;

import static org.example.mechanic.Method.*;
import static org.example.object.card.Allcarte;
import static org.example.object.card.cartes;

public class AI {
    Random random = new Random();
    private static int SaveMouv;
    private static int Savecol;
    private static int profondeur;
    private static float victoire;
    private static float defaite;
    private static float nul;
    public static card[][] rangeesV ;
    public static List<card> ordi = new ArrayList<>();
    public static List<card> SaveCard = new ArrayList<>();
    public static List<Integer> Savemouv = new ArrayList<>();
    private static List<List<card>> joueursV = new ArrayList<>();
    private static List<List<card>> joueursPliV = new ArrayList<>();
    private static List<Integer> ScoreV = new ArrayList<>();


    public static int arbre(int profondeur){
        SaveCard.clear();
        Savemouv.add(0);
        Savemouv.add(0);
        Savemouv.add(50);
            for(int i =0;i<profondeur;i++){
                chooseMin(GameLogicAI());
            }
        Allcarte.addAll(SaveCard);
        return Savemouv.get(0);
    }
    public static void carteRest(){
        for (int i = 0; i<ordi.size();i++){
            Allcarte.remove(ordi.get(i));
        }
    }
    public static int GameLogicAI(){
        rangeesV=rangees;
        while(joueurs.get(nbr_joueur-1).size()>0){
            gameV();
        }
        ScoreV.clear();
        int pointV = 0;
        for (int j = 0; j<joueursPliV.get(nbr_joueur-1).size();j++){
            pointV += joueursPliV.get(nbr_joueur-1).get(j).getNbr_Taureau();
        }
        return pointV;
    }
    private static void chooseMin(int point){
        int min = Savemouv.get(2);
        if(point<min){
            Savemouv.clear();
            Savemouv.add(SaveMouv);
            Savemouv.add(Savecol);
            Savemouv.add(point);

        }
    }


    public static int lastcolV(int i){
        int lastcol=5;
        while( rangeesV[lastcol][i].getNum_card()==0){
            lastcol-=1;
        }
        return lastcol;
    }
    public static boolean verifV(card Cardplay,int choix){
        for (int k = 0; k < 4; k++) {
            int indexLastcol=lastcol(k);
            if (Cardplay.getNum_card() > rangeesV[indexLastcol][k].getNum_card()) {
                return true;
            }
        }
        return false;
    }
    static void turnV(int i,card cardPlay){
        int indexRangee = 0;
        int lastcol;
        int indexLastcol=lastcolV(0);

        for (int k = 1; k < 4; k++) {
            lastcol = lastcolV(k);
            if ((cardPlay.getNum_card() > rangeesV[indexLastcol][indexRangee].getNum_card())) {
                if ((rangeesV[lastcol][k].getNum_card() > rangeesV[indexLastcol][indexRangee].getNum_card()) && (rangeesV[lastcol][k].getNum_card() < cardPlay.getNum_card())) {
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
            rammasserV(i,indexRangee,cardPlay);
        }
        else {
            rangeesV[indexLastcol + 1][indexRangee] = cardPlay;
        }
    }
    public static void rammasserV(int i,int j,card cardPlay){
        int indexLastcol=lastcolV(j);
        for (int k=0;k<=indexLastcol;k++) {
            joueursPliV.get(i).add(rangeesV[k][j]);
            rangeesV[k][j]=Card0;
        }
        rangeesV[0][j]=cardPlay;

    }
    public static void gameV(){

        int randomInput= (int) (Allcarte.size()/0.75);
        int randomInputCol= (int) Math.random()*4;
        int randomInputAI= (int) Math.random()*ordi.size();
        int l=0;
        while (l==0){
            SaveMouv = randomInputAI;
            Savecol = randomInputCol;
            l+=1;
        }
        for (int i = 0; i < nbr_joueur-1; i++) {
            if (verifV(Allcarte.get(randomInput), randomInput) == true){
                turnV(i,Allcarte.get(randomInput));
            }
            else{
                rammasserV(i,randomInputCol-1,Allcarte.get(randomInputCol - 1));
                SaveCard.add(Allcarte.get(randomInputCol - 1));
                Allcarte.remove(Allcarte.get(randomInputCol - 1));


            }
        }
        if (verifV(joueurs.get(nbr_joueur-1).get(randomInputAI), randomInputAI) == true){
            turnV(nbr_joueur-1,joueurs.get(nbr_joueur-1).get(randomInputAI));
        }
        else{
            rammasserV(nbr_joueur-1,randomInputCol-1,joueurs.get(nbr_joueur-1).get(randomInputCol - 1));
        }
    }
}
