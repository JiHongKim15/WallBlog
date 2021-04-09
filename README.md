# TEAM
- SSAFY 3기 광주 2반 201조 싸피탈출넘버원
- **팀장** 노경준
- **팀원** 이로운이, 정현석, 노순건, 이완희 김지홍


# 실행 환경 & 방법
### 실행
Window 10 Chrome
1. STS - Spring boot 실행
2. https://localhost:8080 접속

--> 추후 AWS를 통해 클라이언트 배포
### 환경설정
1. STS

2. VS code
  -> Frontend 폴더 cmd 실행
  -> yarn install
  -> yarn serve(실행)
  -> npm install --save vue-session(세션사용)
  -> yarn add core-js(세션 사용)

  -> vue add vuetify(vuetify 설치)

  

3. vue-bootstrap

# 프로그램 기능
### Keyword 검색 & 블로그 웹 사이트
#### 1. Main
- 키워드 검색
- 로그인
    - 이메일, 패스워드
    - 로그인 후 사용자 닉네임 화면 표시
- 회원가입

#### 2. Search
- 검색 결과 표시
- 공용 데이터
    - 로그인을 한 사용자라면 누구나 수정 가능
    - 비회원인경우, 읽기만 가능
- 키워드와 관련된 블로그 리스트 표시
    - 블로그 제목, 글쓴이, 작성일자, 조회수, 좋아요 표시

#### 3. Blog
- 글 작성
- 글 수정
- 글 삭제
- 파일 업로드

#### 4. MyPage
- 내가 쓴 글 목록
- 회원 정보 수정
    - 이메일, 닉네임 수정 불가능
    - 비밀번호 수정 가능
    - 프로필, 자기소개 등록
- 회원 정보 표시


# 개발 환경
1. Window 10
2. STS - Spring Boot
3. Vue, yarn, axios
4. Java, REST API, MyBatis
5. HTML, CSS, Bootstrakp
6. MariDB

+) Git, Jira 협업 툴 이용


# 코드
MVC 패턴
### BackEnd(BE)
#### 1. DTO/VO
- User
    - Users: 로그인
    - SignupRequest: 회원가입
- Blog
    - Blog: 게시글 번호, 키워드, 블로그 제목, 내용, 글쓴이, 좋아요, 조회수, 작성시간
    - BlogResponse: Blog list와 Keyword 구분하여 전달
#### 2. Service
#### 3. Dao
- UserSignup: 회원관리 CRUD MyBatis 연동
- BlogDao: Blog CRUD MyBatis 연동
#### 4. Controller
- AccountController: 회원 관리
- BlogController: 블로그 관리
#### 5. mappers
- blog.xml: Blog CRUD MyBatis
- user.xml: 회원 CRUD MyBatis

### FrontEnd(FE)
#### page
##### blog
- MyList: 로그인 한 회원의 글 쓴 리스트 출력
##### user
- Join: 회원가입
- Login: 로그인
- Update: 회원수정
#### components\common
- Header: 네이게이션바 & 로그인, 로그아웃
