@REM compiles all java code in the working directory

javac -classpath ..\libs\guava-18.0.jar *.java

@REM Asks the user for the mapnumber

@ECHO OFF

Set /p mapNumber=number of the map to play on

set MAP=maps/map%mapNumber%.txt

@ECHO ON

@REM runs the game

@REM java -jar tools/PlayGame.jar %MAP% 1000 1000 log.txt "java MyBot" "java -jar example_bots/DualBot.jar"| java -jar tools/ShowGame.jar
java -jar tools/PlayGame.jar %MAP% 1000 1000 log.txt "java MyBot" "example_bots/PlanetwarBot.exe"| java -jar tools/ShowGame.jar
