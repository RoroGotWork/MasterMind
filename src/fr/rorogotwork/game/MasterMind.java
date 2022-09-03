package fr.rorogotwork.game;

import java.util.Arrays;
import java.util.Scanner;

public class MasterMind {
    // La combinaison du joueur 1
    private String combinationToFind;
    // La combinaison tappé par le joueur 2
    private String combinationTyped;
    // Le nombre d'essais du joueur
    private short trials;
    // La combinaison existante
    String[] combinationPossible = new String[]{"R", "N", "B", "J", "V", "G", "O"};
    // Permettre au joueur de mettre une formuyle de couleurs
    private Scanner scanner;

    // Initialisation de la partie
    public MasterMind() {
        combinationToFind = null;
        combinationTyped = null;
        trials = 0;
        scanner = new Scanner(System.in);
    }

    // Debut de la partie
    public void startGame(){
        System.out.println("Veuillez choisir une combinaison. (R, N, B, J, V, G, O)");
        System.out.println("Joueur 1");

        // On demande au joueur de chosir la première combinaison
        combinationToFind = getValidCombination();

        System.out.println("Veuillez choisir une combinaison. (R, N, B, J, V, G, O)");
        System.out.println("Joueur 2 :              bien placé : mal placé ");

        // On demande au deuxième joueur de choisir la combinaison
        combinationTyped = getValidCombination();
        trials = 1;

        while (!combinationTyped.equals(combinationToFind)){

            sendTrialsMessage();
            combinationTyped = getValidCombination();

            trials ++;
        }

        sendTrialsMessage();

        System.out.println( (trials <= 5 ? "Bravo" : trials <= 10 ? "Correct" : "Décevant") + " ! Vous avez réussi en " + trials + " essais");

    }


    // Calcul + envoi du nombre d'essais
    private void sendTrialsMessage(){
        char[] letters1 = combinationTyped.toCharArray();
        char[] letters2 = combinationToFind.toCharArray();

        int goodPlacedLetters = 0;

        StringBuilder letters = new StringBuilder();

        for(int i = 0; i < letters1.length; i++){

          if(letters1[i] == letters2[i]){
                goodPlacedLetters ++;
            }

          letters.append(letters1[i] + " ");
        }

        System.out.println("Essai n° " + trials + " : " + letters + "      " + goodPlacedLetters + " : " + (5 - goodPlacedLetters));

    }



    // Demande d"une combinaison valid
    private String getValidCombination(){
        String combination = scanner.nextLine();

        while (!validCombination(combination)){
            System.out.println("Combinaison invalide : Veuillez choisir un combinaison valide (R, N, B, J, V, G, O)");
            combination = scanner.nextLine();
        }
        return combination;
    }

    // Vérifier si la combinaison est valide
    private boolean validCombination(String combination){
        char[] letters = combination.toCharArray();
        if(letters.length != 5) return false;

        for(Character letter : letters){
            if(!Arrays.asList(this.combinationPossible).contains(letter.toString())){
                return false;
            }
        }

        return true;
    }

}
