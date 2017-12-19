/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.lang.Character;

/**
 *
 * @author gormleyjo
 */
public class Song {

    private String title, artist, lyrics;
    private int rank, year;
    private double numSentences;
    private double numWords;
    private double numSyllables;
    private double numCharacters;
    private double gradeLevel;
    private double wordEntropy;
    private double stanzaEntropy;
    private double clIndex;

    Song(int r, String t, String a, int y, String l) throws IOException {
        rank = r;
        title = t;
        artist = a;
        year = y;
        lyrics = l;

        numSentences = countSentences(lyrics);
        numWords = countWords(lyrics);
        numSyllables = countSyllables(lyrics);
        numCharacters = countCharacters();

        gradeLevel = calculateGradeLevel();
        wordEntropy = calculateWordEntropy();
        stanzaEntropy = calculateStanzaEntropy();
        clIndex = calculateCLIndex();

    }

    //print all the data fields of a song
    public void print() {
        System.out.println(rank + ", " + title + ", " + artist + ", " + year);
        System.out.println(lyrics);
    }

    public int getRank() {
        return rank;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getYear() {
        return year;
    }

    public double getGradeLevel() {
        return gradeLevel;
    }
    
    public double getCLIndex()
    {
        return clIndex;
    }
    
    public double getWordEntropy()
    {
        return wordEntropy;
    }
    
    public double getStanzaEntropy()
    {
        return stanzaEntropy;
    }

    private double countSentences(String lyr) throws IOException {
        //split when there is an endline
        int sent = lyr.split("[\r\n|\r|\n]+").length;

        BufferedReader br = new BufferedReader(new StringReader(lyr));
        String line;
        int empty = 0;

        //count the number of empty lines in the lyrics (lines between verses)
        while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty()) {
                empty++;
                //line="";
            }
        }

        //the number of setnences is the total lines - empty lines
        return sent - empty;
    }

    private double countWords(String lyr) {
        //split string into words
        int w = lyr.split("[\\s\\.\\,\\/\\?\\:\\;\\_\\-\\!\\)\\(\\*\"]+").length;

        //System.out.println("Words: " + w);
        return w;
    }

    private double countSyllables(String lyr) throws IOException {
        int syl = 0;
        int knowns = 0;
        int unknowns = 0;

        //file to write to
        FileWriter fw = new FileWriter("uknowns.txt");

        String[] words = lyr.split("[\\s\\.\\,\\/\\?\\:\\;\\>\\<\\_\\-\\!\\)\\(\\*\"]+");

        for (int i = 0; i < words.length; i++) {
            //if the word is in the map, add the corresponding int to the count
            if (Capstone.syllableMap.get(words[i]) != null) {
                syl += (Integer) Capstone.syllableMap.get(words[i]);
                knowns++;
            } else if (Capstone.syllableMap.get(words[i].toLowerCase().trim()) != null) {

                syl += (Integer) Capstone.syllableMap.get(words[i].toLowerCase().trim());
                knowns++;
            } else {
                //if not in the map, use regex to count syllables
                Pattern p = Pattern.compile("[aeiouy]+[^$e(,.:;!?)]");
                Matcher m = p.matcher(words[i]);

                //fw.write(words[i] + "\n");
                if (words[i].isEmpty() || words[i].equals("[SPOKEN") || words[i].equals("]")
                        || words[i].equals("[Instrumental") || words[i].equals("Interlude]")
                        || words[i].equals("#") || words[i].equals("\'") || words[i].equals("[Instrumental]")
                        || words[i].equals("[laughs]") || words[i].equals("[")
                        || words[i].equals("[Spoken]") || words[i].equals("[Spoken") || words[i].equals("backups]")
                        || words[i].equals("[intro") || words[i].equals("[guitar") || words[i].equals("interlude]")
                        || words[i].equals("guitar]") || words[i].equals("[Fade]") || words[i].equals("=")
                        || words[i].equals("Zayn") || words[i].equals("Niall") || words[i].equals("[Harry]")
                        || words[i].equals("[Liam]") || words[i].equals("[All]") || words[i].equals("[spoken")
                        || words[i].equals("[sung") || words[i].equals("[spoken]") || words[i].equals("{fade}")
                        || words[i].equals("Fade]") || words[i].equals("&") || words[i].equals("www")
                        || words[i].equals("mp3lyrics") || words[i].equals("[Musical") || words[i].equals("[Incomprehensible]")
                        || words[i].equals("[long") || words[i].equals("instrumental]") || words[i].equals("fade]")
                        || words[i].equals("[fade") || words[i].equals("out]") || words[i].equals("[music]")
                        || words[i].equals("[Chorus") || words[i].equals("Chorous") || words[i].equals("[chorus")
                        || words[i].equals("LyricsCafe") || words[i].equals("x2") || words[i].equals("x2]")
                        || words[i].equals("2]") || words[i].equals("[1") || words[i].equals("Just]")
                        || words[i].equals("[Verse") || words[i].equals("[x11]") || words[i].equals("[x4]")
                        || words[i].equals("x3]") || words[i].equals("[Reapeat") || words[i].equals("[Repeat]")
                        || words[i].equals("6x") || words[i].equals("10x") || words[i].equals("x4")
                        || words[i].equals("x3") || words[i].equals("x6") || words[i].equals("[x3]")
                        || words[i].equals("[CHORUS]") || words[i].equals("[CHORUS") || words[i].equals("[Repeat")
                        || words[i].equals("fade}") || words[i].equals("x5") || words[i].equals("now]")
                        || words[i].equals("[[CHORUS]]") || words[i].equals("LIBS]]") || words[i].equals("[[AD")
                        || words[i].equals("17x") || words[i].equals("33x") || words[i].equals("[Vinnie]")
                        || words[i].equals("[Treach") || words[i].equals("[Ladybug]") || words[i].equals("Doodlebug")
                        || words[i].equals("Butterfly") || words[i].equals("[R") || words[i].equals("Kelly]")
                        || words[i].equals("[Cassandra]") || words[i].equals("[Changing") || words[i].equals("Faces]")
                        || words[i].equals("Kelly}") || words[i].equals("[Snoop") || words[i].equals("Chorus]")
                        || words[i].equals("[Pre") || words[i].equals("[Ke$ha]") || words[i].equals("[Pitbull")
                        || words[i].equals("[Bridge]") || words[i].equals("[Verse]") || words[i].equals("[Daft")
                        || words[i].equals("[Post") || words[i].equals("[Pre") || words[i].equals("Vocals]")
                        || words[i].equals("[Pharrell") || words[i].equals("[Explicit") || words[i].equals("[Clean")
                        || words[i].equals("[Outro]") || words[i].equals("Sean]") || words[i].equals("[Big")
                        || words[i].equals("1]") || words[i].equals("[Sia") || words[i].equals("Minaj]")
                        || words[i].equals("[Nicki") || words[i].equals("[Rihanna]") || words[i].equals("[Narration]")
                        || words[i].equals("[Goyte]") || words[i].equals("[Kimbra]") || words[i].equals("Pain]")
                        || words[i].equals("[T") || words[i].equals("Sammie]") || words[i].equals("[Sammie]")
                        || words[i].equals("Boy]") || words[i].equals("[Bridge") || words[i].equals("[x2]")
                        || words[i].equals("[6x]") || words[i].equals("x16]") || words[i].equals("[JD")
                        || words[i].equals("[x16]") || words[i].equals("[Hook]") || words[i].equals("x4]")
                        || words[i].equals("[repeat") || words[i].equals("[fading") || words[i].equals("2X]")
                        || words[i].equals("[echo]") || words[i].equals("x4]") || words[i].equals("[scratches]")
                        || words[i].equals("Dogg]") || words[i].equals("[Snoop") || words[i].equals("[Ludacris]")
                        || words[i].equals("[Chingy]") || words[i].equals("Cannon]") || words[i].equals("[chorus]")
                        || words[i].equals("[Beyonce]") || words[i].equals("[Pitbull]") || words[i].equals("[Gotye]")
                        || words[i].equals("Two]") || words[i].equals("One]") || words[i].equals("4x]")
                        || words[i].equals("[Intro]") || words[i].equals("[J") || words[i].equals("[P")
                        || words[i].equals("{Get") || words[i].equals("[Jay") || words[i].equals("Z]")
                        || words[i].equals("Boi]") || words[i].equals("Mike]") || words[i].equals("[Killer")
                        || words[i].equals("3000]") || words[i].equals("[Andre") || words[i].equals("singing]")
                        || words[i].equals("{") || words[i].equals("}") || words[i].equals("[Ja")
                        || words[i].equals("[Charlie") || words[i].equals("[Ashanti") || words[i].equals("[Vita")
                        || words[i].equals("x6]") || words[i].equals("Shamari]") || words[i].equals("[Zhane]")
                        || words[i].equals("2x") || words[i].equals("[Treach]") || words[i].equals("1x]")
                        || words[i].equals("[Natina]") || words[i].equals("[Blaque]") || words[i].equals("Good]")
                        || words[i].equals("[Featuring") || words[i].equals("Elliott]") || words[i].equals("[Missy")
                        || words[i].equals("[Nicole") || words[i].equals("[Mocha]") || words[i].equals("Mocha]")
                        || words[i].equals("sings]") || words[i].equals("[Scarface]") || words[i].equals("[2Pac")
                        || words[i].equals("[x7]") || words[i].equals("repeats]") || words[i].equals("x5]")
                        || words[i].equals("3]") || words[i].equals("[repeats]") || words[i].equals("[[CHORUS")
                        || words[i].equals("x7") || words[i].equals("3X") || words[i].equals("17X")
                        || words[i].equals("33X") || words[i].equals("DelFi4110@aol") || words[i].equals("[Doodlebug]")
                        || words[i].equals("[Butterfly]") || words[i].equals("[2") || words[i].equals("[Repeats]")
                        || words[i].equals("[2Pac]") || words[i].equals("5X") || words[i].equals("[Shamari]")
                        || words[i].equals("+") || words[i].equals("¦") || words[i].equals("x15")
                        || words[i].equals("[Alvin") || words[i].equals("harmonica]") || words[i].equals("[Dave")
                        || words[i].equals("speaking]") || words[i].equals("[Simon") || words[i].equals("[Theodore")
                        || words[i].equals("shouting]") || words[i].equals("[Chipmunks]") || words[i].equals("[Alvin]")
                        || words[i].equals("in]") || words[i].equals("play]") || words[i].equals("irritated]")
                        || words[i].equals("[unverified]") || words[i].equals("[Guitar]") || words[i].equals("[Bass]")
                        || words[i].equals("[Organ]") || words[i].equals("[Trumpets]") || words[i].equals("sirens}")
                        || words[i].equals("{police") || words[i].equals("{shoot") || words[i].equals("effects}")
                        || words[i].equals("9x") || words[i].equals("ronhontz@worldnet") || words[i].equals("2X")
                        || words[i].equals("linex3") || words[i].equals("{x2}") || words[i].equals("backstageproductions")
                        || words[i].equals("info@backstageprofuctions") || words[i].equals("[RoRo") || words[i].equals("[Red")
                        || words[i].equals("[Chris") || words[i].equals("[Mike") || words[i].equals("[2]")
                        || words[i].equals("[Break]") || words[i].equals("X6") || words[i].equals("[mase]")
                        || words[i].equals("[puff]") || words[i].equals("[Allure]") || words[i].equals("X2")
                        || words[i].equals("X4") || words[i].equals("X8") || words[i].equals("8x")
                        || words[i].equals("Fergie]") || words[i].equals("[Refrain") || words[i].equals("[Outro")
                        || words[i].equals("[Interlude") || words[i].equals("[Intro") || words[i].equals("2x]")
                        || words[i].equals("4x") || words[i].equals("×4") || words[i].equals("7x")
                        || words[i].equals("[Fat") || words[i].equals("Joe]") || words[i].equals("Rule]")
                        || words[i].equals("[Hook") || words[i].equals("end]") || words[i].equals("[Eve]")
                        || words[i].equals("[Alicia") || words[i].equals("Keys]") || words[i].equals("[Missy]")
                        || words[i].equals("[puffy]") || words[i].equals("[dream]") || words[i].equals("[black")
                        || words[i].equals("money]") || words[i].equals("[Puffy]") || words[i].equals("[Pam]")
                        || words[i].equals("[Keisha]") || words[i].equals("[Kima]") || words[i].equals("[Timbaland]")
                        || words[i].equals("{Music") || words[i].equals("[featuring") || words[i].equals("sole]")
                        || words[i].equals("[jt") || words[i].equals("money]") || words[i].equals("[sole`]")
                        || words[i].equals("16x") || words[i].equals("[112]") || words[i].equals("[Mase]")
                        || words[i].equals("[Mia") || words[i].equals("X]") || words[i].equals("[1]")
                        || words[i].equals("[Puff") || words[i].equals("Daddy]") || words[i].equals("[Foxy]")
                        || words[i].equals("Chours") || words[i].equals("x9") || words[i].equals("[Master")
                        || words[i].equals("P]") || words[i].equals("@") || words[i].equals("3x")
                        || words[i].equals("1X") || words[i].equals("151bacardi") || words[i].equals("[Boo")
                        || words[i].equals("[3") || words[i].equals("[Rap]") || words[i].equals("{2X}")
                        || words[i].equals("info@backstageproductions") || words[i].equals("{x5}")
                        || words[i].equals("{Instrumental}") || words[i].equals("©") || words[i].equals("break")
                        || words[i].equals("©1970") || words[i].equals("Drummer") || words[i].equals("—") 
                        || words[i].equals("~")	|| words[i].equals("Theodore]") || words[i].equals("speaks]")
                        || words[i].equals("Awcantor@aol") || words[i].equals("5x") || words[i].equals("Chrous")
                        || words[i].equals("Plays}") || words[i].equals("[incomprehensible]") || words[i].equals("4X")
                        || words[i].equals("9x's") || words[i].equals("8X") || words[i].equals("1993")
                        || words[i].equals("1985") || words[i].equals("1994") || words[i].equals("1991")
                        || words[i].equals("850") || words[i].equals("x8") || words[i].equals("1990")
                        || words[i].equals("7784") || words[i].equals("297") || words[i].equals("416")
                        || words[i].equals("4913") || words[i].equals("291") || words[i].equals("416")
                        || words[i].equals("1E7") || words[i].equals("M1V") || words[i].equals("background]")
                        || words[i].equals("1965") || words[i].equals("1975") || words[i].equals("1959")
                        || words[i].equals("1956") || words[i].equals("1971") || words[i].equals("[break]")
                        || words[i].equals("#2") || words[i].equals("[Drummer]") || words[i].equals("1911")
                        || words[i].equals("Music##") || words[i].equals("69971") || words[i].equals("[Fade")
                        || words[i].equals("alvino99") || words[i].equals("1964+") || words[i].equals("#4001")
                        || words[i].equals("A21551") || words[i].equals("Trk") || words[i].equals("#6")
                        || words[i].equals("XKs") || words[i].equals("08") || words[i].equals("&B")
                        || words[i].equals("Trackmaster")
                        
                        || words[i].equals("[Chorus]"))    
                
                {
                    numWords--;
                    numSentences--;
                    //System.out.println("*" + words[i] + "*");
                    //System.out.println(lyrics);
                } else {
                    System.out.print(words[i]);
                    System.out.println("\t\t" + title + " - " + artist);
                    //System.out.println("**\n" + lyrics);

                    int s = 0;
                    while (m.find()) {
                        s++;
                    }
                    //System.out.println(syllables);
                    unknowns++;
                    //System.out.println(words[i] + ", " + s);
                    syl += s;
                }
            }
        }

        //System.out.println("Syllables: " + syl);
        //System.out.println("Known words: " + knowns);
        //System.out.println("Unknown words: " + unknowns);
        fw.close();
        return syl;
    }

    private double countCharacters()
    {
        int c = 0;
        char temp;
        
        for(int i = 0 ; i < lyrics.length() ; i++)
        {
            temp = lyrics.charAt(i);
            
            if(Character.isLetter(temp) || Character.isDigit(temp))
            {
                c++;
            }
            
        }
        
        return c;
    }
    
    private double calculateGradeLevel() {
        double r = (0.39 * (numWords / numSentences)) + (11.8 * (numSyllables / numWords)) - 15.59;
        //System.out.println("Grade level: " + r);
        return r;
    }

    private double calculateWordEntropy() {
        int n = 0;
        HashMap<String, Integer> wordMap = new HashMap();

        //System.out.println(lyrics + "\n\n");
        for (String wrd : lyrics.split("[\\s\\.\\,\\/\\?\\:\\;\\>\\<\\_\\-\\!\\)\\(\\*\"]+")) {
            //System.out.println(wrd);

            if (wordMap.containsKey(wrd)) {
                wordMap.put(wrd, wordMap.get(wrd) + 1);
            } else {
                wordMap.put(wrd, 1);
            }
            ++n;
        }

        double e = 0.0;
        for (HashMap.Entry<String, Integer> entry : wordMap.entrySet()) {
            String cur = entry.getKey();
            double p = (double) entry.getValue() / n;
            e += p * log2(p);
        }

        //System.out.println(-e);

        return -e;

    }

    private double calculateStanzaEntropy() {
        int n = 0;
        HashMap<String, Integer> wordMap = new HashMap();

        //System.out.println(lyrics + "\n\n");
        for (String stnz : lyrics.split("\n\n")) {
            stnz = stnz.trim();
            //System.out.println("*" + stnz + "*\n");

            if (wordMap.containsKey(stnz)) {
                wordMap.put(stnz, wordMap.get(stnz) + 1);
            } else {
                wordMap.put(stnz, 1);
            }
            ++n;
        }

        double e = 0.0;
        for (HashMap.Entry<String, Integer> entry : wordMap.entrySet()) {
            String cur = entry.getKey();
            double p = (double) entry.getValue() / n;
            e += p * log2(p);
        }

        //System.out.println(-e);

        return -e;

    }
    
    private double calculateCLIndex()
    {
        double L = numCharacters / numWords * 100;
        double S = numSentences / numWords * 100;
        
        double ind = (0.0588*L) - (0.296*S) - 15.8;
        
        //System.out.println(ind);
        
        return ind;
    }

    private static double log2(double a) {
        return Math.log(a) / Math.log(2);
    }
}
