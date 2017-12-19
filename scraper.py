#!/usr/bin/python
#
# File: scraper.py
# Author: Zach Kissel
# Date: 9/13/17
# Description: Copy the data scraping method of Kaytlin Walker for
# blog post: http://kaylinwalker.com/50-years-of-pop-music/ our scrape will
# preserve lyrics and not use R which is having problems coping with the
# volume of the data.
#
# Python webscraping examples can be found here:
# https://medium.freecodecamp.org/how-to-scrape-websites-with-python-and-beautifulsoup-5946935d93fe

import urllib2
from bs4 import BeautifulSoup
import base64
import csv
from unidecode import unidecode
import re

start = 1959 # Start year.
end = 2016 # End year.


#
# This function uses BeautifulSoup to scrape wikipedia song charts
# the earliest top 100 chart is 1959 (prior years it was the
# top 30).
#
def buildChartedDictionary(startYear, endYear):
    songList = []
    for i in range(startYear, (endYear + 1), 1):

        print "Processing year: " + str(i)

        # Build the correct URL
        url = "http://en.wikipedia.org/wiki/Billboard_Year-End_Hot_100_singles_of_"+str(i)

        print url

        opener = urllib2.build_opener()
        opener.addheaders = [('User-agent', 'Mozilla/5.0')]

        # Request the data at the URL
        try:
            infile = opener.open(url)
            page = infile.read()
        except urllib2.HTTPError, err:
            if err.code == 404:
                print "Page not Found"
            elif err.code == 403:
                print "Access Denied"
            else:
                print "A failure happened (" + str(err.code) + ")"

            continue

        # Build the DOM for the page.
        soup = BeautifulSoup(page, 'html.parser')

        # Find the table
        table = soup.find("table", {"class" : "wikitable sortable"})

        # Add songs to the list of songs
        rcount = 0;
        for row in table.findAll("tr"):
            cells = row.findAll("td")
            if len(cells) >= 2:
                rcount = rcount + 1
                if len(cells) == 3:
                    rank = cells[0].text.replace('"', '')
                    title = cells[1].text.replace('"', '')
                    artist = cells[2].text.replace('&amp;', ' & ')
                elif len(cells) == 2:
                    rank = str(rcount)
                    title = cells[0].text.replace('"', '')
                    artist = cells[1].text.replace('&amp;', ' & ')

                # Finally, make sure everything is ASCII.
                cleanedTitle = unidecode(title);
                cleanedTitle.encode("ascii")
                cleanedArtist = unidecode(artist);
                cleanedArtist.encode("ascii")
                cleanedArtist.replace('"', '')

                # Remove the artist feature, feat, feat., with, duet, and for the
                # artist.
                artist = re.split('featuring | feat | feat. | with | duet | ,', cleanedArtist)

                cleanedArtist = artist[0].rstrip(' ');

                song = {'year': str(i), 'rank': rank, 'title': cleanedTitle, 'artist': cleanedArtist, 'lyrics': ""}
                songList.append(song)

    # Return the complete list of songs.
    return songList

def checkMetroLyrics(title, artist):

    lyrics = ""
    url = "http://metrolyrics.com/" + title +"-lyrics-" + artist + ".html"
    print "Processing: " + url

    # Get the page
    try:
        page = urllib2.urlopen(url)
    except urllib2.HTTPError, err:
        if err.code == 404:
            print "Page not Found"
        elif err.code == 403:
            print "Access Denied"
        else:
            print "A failure happened (" + str(err.code) + ")"

        return ""

    # Parse the page
    soup = BeautifulSoup(page, 'html.parser')

    # Find the lyrics div
    lyricDiv = soup.find("div", {"class" : "lyrics-body"})

    lyrics = ""

    if lyricDiv is None:
        return ""

    # Walk the lyrics parsing out the verses.
    for verse in (lyricDiv.findAll("p",  {"class" : "verse"})):
        lyrics += verse.text.replace("<br>", "\n") + "\n\n"

    # Correct trailing newlines
    lyrics = lyrics.rstrip("\n")
    lyrics += "\n"

    return lyrics

#
# Check song lyrics for the lyrics
#
def checkSongLyrics(title, artist):
        lyrics = ""

        # Build the URL for song lyrics
        url = "http://www.songlyrics.com/" + title + "/" + artist +"-lyrics"
        print "Processing: " + url

        # If we don't fake a user agent we get denied access.
        opener = urllib2.build_opener()
        opener.addheaders = [('User-agent', 'Mozilla/5.0')]

        # Get the page
        try:
            infile = opener.open(url)
            page = infile.read()
        except urllib2.HTTPError, err:
            if err.code == 404:
                print "Page not Found"
            elif err.code == 403:
                print "Access Denied"
            else:
                print "A failure happened (" + str(err.code) + ")"

            return ""

        # Parse the page
        soup = BeautifulSoup(page, 'html.parser')

        # Find the lyrics div
        lyricDiv = soup.find("p", {"id" : "songLyricsDiv"})

        # Make sure the lyricDiv is valid.
        if lyricDiv is None:
            return ""

        lyrics = lyricDiv.text.replace("<br />", "\n")

        lyrics += "\n"

        return lyrics

#
# Check lyrics mode for the lyrics
#
def checkLyricsMode(title, artist):
    lyrics = ""

    fixedTitle = title.replace("-", "_");
    fixedArtist = artist.replace("-", "_")

    # Build the URL for song lyrics
    url = "http://www.lyricsmode.com/lyrics/" + artist[0] + "/" + fixedArtist + "/" + fixedTitle + ".html"
    print "Processing: " + url

    # If we don't fake a user agent we get denied access.
    opener = urllib2.build_opener()
    opener.addheaders = [('User-agent', 'Mozilla/5.0')]

    # Get the page
    try:
        infile = opener.open(url)
        page = infile.read()
    except urllib2.HTTPError, err:
        if err.code == 404:
            print "Page not Found"
        elif err.code == 403:
            print "Access Denied"
        else:
            print "A failure happened (" + str(err.code) + ")"

        return ""

    # Parse the page
    soup = BeautifulSoup(page, 'html.parser')

    # Find the lyrics div
    lyricDiv = soup.find("p", {"id" : "lyrics_text"})

    # Make sure the lyricDiv is valid.
    if lyricDiv is None:
        return ""

    lyrics = lyricDiv.text.replace("<br />", "\n")

    lyrics += "\n"

    return lyrics


#
# Get lyrics from easily parsable sites
# For now, we are just working with one website.
# to preserve all of the formatting of the lyrics but, also allow for storage
# in a csv file, we base64 encode the lyrics we construct.
#
def getLyrics(title, artist):
        # Clean up title
        cleanedTitle = title.replace(' ', '-')
        cleanedTitle = cleanedTitle.replace("'", '')
        cleanedTitle = cleanedTitle.replace('"', '')
        cleanedTitle = cleanedTitle.replace(',', '')
        cleanedTitle = cleanedTitle.replace('(', '')
        cleanedTitle = cleanedTitle.replace(')', '')
        cleanedTitle = cleanedTitle.replace('.', '')
        cleanedTitle = cleanedTitle.replace('&', "and")

        # Clean up artist name still need to remove "featuring" artist
        cleanedArtist = artist.replace(' & ', ' ')
        cleanedArtist = cleanedArtist.replace("'", '')
        cleanedArtist = cleanedArtist.replace('"', '')
        cleanedArtist = cleanedArtist.replace('.', '')
        cleanedArtist = cleanedArtist.replace(',', '')
        cleanedArtist = cleanedArtist.replace(' ', '-')

        lyrics = checkMetroLyrics(cleanedTitle, cleanedArtist)

        if lyrics == "":
            lyrics == checkSongLyrics(cleanedTitle, cleanedArtist)

        if lyrics == "":
            lyrics == checkLyricsMode(cleanedTitle, cleanedArtist)

        # Encode the lyrics as a base-64 number and return that.
        return base64.b64encode(lyrics.encode('utf-8'))

songs = buildChartedDictionary(start, end)

for song in songs:
    song['lyrics'] = getLyrics(song['title'], song['artist'])

notFound = 0 # Number of missing songs.
with open('songs.csv', 'wb') as f:
    for entry in songs:
        if not (entry['lyrics'] == base64.b64encode("".encode('utf-8'))):
            f.write(entry['year'] + ";" + entry['rank'] + ";" + entry['artist'] + ";" + entry['title'] + ";" + entry['lyrics'] + "\n")
        else:
            notFound = notFound + 1

found = ((100 * (end - start + 1)) - notFound)
print "Found: " + str(found)
print "Missing: " + str(notFound)
