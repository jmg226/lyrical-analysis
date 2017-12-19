/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyphens.to.syllables;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author gormleyjo
 */
public class HyphensToSyllables {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        //file to read from
        String file = "hyphened.txt";

        Scanner sc = new Scanner(new File(file));
        sc.useDelimiter("\\n");

        //file to write to
        FileWriter fw = new FileWriter("syllables.txt");
        
        String word;
        int syllables = 0;

        while (sc.hasNext()) 
        {
            //grab word from hyphened list
            word = sc.next();

            //if the char is a - or space, increment syllable count
            for(int i=0 ; i < word.length() ; i++)
            {
                if(word.charAt(i) == '-' || word.charAt(i) == ' ')
                {
                    syllables++;
                }
            }
            
            syllables++;
            
            //take the hyphens out of the words
            word = word.replace("-", "");

            //output each word and its syllable count to a file
            fw.write(word + "," + syllables + "\n");
            
            //reset syllable count for next word
            syllables = 0;
        }
        
        //close files
        sc.close();
        fw.close();
    }
}
