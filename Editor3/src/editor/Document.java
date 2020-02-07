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
        if (c == 10) {
            lineFeed(c);
            display.displayCursor(c, cursorRow, cursorCol);
        } else if (c == 8) {
            moveLeft(c);
            overWrite(' ');
        } else if (c == 49) {
            moveLeft(c);
            display.displayCursor(c, cursorRow, cursorCol);
        } else
            iChar(c);
    }


    private void iChar(char c) {
        int i = 1;

        switch (i) {
            case 1:
                if (cursorCol == CharacterDisplay.WIDTH && cursorRow == CharacterDisplay.HEIGHT-1) {
                    break;
                }
            case 2:
                if (cursorCol >= CharacterDisplay.WIDTH) {
                    cursorCol = 0;
                    overWrite(c);
                    cursorRow++;
                }
            case 3:
                CHLC.add(cursorCol, c);
                overWrite(c);
                cursorCol++;
        }
    }

        private void moveLeft (char c){
        int r = 1;
        switch (r) {
                case 1:
                    if (cursorCol == 0 && cursorRow == 0) {
                        c = 0;
                        System.out.println("Nothing to remove");
                        break;
                    }
                case 2:
                    if (cursorCol == 0) {
                        cursorRow--;
                        cursorCol = CharacterDisplay.WIDTH -1;
                        c = 0;
                        break;
                    }
                case 3:
                        CHLC.removeLast();
                        c = ' ';
                        cursorCol--;
        }
    }

    private void lineFeed(char c) {
        CHLC.add(cursorCol, c);
        CHLR.add(cursorRow, c);
        if (cursorRow < CharacterDisplay.HEIGHT-1) {
            int ant = CharacterDisplay.WIDTH - cursorCol;
            for (int i = 0; i < ant; i++) {
                CHLC.add('c');
            }
            cursorCol = 0;
            cursorRow++;
        }
    }
        private void overWrite (char c) {
            display.displayChar(c, cursorRow, cursorCol);
            display.displayCursor(c, cursorRow, cursorCol);
        }

        private void moveLeft () {
            if (cursorCol == 0 && cursorRow == 0) {
            }
            else if (cursorCol == 0) {
                cursorRow--;
                cursorCol = CharacterDisplay.WIDTH;
            } else {
                cursorCol--;
                display.displayCursor(' ', cursorRow, cursorCol);
            }
        }

        private void moveRight () {
        if (cursorCol <= CHLC.getLast()) {
            CHLC.add(' ');
            cursorCol++;
            System.out.println(":)");
            display.displayCursor(' ', cursorRow, cursorCol);
        } else if (cursorCol == CharacterDisplay.WIDTH -1) {
            System.out.println(":(");
                cursorCol = 0;
            }
            else {
                cursorCol++;
            System.out.println("^^,");
                display.displayCursor(' ', cursorRow, cursorCol);
            }
        }
    }


