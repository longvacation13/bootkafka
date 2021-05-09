# bootkafka
springboot kafka 구현

카프카 생성 방법  
[ 카프카 사용 예제 - 잘 나와있음 ]
https://geonyeongkim-development.tistory.com/4


[ 카프카 cmd 테스트 방법 ] 
1. Zookeeper 실행 
bin\windows\zookeeper-server-start.bat d:\kafka\kafka_2.12-2.5.0\config\zookeeper.properties

2. 카프카 서버 실행 ( kafka daemon ) 
bin\windows\kafka-server-start.bat d:\kafka\kafka_2.12-2.5.0\config\server.properties

3. 토픽생성 
bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic testjy16ahn
n
4. 토픽삭제 
bin\windows\kafka-topics.bat --delete --zookeeper localhost --topic toptictest

5. 토픽목록확인 
bin\windows\kafka-topics.bat --list --zookeeper localhost:2181

6. 카프카 컨슈머 시작 ( 입력 후 아무 변화가 없어야 정상 ) 
bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic testjy16ahn

7. 카프카 프로듀서 시작 
bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic testjy14ahn

8. 카프카 프로듀서 cmd에서 메시지 입력 
> 카프카 컨슈머에서 메시지 수신 확인 가능 


[카프카 관련] - https://man-tae.tistory.com/5
https://dadk.tistory.com/5

1. 카프카 설치 
2. 카프카 설정 
3. 카프카 실행 
3-1. 주키퍼 실행 
3-2. 카프카 실행
4. 카프카 예제 
4-1. 카프카 토픽생성 
4-2. 카프카 토픽 리스트 조회 
4-3. 카프카 컨슈머 시작 
4-4. 카프카 프로듀서 시작 
5. 메이븐과 이클립스를 이용한 카프카 클러스터 구축  
5-1. 프로젝트 생성  ( maven project ) 
5-2. dependency 추가 ( pom.xml ) 
5-3. consumer 작성 ( java ) 
5-4. producer 작성 ( java ) 
5-5. 주키퍼 실행 -> 카프카 실행 -> Run Producer -> Run Consumer
