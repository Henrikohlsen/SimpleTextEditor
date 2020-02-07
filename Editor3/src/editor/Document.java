/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor;

import editor.display.CharacterDisplay;


import javax.swing.*;
import java.util.LinkedList;

/**
 * This class represents the document being edited. Using a 2d array to hold the
 * document content is probably not a very good choice. Fixing this class is the
 * main focus of the exercise. In addition to designing a better data model, you
 * must add methods to do at least basic editing: write and delete text, and
 * moving the cursor
 *
 * @author evenal
 */
public class Document {

    private CharacterDisplay display;
    private int cursorRow;
    private int cursorCol;
    LinkedList<Character> CHLR = new LinkedList<>();
    LinkedList<Character> CHLC = new LinkedList<>();

    public Document(CharacterDisplay display) {
        this.display = display;
        cursorCol = cursorRow = 0;
        display.displayCursor(' ', cursorRow, cursorCol);
    }

    public void insertChar(char c) {
   int ic = 1;
        switch (ic) {
            case 1:
                if (c == 8) { // Når man trykker "BackSpace" så fjerner den en bokstav
                    removeChar(c);
                    break;
                }
            case 2:
                if (c == 10) { // Når man trykker "Enter" så hopper den ett hakk ned
                    lineFeed(c);
                    display.displayCursor(c, cursorRow, cursorCol);
                }
            case 3:
                if (c == 49) { // Når man trykker på "1", så flytter man et hakk til venstre
                    moveLeft();
                    break;
                }
            case 4:
                if (c == 50) { // Når man trykker på "2", så flytter man et hakk til høyre
                    moveRight();
                    break;
                }
            case 5:
                iChar(c);

        }
    }


    private void iChar(char c) {
        int i = 1;

        switch (i) {
            case 1:
                if (cursorCol == CharacterDisplay.WIDTH && cursorRow == CharacterDisplay.HEIGHT - 1) { // Hindrer at man kan legge til en bokstav helt på slutten av dokumentet
                    break;
                }
            case 2:
                if (cursorCol >= CharacterDisplay.WIDTH) { // Når man kommer til enden, så hopper den ned et hakk og begynner fra starten
                    cursorCol = 0;
                    cursorRow++;
                    displayC(c);
                }
            case 3:
                CHLC.add(cursorCol, c); // Legger til en bokstav
                displayC(c);
                cursorCol++;
        }
    }

    private void removeChar(char c) {
        int r = 1;
        switch (r) {
            case 1:
                if (cursorCol == 0 && cursorRow == 0) { // Hindrer at man kan slette en bokstav helt i starten av dokumentet
                    c = 0;
                    System.out.println("Nothing to remove");
                    displayC(c);
                    break;
                }
            case 2:
                if (cursorCol == 0) { // Når man kommer på starten, så hopper den opp et hakk og begynner på enden
                    cursorRow--;
                    cursorCol = CharacterDisplay.WIDTH - 1;
                    c = 0;
                    displayC(c);
                    break;
                }
            case 3:
                CHLC.removeLast(); // Fjerner en bokstav
                c = ' ';
                cursorCol--;
                displayC(c);
        }

    }

    private void displayC(char c) {
        display.displayChar(c, cursorRow, cursorCol);
        display.displayCursor(c, cursorRow, cursorCol);
    }


    public void lineFeed(char c) {
        CHLC.add(cursorCol, c);
        CHLR.add(cursorRow, c);
        display.displayChar(c, cursorRow, cursorCol);
        if (cursorRow < CharacterDisplay.HEIGHT-1) {
            int ant = CharacterDisplay.WIDTH - cursorCol;
            for (int i = 0; i < ant; i++) {
                CHLC.add('c');
            }
            cursorCol = 0;
            cursorRow++;
        }
        display.displayCursor(' ', cursorRow, cursorCol);
    }

    private void moveLeft() {
        int ml = 1;
        switch (ml) {
            case 1:
                if (cursorCol == 0 && cursorRow == 0) {
                    break;
                }
            case 2:
                if (cursorCol == 0) {
                    cursorRow--;
                    cursorCol = CharacterDisplay.WIDTH - 1;
                    display.displayCursor(' ', cursorRow, cursorCol);
                }
            case 3:
                cursorCol--;
                display.displayCursor(' ', cursorRow, cursorCol);

        }
    }

    private void moveRight() {
        int mr = 1;
        switch (mr) {
            case 1:
                if (cursorCol == CharacterDisplay.WIDTH - 1 && cursorRow == CharacterDisplay.HEIGHT - 1) {
                    break;
                }
            case 2:
                if (cursorCol == CharacterDisplay.WIDTH - 1) {
                    cursorRow++;
                    cursorCol = 0;
                    display.displayCursor(' ', cursorRow, cursorCol);
                    break;
                }
            case 3:
                if (cursorCol <= CHLC.getLast()) {
                    CHLC.add(' ');
                    cursorCol++;
                    display.displayCursor(' ', cursorRow, cursorCol);
                    break;
                }
            case 4:
                cursorCol++;
                display.displayCursor(' ', cursorRow, cursorCol);
        }
    }
}

