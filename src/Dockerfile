FROM openjdk:17

WORKDIR /app

COPY rmi/ /app/rmi/

RUN javac rmi/interfaces/*.java rmi/servidor/*.java

EXPOSE 1099

ENV RMI_HOSTNAME=localhost

CMD ["sh", "-c", "java -Djava.rmi.server.hostname=$RMI_HOSTNAME rmi.servidor.ServidorRMI"]

