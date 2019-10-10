export JAVA_HOME=/usr/local/java/bin
export ANT_HOME=/usr/local/apache-ant-1.9.7
/usr/local/java/bin/java -Xmx512M -cp $ANT_HOME/lib/ant-launcher.jar -Dant.home="$ANT_HOME" -Dfile.encoding="ISO-8859-1" -Dant.library.dir="$ANT_HOME/lib" org.apache.tools.ant.launch.Launcher -f buildsh.xml $@
