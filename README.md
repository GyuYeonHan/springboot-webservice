# 스프링 부트를 이용한 웹 게시판 구현

이동욱 님의 "스프링 부트와 AWS로 혼자 구현하는 웹 서비스" 책에 나오는 예제에 대한 실습 및 그 외 추가적인 기능을 구현해보고 있습니다.

홈 화면: http://ec2-3-35-122-207.ap-northeast-2.compute.amazonaws.com/

(테스트 ID: testuser, password: test)


<hr />


## &#128204; 사용 기술

#### 개발
- Java
- Gradle
- Spring Boot
- JPA
- Mustache -> Thymeleaf (기존 Mustache 구현을 Thymeleaf로 변경하여 구현)

#### CI/CD
- Travis CI
- AWS EC2, RDS, S3, CodeDeploy 

## [전체 구조]
![image](https://user-images.githubusercontent.com/54987488/124282775-294dd780-db86-11eb-9d06-35000fd657a6.png)


## &#128204; 기능 정의
### 실습
+ 게시글 조회, 게시글 등록, 게시글 수정, 게시글 삭제 (기본 CRUD Operation)
### 추가
+ 실습 내용 외 Validation 기능, 로그인 기능 추가
+ 로그인 체크 인터셉터 적용

![image](https://user-images.githubusercontent.com/54987488/123799298-0b346d00-d923-11eb-8621-8e1ff5c1d9c7.png)
![image](https://user-images.githubusercontent.com/54987488/120884875-c0278280-c620-11eb-8b39-a82c7e36c0ae.png)
![image](https://user-images.githubusercontent.com/54987488/120892254-515e1f80-c648-11eb-9668-c1ee00f3f5b8.png)
![image](https://user-images.githubusercontent.com/54987488/120892274-7488cf00-c648-11eb-9aa0-81ac5a52368b.png)


<br>
