/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone;

import java.io.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author gormleyjo
 */
public class Capstone {

    public static HashMap syllableMap = new HashMap();
    public static FileWriter fileWriter = null;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        // TODO code application logic 

        //create hash map of dictionary words and their syllables
        createMap();

        //file to read in song data
        //String file = "billboard-100-59-to-16.csv";
        String file = "/home/gormleyjo/CapstoneRepo/lyrical-analysis/corpus/billboard-100-59-to-16-full-lyrics.csv";

        //file to write the songs and their reading level to
        fileWriter = new FileWriter("Results.csv");
        fileWriter.append("Rank;Title;Artist;Year;Grade Level;CL Index;Word Entropy;Stanza Entropy");
        fileWriter.append("\n");

        //scanner to read file containing the songs
        Scanner sc = new Scanner(new File(file));
        sc.useDelimiter("\\n|;");

        String song, artist, lyrics;
        int rank, year;

        //linked list declarations
        // <editor-fold>
        LinkedList<Song> songs1959 = new LinkedList<Song>();
        LinkedList<Song> songs1960 = new LinkedList<Song>();
        LinkedList<Song> songs1961 = new LinkedList<Song>();
        LinkedList<Song> songs1962 = new LinkedList<Song>();
        LinkedList<Song> songs1963 = new LinkedList<Song>();
        LinkedList<Song> songs1964 = new LinkedList<Song>();
        LinkedList<Song> songs1965 = new LinkedList<Song>();
        LinkedList<Song> songs1966 = new LinkedList<Song>();
        LinkedList<Song> songs1967 = new LinkedList<Song>();
        LinkedList<Song> songs1968 = new LinkedList<Song>();
        LinkedList<Song> songs1969 = new LinkedList<Song>();

        LinkedList<Song> songs1970 = new LinkedList<Song>();
        LinkedList<Song> songs1971 = new LinkedList<Song>();
        LinkedList<Song> songs1972 = new LinkedList<Song>();
        LinkedList<Song> songs1973 = new LinkedList<Song>();
        LinkedList<Song> songs1974 = new LinkedList<Song>();
        LinkedList<Song> songs1975 = new LinkedList<Song>();
        LinkedList<Song> songs1976 = new LinkedList<Song>();
        LinkedList<Song> songs1977 = new LinkedList<Song>();
        LinkedList<Song> songs1978 = new LinkedList<Song>();
        LinkedList<Song> songs1979 = new LinkedList<Song>();

        LinkedList<Song> songs1980 = new LinkedList<Song>();
        LinkedList<Song> songs1981 = new LinkedList<Song>();
        LinkedList<Song> songs1982 = new LinkedList<Song>();
        LinkedList<Song> songs1983 = new LinkedList<Song>();
        LinkedList<Song> songs1984 = new LinkedList<Song>();
        LinkedList<Song> songs1985 = new LinkedList<Song>();
        LinkedList<Song> songs1986 = new LinkedList<Song>();
        LinkedList<Song> songs1987 = new LinkedList<Song>();
        LinkedList<Song> songs1988 = new LinkedList<Song>();
        LinkedList<Song> songs1989 = new LinkedList<Song>();

        LinkedList<Song> songs1990 = new LinkedList<Song>();
        LinkedList<Song> songs1991 = new LinkedList<Song>();
        LinkedList<Song> songs1992 = new LinkedList<Song>();
        LinkedList<Song> songs1993 = new LinkedList<Song>();
        LinkedList<Song> songs1994 = new LinkedList<Song>();
        LinkedList<Song> songs1995 = new LinkedList<Song>();
        LinkedList<Song> songs1996 = new LinkedList<Song>();
        LinkedList<Song> songs1997 = new LinkedList<Song>();
        LinkedList<Song> songs1998 = new LinkedList<Song>();
        LinkedList<Song> songs1999 = new LinkedList<Song>();

        LinkedList<Song> songs2000 = new LinkedList<Song>();
        LinkedList<Song> songs2001 = new LinkedList<Song>();
        LinkedList<Song> songs2002 = new LinkedList<Song>();
        LinkedList<Song> songs2003 = new LinkedList<Song>();
        LinkedList<Song> songs2004 = new LinkedList<Song>();
        LinkedList<Song> songs2005 = new LinkedList<Song>();
        LinkedList<Song> songs2006 = new LinkedList<Song>();
        LinkedList<Song> songs2007 = new LinkedList<Song>();
        LinkedList<Song> songs2008 = new LinkedList<Song>();
        LinkedList<Song> songs2009 = new LinkedList<Song>();

        LinkedList<Song> songs2010 = new LinkedList<Song>();
        LinkedList<Song> songs2011 = new LinkedList<Song>();
        LinkedList<Song> songs2012 = new LinkedList<Song>();
        LinkedList<Song> songs2013 = new LinkedList<Song>();
        LinkedList<Song> songs2014 = new LinkedList<Song>();
        LinkedList<Song> songs2015 = new LinkedList<Song>();
        LinkedList<Song> songs2016 = new LinkedList<Song>();
        // </editor-fold>

        while (sc.hasNext()) {

            year = sc.nextInt();
            rank = sc.nextInt();
            artist = sc.next();
            song = sc.next();
            lyrics = sc.next();

            Base64.Decoder dec = Base64.getDecoder();
            byte[] lyr = dec.decode(lyrics);
            lyrics = new String(lyr, "UTF-8");

            if (!lyrics.equals("\n") && !lyrics.contains("chorus") && !lyrics.contains("Chorus")) {

                //lyrics = lyrics.replaceAll("^\\s*\n", "\n");
                Song temp = new Song(rank, song, artist, year, lyrics);

                //add song to corresponding linked list
                // <editor-fold>
                if (year >= 1959 && year < 1970) {
                    switch (year) {

                        case 1959:
                            songs1959.add(temp);
                            break;

                        case 1960:
                            songs1960.add(temp);
                            break;

                        case 1961:
                            songs1961.add(temp);
                            break;

                        case 1962:
                            songs1962.add(temp);
                            break;

                        case 1963:
                            songs1963.add(temp);
                            break;

                        case 1964:
                            songs1964.add(temp);
                            break;

                        case 1965:
                            songs1965.add(temp);
                            break;

                        case 1966:
                            songs1966.add(temp);
                            break;

                        case 1967:
                            songs1967.add(temp);
                            break;

                        case 1968:
                            songs1968.add(temp);
                            break;

                        case 1969:
                            songs1969.add(temp);
                            break;
                    }
                } else if (year >= 1970 && year < 1980) {
                    switch (year) {

                        case 1970:
                            songs1970.add(temp);
                            break;

                        case 1971:
                            songs1971.add(temp);
                            break;

                        case 1972:
                            songs1972.add(temp);
                            break;

                        case 1973:
                            songs1973.add(temp);
                            break;

                        case 1974:
                            songs1974.add(temp);
                            break;

                        case 1975:
                            songs1975.add(temp);
                            break;

                        case 1976:
                            songs1976.add(temp);
                            break;

                        case 1977:
                            songs1977.add(temp);
                            break;

                        case 1978:
                            songs1978.add(temp);
                            break;

                        case 1979:
                            songs1979.add(temp);
                            break;
                    }
                } else if (year >= 1980 && year < 1990) {
                    switch (year) {

                        case 1980:
                            songs1980.add(temp);
                            break;

                        case 1981:
                            songs1981.add(temp);
                            break;

                        case 1982:
                            songs1982.add(temp);
                            break;

                        case 1983:
                            songs1983.add(temp);
                            break;

                        case 1984:
                            songs1984.add(temp);
                            break;

                        case 1985:
                            songs1985.add(temp);
                            break;

                        case 1986:
                            songs1986.add(temp);
                            break;

                        case 1987:
                            songs1987.add(temp);
                            break;

                        case 1988:
                            songs1988.add(temp);
                            break;

                        case 1989:
                            songs1989.add(temp);
                            break;
                    }
                } else if (year >= 1990 && year < 2000) {
                    switch (year) {

                        case 1990:
                            songs1990.add(temp);
                            break;

                        case 1991:
                            songs1991.add(temp);
                            break;

                        case 1992:
                            songs1992.add(temp);
                            break;

                        case 1993:
                            songs1993.add(temp);
                            break;

                        case 1994:
                            songs1994.add(temp);
                            break;

                        case 1995:
                            songs1995.add(temp);
                            break;

                        case 1996:
                            songs1996.add(temp);
                            break;

                        case 1997:
                            songs1997.add(temp);
                            break;

                        case 1998:
                            songs1998.add(temp);
                            break;

                        case 1999:
                            songs1999.add(temp);
                            break;
                    }
                } else if (year >= 2000 && year < 2010) {
                    switch (year) {

                        case 2000:
                            songs2000.add(temp);
                            break;

                        case 2001:
                            songs2001.add(temp);
                            break;

                        case 2002:
                            songs2002.add(temp);
                            break;

                        case 2003:
                            songs2003.add(temp);
                            break;

                        case 2004:
                            songs2004.add(temp);
                            break;

                        case 2005:
                            songs2005.add(temp);
                            break;

                        case 2006:
                            songs2006.add(temp);
                            break;

                        case 2007:
                            songs2007.add(temp);
                            break;

                        case 2008:
                            songs2008.add(temp);
                            break;

                        case 2009:
                            songs2009.add(temp);
                            break;
                    }
                } else if (year >= 2010 && year <= 2016) {
                    switch (year) {

                        case 2010:
                            songs2010.add(temp);
                            break;

                        case 2011:
                            songs2011.add(temp);
                            break;

                        case 2012:
                            songs2012.add(temp);
                            break;

                        case 2013:
                            songs2013.add(temp);
                            break;

                        case 2014:
                            songs2014.add(temp);
                            break;

                        case 2015:
                            songs2015.add(temp);
                            break;

                        case 2016:
                            songs2016.add(temp);
                            break;
                    }
                }
                // <</editor-fold>>
            }

        }

        //print results from each year to Results.csv
        // <editor-fold>
        createResultsCSV(songs1959);

        createResultsCSV(songs1960);
        createResultsCSV(songs1961);
        createResultsCSV(songs1962);
        createResultsCSV(songs1963);
        createResultsCSV(songs1964);
        createResultsCSV(songs1965);
        createResultsCSV(songs1966);
        createResultsCSV(songs1967);
        createResultsCSV(songs1968);
        createResultsCSV(songs1969);

        createResultsCSV(songs1970);
        createResultsCSV(songs1971);
        createResultsCSV(songs1972);
        createResultsCSV(songs1973);
        createResultsCSV(songs1974);
        createResultsCSV(songs1975);
        createResultsCSV(songs1976);
        createResultsCSV(songs1977);
        createResultsCSV(songs1978);
        createResultsCSV(songs1979);

        createResultsCSV(songs1980);
        createResultsCSV(songs1981);
        createResultsCSV(songs1982);
        createResultsCSV(songs1983);
        createResultsCSV(songs1984);
        createResultsCSV(songs1985);
        createResultsCSV(songs1986);
        createResultsCSV(songs1987);
        createResultsCSV(songs1988);
        createResultsCSV(songs1989);

        createResultsCSV(songs1990);
        createResultsCSV(songs1991);
        createResultsCSV(songs1992);
        createResultsCSV(songs1993);
        createResultsCSV(songs1994);
        createResultsCSV(songs1995);
        createResultsCSV(songs1996);
        createResultsCSV(songs1997);
        createResultsCSV(songs1998);
        createResultsCSV(songs1999);

        createResultsCSV(songs2000);
        createResultsCSV(songs2001);
        createResultsCSV(songs2002);
        createResultsCSV(songs2003);
        createResultsCSV(songs2004);
        createResultsCSV(songs2005);
        createResultsCSV(songs2006);
        createResultsCSV(songs2007);
        createResultsCSV(songs2008);
        createResultsCSV(songs2009);

        createResultsCSV(songs2010);
        createResultsCSV(songs2011);
        createResultsCSV(songs2012);
        createResultsCSV(songs2013);
        createResultsCSV(songs2014);
        createResultsCSV(songs2015);
        createResultsCSV(songs2016);
        // <</editor-fold>>

        sc.close();
        fileWriter.flush();
        fileWriter.close();

    }

    //print all the data fields of each song in a list
    public static void printList(LinkedList<Song> songs) {
        for (int i = 0; i < songs.size(); i++) {
            songs.get(i).print();
        }
    }

    //write each song's data to the Results.csv
    public static void createResultsCSV(LinkedList<Song> songs) throws IOException {
        String semiDelim = ";";
        String newLineSep = "\n";

        //Write a song object list to the CSV file
        for (int i = 0; i < songs.size(); i++) {
            //if there is a valid grade level score
            if (String.valueOf(songs.get(i).getGradeLevel()) != "NaN") {
                fileWriter.append(String.valueOf(songs.get(i).getRank()));
                fileWriter.append(semiDelim);
                fileWriter.append(songs.get(i).getTitle());
                fileWriter.append(semiDelim);
                fileWriter.append(songs.get(i).getArtist());
                fileWriter.append(semiDelim);
                fileWriter.append(String.valueOf(songs.get(i).getYear()));
                fileWriter.append(semiDelim);
                fileWriter.append(String.valueOf(String.valueOf(songs.get(i).getGradeLevel())));
                fileWriter.append(semiDelim);
                fileWriter.append(String.valueOf(String.valueOf(songs.get(i).getCLIndex())));
                fileWriter.append(semiDelim);
                fileWriter.append(String.valueOf(String.valueOf(songs.get(i).getWordEntropy())));
                fileWriter.append(semiDelim);
                fileWriter.append(String.valueOf(String.valueOf(songs.get(i).getStanzaEntropy())));
                fileWriter.append(newLineSep);
            }
        }
    }

    //create hashmap of words and their syllables
    public static void createMap() throws FileNotFoundException {
        //String file = "syllablesBig.txt";
        String file = "/home/gormleyjo/CapstoneRepo/lyrical-analysis/Dehyphenate/syllablesBig.txt";

        Scanner sc = new Scanner(new File(file));
        sc.useDelimiter("\\n|,");

        String word;
        int syllables;

        while (sc.hasNext()) {
            word = sc.next();
            syllables = sc.nextInt();

            syllableMap.put(word, syllables);
            //System.out.println(word);
        }

        sc.close();
    }

}
