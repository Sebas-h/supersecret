@REM compiles all java code in the working directory
javac *.java

@REM Asks the suer for the mapnumber
@ECHO OFF
Set /p mapNumber=number of the map to play on
set MAP=maps/map%mapNumber%.txt
@ECHO ON

@REM runs the game
java -jar tools/PlayGame.jar %MAP% 1000 1000 log.txt "java MyBot" "java -jar exapmple_bots\ProspectorBot.jar" | java -jar tools/ShowGame.jar

