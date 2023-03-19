#bae-image
FROM openjdk:11
#변수설정(빌드파일의 경로)
ARG JAR_File=target/*.jar
#빌드파일을 컨테이너로 복사
COPY ${JAR_File} app.jar
#jar 파일 실행
ENTRYPOINT ["java","-jar","/app.jar"]