package org.example.AI;

import org.example.joueurs.joueurs;
import org.example.object.card;

import java.util.*;

import static org.example.mechanic.Method.*;
import static org.example.object.card.Allcarte;

public class AI {
    static Random random = new Random();
    private static int Save;
    private static int Savecol;
    public static card[][] rangeesV ;
    public static List<card> ordimain = new ArrayList<>();
    public static List<card> ordibin = new ArrayList<>();
    public static List<card> ordimainV = new ArrayList<>();
    public static List<card> ordibinV = new ArrayList<>();
    public static List<card> SaveCard = new ArrayList<>();
    public static List<Integer> Savemouv = new ArrayList<>();
    public static List<card> binV = new ArrayList<>();
    public static List<List<card>> joueursPliV = new ArrayList<>();
    private static List<Integer> ScoreV = new ArrayList<>();
    private static int l;
    public static void makeListIdentical(card[][] list1, card[][] list2) {
        for (int i = 0; i < list1.length; i++) {
            for (int j = 0; j < list1[0].length; j++) {
                list1[i][j] = list2[i][j];
            }
        }
    }
    public static void arbre(int profondeur){

        Savemouv.add(0);
        Savemouv.add(0);
        Savemouv.add(50);


            for(int i =0;i<profondeur;i++){
                makeListIdentical(rangeesV,rangees);
                ordimainV.addAll(ordimain);
                ordibinV.addAll(ordibin);
                chooseMin(GameLogicAI());
                ordibinV.clear();
                binV.clear();
                Allcarte.addAll(SaveCard);
                SaveCard.clear();
                System.out.println(ordimain.size());
            }

    }
    public static void carteRest(){
        List<card> main = ordimain;
        Allcarte.removeAll(main);

    }
    public static int GameLogicAI(){
        l=0;
        while(ordimainV.size()>0){

            gameV(l);
            l+=1;
        }
        ScoreV.clear();
        int pointV = 0;
        for (int j = 0; j<joueursPliV.get(joueurs.joueurs.size()-1).size();j++){
            pointV += joueursPliV.get(joueurs.joueurs.size()-1).get(j).getNbr_Taureau();
        }
        return pointV;
    }
    private static void chooseMin(int point){
        int min = Savemouv.get(2);
        if(point<min){
            Savemouv.clear();
            Savemouv.add(Save);
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
            int indexLastcol=lastcolV(k);
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
    public static void gameV(int l){

        int randomInput= (int) (Allcarte.size()*0.75);
        int randomInputCol= random.nextInt(4);
        int randomInputAI;
        if (ordimainV.size()!=1) {
             randomInputAI= random.nextInt(ordimainV.size()-1);
        }
        else {
            randomInputAI=0;
        }
        if(l==0){
            Save = randomInputAI;
            Savecol = randomInputCol;
            if (verifV(ordimainV.get(randomInputAI), randomInputAI) == true){
                turnV(joueurs.joueurs.size()-1,joueurs.joueurs.get(joueurs.joueurs.size()-1).get(randomInputAI));
                ordimainV.remove(randomInputAI);
            }
            else{
                rammasserV(joueurs.joueurs.size()-1,randomInputCol,ordimainV.get(randomInputAI));
                ordimainV.remove(randomInputAI);

            }

        }


        for (int i = 0; i < joueurs.joueurs.size()-1; i++) {
            if (verifV(Allcarte.get(randomInput), randomInput) == true) {
                turnV(i, Allcarte.get(randomInput));
                SaveCard.add(Allcarte.get(randomInput));
                Allcarte.remove(randomInput);

            } else {
                rammasserV(i, randomInputCol, Allcarte.get(randomInput));
                SaveCard.add(Allcarte.get(randomInput));
                Allcarte.remove(randomInput);


            }
        }
        if (verifV(ordimainV.get(randomInputAI), randomInputAI) == true){
            turnV(joueurs.joueurs.size()-1,joueurs.joueurs.get(joueurs.joueurs.size()-1).get(randomInputAI));
            ordimainV.remove(randomInputAI);
        }
        else{
            rammasserV(joueurs.joueurs.size()-1,randomInputCol,ordimainV.get(randomInputAI));
            ordimainV.remove(randomInputAI);

        }

    }
}
