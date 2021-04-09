##### 20.07.13
# 환경 구축

#### Frontend

1. Node.js 설치
2. yarn 설치
3. cmd 실행
   - yarn global add @vue/cli
   - yarn install
   - cd {프론트엔드 위치}
   - yarn serve
4. https://localhost:8080 접속

#### Backend

1. STS 설치

2. 마리아디비 설치 / DBeaver 설치

   - user db 설정

3. STS에서 import로 backend 폴더 불러오기

   - application.properties 설정

     - server.servlet.context-path=/test
       server.port=8080

       spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
       spring.datasource.url=jdbc:mysql://localhost/test
       spring.datasource.username=root
       spring.datasource.password=1234

       spring.mvc.view.prefix=/WEB-INF/views
       spring.mvc.view.suffix=.jsp

   - DB 연결 (db명 / pw)

4. WebBlogApplication.java -> ctrl + f11로 실행

5. https://localhost:8080/test



# 스켈레톤 리뷰

#### 어노테이션

- @Crossorigin

  CORS(corss-origin resource sharing): 웹페이지의 제한된 자원을 외부 도메인에서 접근을 허용해주는 매커니즘

- User DB 실행 필요

----


##### 20.07.14
### DONE

- 회원가입
- 회원수정(프로필 업데이트x)
- 회원탈퇴

----

##### 20.07.15

### DONE

- 블로그 CURD
- 회원수정 - 프로필
- MatterMost 연결

----

##### 20.07.16

### 해야할 일

- Marge
- Vue와 연동
- 에러수정

# DB

- User
  - uid
  - password
  - email
  - image
  - introduce