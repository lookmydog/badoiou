echo on
@REM �]�w�A���M�׸��| Path
SETLOCAL
set PROJECTPATH=C:\Users\�i��\workspace\badoiou_Client
set LOG4J_LIB_PATH=%PROJECTPATH%\lib\log4j-1.2.9.jar
set JSON_LIB_PATH=%PROJECTPATH%\lib\json.jar
set BADOIOU_MAIN=Main.badoiou_Client
set LOG4J_PROPERITES=%PROJECTPATH%\log4j.properites

java -cp %PROJECTPATH%\bin;%LOG4J_LIB_PATH%;%JSON_LIB_PATH% %BADOIOU_MAIN% %LOG4J_PROPERITES%

ENDLOCAL

pause