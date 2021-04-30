# MindPuzzle

Mind Puzzle game was developed by a 5 member team and the development 
happened from mid-January to the end of April 2021. The game was developed 
for a project called Empathizing way to build social inclusion. 
The project supports teens who are at risk social exclusion and 
dropping out from education. 

Mind Puzzle was build around the hand of mental health, meaning that 
in the game we address five areas of mental health. Game was designed 
to support the mental health of teens in their every day life.

# Technologies

Mind Puzzle is a game made for Android operating systems, 
developed with Android Studio using LibGDX and its technologies 
Screen and Scene2D. The game is made in both English and Finnish.

# Where to get it?

The game is available for free download from the Google Play Store
https://play.google.com/store/apps/details?id=fi.tamk.mindpuzzle

# More info about the project and the game

The game portal: https://webpages.tuni.fi/20tiko/#en

The client: https://www.tuni.fi/en/research/empathizing-way-build-social-inclusion

# The main functionality of the code

The hardest part of the code was figuring out how to read the game questions 
from a separate text file and have them neatly printed on the screen.

## The questions are placed in a text file in this order: theme, question, option a, option b, option c and the correct answer last.

SPORTS

Can I improve my mental health by       exercising? 

a) It depends on what kind of exercisingyou are doing. 

b) No, exercising only stresses you more 

c) Of course, exercising is a good way  to bring balance to your every day life and usually after exercising            you feel good.

c

SPORTS

My best friend doesn't like exercising  at all. How can I get them excited about exercising?

a) You could start some active hobby    together that your friend might like.

b) It's not your job to get your friendsexited about exercising.

c) Others like exercising, others don't.

a



## This is how I implemented the textfile handling in the code. 
Text file is scanned through and then put in to 2D arrays.
Line breaks are added to String lines after a certain number of characters.

    /**
     * Initializes the text files.
     *
     * @param array     the array in which questions are stored
     * @param theme     the theme to be processed within the text file
     * @param file      the text file to be processed
     */
    private void initTextFile(String[][] array,
                              String theme, FileHandle file) {
        Scanner scanner = new Scanner(file.readString());
        String line = "";

        while(scanner.hasNext()) {
            line = scanner.nextLine();
            if (line.contains(theme)) {
                column = 0;
                line = scanner.nextLine();
                if (line.contains("?")) {
                    lineUpText(array, line, 0);
                }

                line = scanner.nextLine();
                if (line.contains("a)")) {
                    lineUpText(array, line, 1);
                }

                line = scanner.nextLine();
                if (line.contains("b)")) {
                    lineUpText(array, line, 2);
                }

                line = scanner.nextLine();
                if (line.contains("c)")) {
                    lineUpText(array, line, 3);
                }

                line = scanner.nextLine();
                if (line.contains("a") ||
                        line.contains("b") || line.contains("c")) {
                    array[row][column + 4] = line;
                }

                if (row < 19) {
                    row++;
                }
                else if (row == 19) {
                    break;
                }
            }
        }

        row = 0;
        scanner.close();
    }

    /**
     * Adds line breaks to String lines.
     *
     * @param array     the array in which questions are stored
     * @param line      the line to which line breaks are added
     * @param columnNo  the value of the column in the array to which
     *                  the processed line will be added
     */
    private void lineUpText(String[][] array, String line, int columnNo) {
        String longLine = "";
        if(line.length() >= 40 && line.length() < 80) {
            longLine = new StringBuilder().append(line.substring(0, 40))
                    .append("\n").append(line.substring(40)).toString();
            array[row][columnNo] = longLine;
        } else if(line.length() >= 80 && line.length() < 120) {
            longLine = new StringBuilder().append(line.substring(0,40))
                    .append("\n").append(line.substring(40,80))
                    .append("\n").append(line.substring(80)).toString();
            array[row][columnNo] = longLine;
        } else if(line.length() >= 120 && line.length() < 150) {
            longLine = new StringBuilder().append(line.substring(0,40))
                    .append("\n").append(line.substring(40,80))
                    .append("\n").append(line.substring(80,120))
                    .append("\n").append(line.substring(120)).toString();
            array[row][columnNo] = longLine;
        } else if(line.length() >= 150) {
            longLine = new StringBuilder().append(line.substring(0,40))
                    .append("\n").append(line.substring(40,80))
                    .append("\n").append(line.substring(80,120))
                    .append("\n").append(line.substring(120,150))
                    .append("\n").append(line.substring(150)).toString();
            array[row][columnNo] = longLine;
        } else {
            array[row][columnNo] = line;
        }
    }
