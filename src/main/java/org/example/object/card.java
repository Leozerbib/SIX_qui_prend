package org.example.object;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter@Setter
public class card {
    private int num_card;
    private int nbr_Taureau;
    public static List<card> cartes = new ArrayList<>();
    card(int n,int num){
        num_card=n;
        nbr_Taureau=num;
    }
    public static int taureau(int numCard,int i){
        if (numCard % 10==5){
            cartes.get(i-1).nbr_Taureau+=2;
        }
        if (numCard % 10==0){
            cartes.get(i-1).nbr_Taureau+=3;
        }
        if (numCard % 11==0){
            cartes.get(i-1).nbr_Taureau+=5;
        } if (numCard % 10!=5&&numCard % 10!=0&&numCard % 11!=0){
            cartes.get(i-1).nbr_Taureau+=1;
        }
        return cartes.get(i-1).nbr_Taureau;
    }

    public static List cart(){
        // Initialiser les cartes avec les valeurs de 1 à 104
        for (int i = 1; i <= 104; i++) {
            card card = new card(0, 0);
            cartes.add(card);
            cartes.get(i-1).num_card=i;
            taureau(cartes.get(i-1).num_card,i);
        }

        return cartes;
    }

}
