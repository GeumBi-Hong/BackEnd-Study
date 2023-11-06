# Connection Pool

## DB Connection Pool이란 개념이 왜 나왔을까?

- WAS(Web Application Server)와 DB(DataBase)사이의 연결에는 **비용이 발생**하게 된다.
- DB 서버 접속을 위한 과정을 HTTP 요청이 있을때마다 DB서버에 지속적으로 접근하는 (Connection 생성) 작업이 **반복**적으로 이루어지게 된다.
- 이때 DB서버에 최초로 연결하여 Connection 객체를 생성하는 작업은 연결 설정과 관련된 초기화 작업을 수반하고, **이로 인해서 시간과 리소스가 소비된다**.
- 이러한 이유로 계속해서 DB에 접근하기 위해 새로운 Connection을 생성해 낸다면 **비용적인 측면에서 성능 저하를 야기하게 될것이다**.

### 어떤 비용인데? 뭐 때문에 성능 저하가 일어나는데?

- A : 데이터베이스는 최초 접속시 TCP 통신을 진행한다. 데이터를 전송하기 전에 정확한 전송을 보장하기 위한 3-HandShake 단계와 데이터 전송이 종료되면 리소스를 정리하기 위한 4-HandShake 단계가 이루어진다.
- 매번 DB와 연결되기 위해 3,4 HandShake를 거친다고하면(네트워크처리) ? 연결이 필요한 객체(socket 등등)를 만들고 처리하는 과정은 굉장히 느리다.

## Connection Pool의 역할

- 따라서 이러한 비용을 줄이고자 Connection Pool이 등장했다.
- `Connection : 연결`  + `Pool :  웅덩이(?) or 모으다` ⇒ 연결을 모아놓은?!

![figure_1.png](DB%20Connection%20Pool%20aa56458517e6401a8cc22c199979ad70/figure_1.png)


- **데이터베이스와 연결된 커넥션을 미리 만들어 놓고 이를 Pool에서 관리**한다는 것을 의미한다.
- WAS 실행시 일정량의 Connection 객체를 **미리 생성**하여 Pool이라는 **공간(캐시)에 저장**해둔다.
- 필요할 때마다 커넥션 풀의 DB 커넥션을 **이용하고 반환**한다. 미리 만들어 놓은 커넥션을 이용하면 **비용이 줄어들게 되며, DB에 빠르게 접근**할 수 있게된다.












   (이미지 출처 :[https://technet.tmaxsoft.com/upload/download/online/jeus/pver-20150722-000001/server/chapter_datasource.html](https://technet.tmaxsoft.com/upload/download/online/jeus/pver-20150722-000001/server/chapter_datasource.html))


