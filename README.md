<h1>park</h1>
<h2>1.개요</h2>
 기존 다양한 놀이공원 웹 사이트를 보면서 가장 핵심이라 생각하는 놀이공원 지도에서 어트랙션의 위치나 다양한 편의시설의 위치와 후기, 정보를 보려면 각자 다른 카테고리를 통하여 찾아 들어가야 하는 불편함을 느껴 카카오 지도 api 를 이용하여 각 어트랙션, 편의시설 등 위치와 상세정보, 후기를 바로 찾아 볼 수 있도록 웹 사이트를 제작 하게 되었습니다.
 <h2>2.구성 및 담당자</h2>
 
 ### 오은석(팀장)

- 프로젝트 전체 가이드라인
- 관리자 페이지 가이드라인
- Map 시설 기본 기능 CRUD
- 관리자 페이지 및 기능 (Map 시설관리, 시설 상세뷰)

### 임진구

- 멤버 관련 CRUD
- 로그인/로그아웃
- 소셜로그인 (카카오)
- 회원가입 및 회원정보수정
- 관리자 페이지 및 기능 (전체적인 회원관리, 회원탈퇴자 처리)

### 고지훈

- 메인페이지 작성
- 티켓 관련 CRUD
- 리뷰 관련 CRUD
- 관리자 페이지 및 기능 (티켓 추가 및 삭제)

### 오승훈

- 마이페이지
- 1:1 문의 관련 CRUD
- 관리자 페이지 및 기능 (문의 답변 추가 및 문의 삭제)

 <h2>3.사용한 기술스택 및 개발환경</h2>
 
 | Category 	| Techs 	|
|---	|:---:	|
| 📋 협업 	|   ![Notion](https://img.shields.io/badge/Notion-000000.svg?style=flat-square&logo=notion&logoColor=white)   ![GitHub](https://img.shields.io/badge/GitHub-181717.svg?style=flat-square&logo=github&logoColor=white) 	|
| 📚 프론트엔드 	| ![HTML5](https://img.shields.io/badge/HTML5-%23E34F26.svg?style=flat-square&logo=html5&logoColor=white) ![CSS3](https://img.shields.io/badge/CSS3-%231572B6.svg?style=flat-square&logo=css3&logoColor=white) ![JavaScript](https://img.shields.io/badge/Javascript-%23323330.svg?style=flat-square&logo=javascript&logoColor=%23F7DF1E)  ![Bootstrap](https://img.shields.io/badge/Bootstrap-%23563D7C.svg?style=flat-square&logo=bootstrap&logoColor=white) | 
| 💻 백엔드 및 라이브러리 	| ![Spring](https://img.shields.io/badge/Spring-%236DB33F.svg?style=flat-square&logo=spring&logoColor=white) ![springboot](https://img.shields.io/badge/springboot-%6DB33F.svg?style=flat-square&logo=springboot&logoColor=white) ![springsecurity](https://img.shields.io/badge/springsecurity-%6DB33F.svg?style=flat-square&logo=springsecurity&logoColor=white) ![JSON](https://img.shields.io/badge/JSON-000000.svg?style=flat-square&logo=json&logoColor=white) ![Thymeleaf](https://img.shields.io/badge/Thymeleaf-%23005C0F.svg?style=flat-square&logo=Thymeleaf&logoColor=white) ![JQuery](https://img.shields.io/badge/jquery-%230769AD.svg?style=flat-square&logo=jquery&logoColor=white) ![AJAX](https://img.shields.io/badge/AJAX-%231572B6.svg?style=flat-square&logo=AJAX&logoColor=white) ![Swiper](https://img.shields.io/badge/Swiper-6332F6.svg?style=flat-square&logo=swiper&logoColor=white) ![axios](https://img.shields.io/badge/axios-5A29E4.svg?style=flat-square&logo=axios&logoColor=white) ![swagger](https://img.shields.io/badge/Swagger-85EA2D.svg?style=flat-square&logo=swagger&logoColor=white) ![junit5](https://img.shields.io/badge/junit5-%25A162.svg?style=flat-square&logo=junit5&logoColor=white) ![Log4j](https://img.shields.io/badge/Log4j-%23FA0F00.svg?style=flat-square&logo=Log4j&logoColor=white) |
| ☁️ 환경 | ![IntellijIDEA](https://img.shields.io/badge/IntellijIDEA-000000.svg?style=flat-square&logo=intellijidea&logoColor=white) ![MySQL](https://img.shields.io/badge/Mysql-4479A1.svg?style=flat-square&logo=mysql&logoColor=white) |
| 💾 배포 	| ![AWS](https://img.shields.io/badge/AWS-%23FF9900.svg?style=flat-square&logo=amazon-aws&logoColor=white) |

 <h2>4.데이터 베이스 구조</h2>
 
 <h2>5.구현기능</h2>
