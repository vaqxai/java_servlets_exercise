@ECHO off

dir

dir /s /B *.java > sources.txt
javac -cp lib\servlet-api.jar;lib\sqlite-jdbc.jar -d bin @sources.txt

xcopy bin\* web\WEB-INF\classes /e /Y
xcopy lib\* web\WEB-INF\lib /e /Y

del tpo5.war /f

jar -cvf tpo5.war -C web\ .