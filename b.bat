
SET JAVA_HOME=lib/Java/jdk1.7.0_17
SET ANT_HOME=lib/apache-ant-1.8.1
java -Xmx512M -XX:MaxPermSize=256M -cp %ANT_HOME%/lib/ant-launcher.jar -Dant.home="%ANT_HOME%" -Dant.library.dir="%ANT_HOME%/lib" org.apache.tools.ant.launch.Launcher -f build.xml %*
