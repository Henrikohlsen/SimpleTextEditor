/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor;

import editor.display.CharacterDisplay;
import sun.awt.image.ImageWatched;

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
        if (c == 8) {
            removeChar(c);
        } else {
            iChar(c);
        }
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
                    cursorRow++;
                    displayC(c);
                }
            case 3:
                CHLC.add(cursorCol, c);
                displayC(c);
                cursorCol++;
        }
    }




        private void removeChar (char c){
        int r = 1;
        switch (r) {
                case 1:
                    if (cursorCol == 0 && cursorRow == 0) {
                        c = 0;
                        System.out.println("Nothing to remove");
                        displayC(c);
                        break;
                    }
                case 2:
                    if (cursorCol == 0) {
                        cursorRow--;
                        cursorCol = CharacterDisplay.WIDTH -1;
                        c = 0;
                        displayC(c);
                        break;
                    }

                case 3:
                        CHLC.removeLast();
                        c = ' ';
                        cursorCol--;
                        displayC(c);
        }

    }

        private void displayC (char c) {
            display.displayChar(c, cursorRow, cursorCol);
            display.displayCursor(c, cursorRow, cursorCol);
        }
    }


