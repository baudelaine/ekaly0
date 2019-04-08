export WLP_HOME='/opt/wlp'
export WLP_SRV_NAME='defaultServer'
export APP_NAME='app'
alias stwlp='$WLP_HOME/bin/server start $WLP_SRV_NAME'
alias spwlp='$WLP_HOME/bin/server stop $WLP_SRV_NAME'
alias rmwlplogs='rm -rf $WLP_HOME/usr/servers/$WLP_SRV_NAME/logs/*'
alias rstwlp='spwlp && rmwlplogs  && stwlp'
alias cnfwlp='/usr/bin/vi $WLP_HOME/usr/servers/$WLP_SRV_NAME/server.xml'
alias monwlp='tail -f $WLP_HOME/usr/servers/$WLP_SRV_NAME/logs/messages.log'
alias logwlp='/usr/bin/vi $WLP_HOME/usr/servers/$WLP_SRV_NAME/logs/messages.log'
alias statwlp='$WLP_HOME/bin/server status $WLP_SRV_NAME'
alias pidwlp="ps -eaf | grep -v grep | grep $WLP_SRV_NAME'$' | awk '{print \$2}'"
alias kwlp='kill -9 $(pidwlp)'
alias appwlp='/usr/bin/vi $WLP_HOME/usr/servers/$WLP_SRV_NAME/apps/$APP_NAME.war.xml'

JAVA_HOME=/opt/JDK/jre
MAVEN_HOME=/opt/apache-maven-3.6.0
GC_HOME=/opt/google-cloud-sdk

JAVA_PATH=/opt/JDK/bin
PATH=$JAVA_PATH:$MAVEN_HOME/bin:$GC_HOME/bin:$PATH

export JAVA_HOME PATH

