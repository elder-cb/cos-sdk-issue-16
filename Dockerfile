FROM ibmcom/ibmjava:8-sdk

RUN mkdir -p /issue16
WORKDIR /issue16
COPY . .

RUN ./gradlew build --no-daemon

ENTRYPOINT [ "./gradlew", "--no-daemon" ]
CMD ["run"]
