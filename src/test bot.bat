javac *.java
@ECHO OFF
Set /p mapNumber=number of the map to play on
set MAP=maps/map%mapNumber%.txt
@ECHO ON
java -jar tools/PlayGame.jar %MAP% 1000 1000 log.txt "java MyBot" "java MyBot" | java -jar tools/ShowGame.jar