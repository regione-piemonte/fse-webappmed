@echo off
echo Setting environment ...

set JAVA_HOME=C:\Program Files\Java\jdk-11.0.17
set CLASSPATH=%JAVA_HOME%lib\tools.jar;
set PATH=%JAVA_HOME%bin;%PATH%
set JBOSS_HOME=C:\CSI\Progetti\DMA\Server\wildfly-23.0.2.Final
set arg1=%1

IF "%arg1%"=="" (
    set arg1=clean-classes clean-dist deployLocal
)

echo Building ...
call ant -Dtarget=tst %arg1%  


echo.
