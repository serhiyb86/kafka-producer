@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem
@rem SPDX-License-Identifier: Apache-2.0
@rem

@if "%DEBUG%"=="" @echo off
@rem ##########################################################################
@rem
@rem  kafka-producer startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.
@rem This is normally unused
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and KAFKA_PRODUCER_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if %ERRORLEVEL% equ 0 goto execute

echo. 1>&2
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH. 1>&2
echo. 1>&2
echo Please set the JAVA_HOME variable in your environment to match the 1>&2
echo location of your Java installation. 1>&2

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo. 1>&2
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME% 1>&2
echo. 1>&2
echo Please set the JAVA_HOME variable in your environment to match the 1>&2
echo location of your Java installation. 1>&2

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\kafka-producer-1.0-SNAPSHOT.jar;%APP_HOME%\lib\kafka-avro-serializer-5.3.0.jar;%APP_HOME%\lib\kafka-schema-registry-client-5.3.0.jar;%APP_HOME%\lib\kafka-clients-5.3.0-ccs.jar;%APP_HOME%\lib\avro-1.11.1.jar;%APP_HOME%\lib\logback-classic-1.2.12.jar;%APP_HOME%\lib\common-config-5.3.0.jar;%APP_HOME%\lib\common-utils-5.3.0.jar;%APP_HOME%\lib\zkclient-0.10.jar;%APP_HOME%\lib\zookeeper-3.4.14.jar;%APP_HOME%\lib\slf4j-api-1.7.36.jar;%APP_HOME%\lib\zstd-jni-1.4.0-1.jar;%APP_HOME%\lib\lz4-java-1.6.0.jar;%APP_HOME%\lib\snappy-java-1.1.7.3.jar;%APP_HOME%\lib\jackson-annotations-2.12.7.jar;%APP_HOME%\lib\jackson-databind-2.12.7.jar;%APP_HOME%\lib\jackson-core-2.12.7.jar;%APP_HOME%\lib\commons-compress-1.21.jar;%APP_HOME%\lib\logback-core-1.2.12.jar;%APP_HOME%\lib\spotbugs-annotations-3.1.9.jar;%APP_HOME%\lib\jline-0.9.94.jar;%APP_HOME%\lib\audience-annotations-0.5.0.jar;%APP_HOME%\lib\netty-3.10.6.Final.jar;%APP_HOME%\lib\jsr305-3.0.2.jar


@rem Execute kafka-producer
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %KAFKA_PRODUCER_OPTS%  -classpath "%CLASSPATH%" org.example.AvroKafkaProducer %*

:end
@rem End local scope for the variables with windows NT shell
if %ERRORLEVEL% equ 0 goto mainEnd

:fail
rem Set variable KAFKA_PRODUCER_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
set EXIT_CODE=%ERRORLEVEL%
if %EXIT_CODE% equ 0 set EXIT_CODE=1
if not ""=="%KAFKA_PRODUCER_EXIT_CONSOLE%" exit %EXIT_CODE%
exit /b %EXIT_CODE%

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
