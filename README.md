<h1>park</h1>
<h2>1.개요</h2>
 기존 다양한 놀이공원 웹 사이트를 보면서 가장 핵심이라 생각하는 놀이공원 지도에서 어트랙션의 위치나 다양한 편의시설의 위치와 후기, 정보를 보려면 각자 다른 카테고리를 통하여 찾아 들어가야 하는 불편함을 느껴 카카오 지도 api 를 이용하여 각 어트랙션, 편의시설 등 위치와 상세정보, 후기를 바로 찾아 볼 수 있도록 웹 사이트를 제작 하게 되었습니다.
 <h2>2.구성 및 담당자</h2>
 
 ### 오은석

- 프로젝트 전체 가이드라인
- 관리자 페이지 가이드라인
- Map 시설 기본 기능 CRUD
- 관리자 페이지 및 기능 (Map 시설관리, 시설 상세뷰)

### 임진구(본인)

- 멤버 관련 CRUD
- 로그인/로그아웃
- 소셜로그인 (카카오)
- 회원가입 및 회원정보수정
- 관리자 페이지 및 기능 (전체적인 회원관리, 회원탈퇴 신청 처리)

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
 
 ![스크린샷 2023-11-29 오후 12 18 10](https://github.com/jingugugu/park/assets/116573862/d7df642c-19f8-469d-b92d-f1702802f13a)
 
 <h2>5.구현기능</h2>

 <details>
  <summary>회원관련</summary>
  <h3>회원가입</h3>
  
  로그인 페이지에서 회원가입을 클릭하여 회원가입 페이지로 이동합니다.
  
  ![로그인창](https://github.com/jingugugu/park/assets/116573862/313e7e78-0bc0-4910-bb8b-e67500a3f4d2)

  비어있는 항목이 있으면 회원가입 처리를 거절합니다.

  ![스크린샷 2023-11-29 오후 7 05 04](https://github.com/jingugugu/park/assets/116573862/ede323c7-3260-4e91-b66d-4334f9b81217)

  이메일인증을 클릭하여 해당 이메일로 인증키를 발송받은 후 이메일인증을 완료합니다.

  ![스크린샷 2024-01-25 오후 3 20 55](https://github.com/jingugugu/park/assets/116573862/c7f43e1b-4767-4f3f-8cf2-f85edf718c80)

  ![스크린샷 2024-01-25 오후 3 21 16](https://github.com/jingugugu/park/assets/116573862/170afb24-6614-4e67-b5e6-b2de62e14f05)

  ![스크린샷 2024-01-25 오후 3 21 34](https://github.com/jingugugu/park/assets/116573862/2997155e-69a2-44b1-8b27-c4884e77a36b)

  모든 항목을 입력후 유효성검사 통과후에 회원가입을 완료합니다.

  ![스크린샷 2024-01-25 오후 3 23 58](https://github.com/jingugugu/park/assets/116573862/eb224aa6-0ff7-406d-b06b-c91c1edb841d)

  <h3>로그인</h3>
  
  로그인 시도 시 Spring Security 를 이용하여 가입된 아이디와 비밀번호를 비교하여 일치한다면 로그인이 성공하고, 일치하지 않다면 경고 메세지를 출력합니다.
  자동 로그인 기능도 지원하여 자동 로그인 선택 여부를 선택하여 로그인을 할 수 있습니다.

  ![로그인](https://github.com/jingugugu/park/assets/116573862/dbfe4324-c0cf-4b76-8c7c-7704d31ba326)

  카카오 로그인도 지원합니다

  ![카카오로그인](https://github.com/jingugugu/park/assets/116573862/c98a67a7-74fd-433b-96d9-a72adcc5565c)

  <h3>비밀번호 찾기</h3>
  
  이메일 인증을 통하여 인증키 확인시 비밀번호 수정이 가능합니다.

  <img width="1129" alt="비밀번호찾기" src="https://github.com/jingugugu/park/assets/116573862/dd1595f6-8c77-4d0a-ac88-e22cca7af0e8">

  <img width="905" alt="비밀번호찾기2" src="https://github.com/jingugugu/park/assets/116573862/f3eb4486-ce7b-4e8d-8911-d817b34d2e4f">

  <h3>마이페이지</h3>
  
  마이페이지에서 내 정보를 확인,수정,탈퇴 가 가능하고 내가 작성한 리뷰, 문의, 티켓구매내역을 확인할 수 있습니다.

  ![스크린샷 2024-01-25 오후 4 21 48](https://github.com/jingugugu/park/assets/116573862/c35124f6-f3f0-4975-9a48-fbc456d16865)
  ![스크린샷 2024-01-25 오후 4 23 22](https://github.com/jingugugu/park/assets/116573862/ecfa0ce2-0f9a-485f-8f11-680be1e37231)
  ![스크린샷 2024-01-25 오후 4 23 32](https://github.com/jingugugu/park/assets/116573862/d413a028-ecf6-43bb-8215-55213279e0bd)
  ![스크린샷 2024-01-25 오후 4 23 37](https://github.com/jingugugu/park/assets/116573862/adda44a2-0f34-4af9-8433-3387b5f5bc1b)

  <h3>정보수정</h3>
  
  정보수정은 기존 비밀번호를 입력받아야 수정이 가능하며, 비밀번호 변경을 원하는 경우 비밀번호 변경을 클릭 후 원하는 비밀번호를 입력합니다.
  
  <img width="905" alt="정보수정2" src="https://github.com/jingugugu/park/assets/116573862/adfe5a2e-6b81-4792-b626-5d11f5e01875">
  <img width="905" alt="정보수정" src="https://github.com/jingugugu/park/assets/116573862/1d36ef21-7f4e-4bf9-8abb-36d08c3474a2">

  <h3>회원탈퇴</h3>
  
  회원탈퇴는 바로 탈퇴처리가 이루어지지 않고 탈퇴 신청을 하고 관리자가 검토 후 탈퇴처리를 하는 형식입니다.
  
  ![스크린샷 2024-01-25 오후 4 43 34](https://github.com/jingugugu/park/assets/116573862/a05b91e9-0a10-4db0-9687-247ec6ea515c)
 </details>

<details>
  <summary>티켓예매</summary>
 
  티켓 리스트는 메인화면 또는 상단 메뉴에 티켓 카테고리에서 확인하여 구매가 가능합니다.
 
  ![티켓예매](https://github.com/jingugugu/park/assets/116573862/b2485a91-07ab-45ab-806a-c8efb08fdf51)
  ![티켓구매](https://github.com/jingugugu/park/assets/116573862/89ad32ea-43cf-443d-9da8-4d0f47b530e3)
  ![티켓 구매2](https://github.com/jingugugu/park/assets/116573862/a528b30c-996f-4ce2-967e-e41e60da4f8f)  
  ![티켓구매3](https://github.com/jingugugu/park/assets/116573862/76dcbbed-bc9f-432d-9714-4987098074bd)
</details>

<details>
  <summary>고객문의</summary>
  상단메뉴에서 고객문의 카테고리를 클릭하여 문의 페이지로 이동합니다.
 
  ![고객문의](https://github.com/jingugugu/park/assets/116573862/f9cc0226-ed2b-4c43-aa58-d95ba610ef64)
  ![고객문의2](https://github.com/jingugugu/park/assets/116573862/1637c4ed-abef-4740-b888-8a8b2cd52a10)

  등록 후 마이페이지의 내가쓴문의 에서 답변 진행 상태 여부 확인이 가능합니다.
    
  ![고객문의3](https://github.com/jingugugu/park/assets/116573862/56759f6f-f85f-4902-a54b-519085469bbb)

  답변 여부를 확인하여 수정버튼 유무를 확인합니다. 답변이 달리게 되면 수정이 불가능합니다.
    
  ![고객문의4](https://github.com/jingugugu/park/assets/116573862/644061f7-c0d6-4881-9c8b-127a46a89baf)
  ![고객문의5](https://github.com/jingugugu/park/assets/116573862/c821cc5a-560d-4d80-9e10-7ada1981a27a)
</details>

<details>
  <summary>내부시설 상세정보/리뷰</summary>
  상단 네비의 즐길거리 카테고리를 클릭하면 내부시설 지도가 나타납니다. 지도에서 각 마커를 클릭하여 상세정보를 확인할 수 있습니다.
 
![지도](https://github.com/jingugugu/park/assets/116573862/5d4e4bf5-f331-4811-8c65-ce117bddc815)
![지도 상세정보](https://github.com/jingugugu/park/assets/116573862/1f04e59a-9e4f-4426-a53d-b29e20103d4c)
![상세정보](https://github.com/jingugugu/park/assets/116573862/8d292db7-2730-4908-8adf-bff1505708b0)

시설 상세정보 창 하단에 리뷰내용을 확인 할 수 있습니다. 로그인 상태일 때 시설상세정보 하단부분에 리뷰 작성칸 이용가능 합니다.
![리뷰](https://github.com/jingugugu/park/assets/116573862/967343c2-4629-4192-ba57-ba25e5f53d86)

티켓의 리뷰 작성기간이 만료되었거나 티켓구매자가 아니라면 리뷰를 남길 수 없습니다.
![리뷰만료](https://github.com/jingugugu/park/assets/116573862/34a19aa7-3a1f-4d57-9904-2b21ebdd4b8d)

리뷰는 본인의 리뷰만 삭제가 가능합니다.
![리뷰 삭제](https://github.com/jingugugu/park/assets/116573862/52b69d93-27c4-4fd8-b2e0-5fa5f073bcba)

</details>

<details>
  <summary>관리자</summary>
 
  관리자는 관리자의 권한을 받은 아이디로 로그인을 하였을때 상단네비에서 '관리' 메뉴가 새로 나타납니다.
 
  ![스크린샷 2024-01-25 오후 6 34 03](https://github.com/jingugugu/park/assets/116573862/9a98d5a3-1c59-4f4e-bc6e-974bbb2aa74c)
  <h3>시설 추가/수정</h3>
  
  관리자는 지도내 시설마커를 추가/수정 이 가능합니다. 지도에서 원하는 위치에 클릭을하고 시설타입을 선택, 정보를 입력한뒤 시설을 등록합니다.
  시설을 추가할때 이미지도 추가하는데 이 이미지들은 시설타입에 따라 폴더를 분류하여 저장되며 시설 삭제시 해당 시설의 이미지 폴더도 함께 제거됩니다.
  
  ![스크린샷 2024-01-25 오후 6 45 47](https://github.com/jingugugu/park/assets/116573862/e5b94928-49fe-4419-b250-4f420172d460)
  ![시설등록파일](https://github.com/jingugugu/park/assets/116573862/74834e35-de4c-42cd-a194-2d83a9a49732)

  시설 수정은 관리자 메뉴에서 수정메뉴로 들어가 수정을 원하는 시설을 클릭하여 수정이 가능합니다.

  ![스크린샷 2024-01-25 오후 6 46 50](https://github.com/jingugugu/park/assets/116573862/13898c17-ee36-489d-9262-25b074fbd904)
  <h3>티켓관리</h3>
  
  관리자는 원하는 티켓을 추가할 수 있습니다.
  
  ![스크린샷 2024-01-25 오후 6 50 47](https://github.com/jingugugu/park/assets/116573862/4581cabb-25ef-4324-99bd-b34f65ac95d9)
  ![스크린샷 2024-01-25 오후 6 51 00](https://github.com/jingugugu/park/assets/116573862/22171420-6d6b-4f18-9c29-6bae552ce45d)
  <h3>문의관리</h3>
  
  관리자는 회원들의 문의를 답변하며 관리를 할 수 있습니다.

  ![스크린샷 2024-01-25 오후 6 51 11](https://github.com/jingugugu/park/assets/116573862/cabad1f4-6530-4c56-b60b-79be60425346)
  ![스크린샷 2024-01-25 오후 6 53 55](https://github.com/jingugugu/park/assets/116573862/4b94be15-c3ad-4a41-b7dc-21d990918b04)
  
 <h3>회원관리</h3>
 
  관리자는 멤버관리 메뉴에서 회원탈퇴신청한 회원을 확인하고 해당회원이 작성한 글을 확인하여 탈퇴처리를 합니다.
  
  ![스크린샷 2024-01-25 오후 7 27 46](https://github.com/jingugugu/park/assets/116573862/761eef9d-9f73-4e49-8d49-c4926beaa33e)
  ![스크린샷 2024-01-25 오후 7 28 15](https://github.com/jingugugu/park/assets/116573862/15c18cb8-afc3-4878-8f07-a19f54a69957)
  ![스크린샷 2024-01-25 오후 7 28 26](https://github.com/jingugugu/park/assets/116573862/cdd6cf65-e820-41d9-913f-a1c0ce27ded6)
  ![스크린샷 2024-01-25 오후 7 28 32](https://github.com/jingugugu/park/assets/116573862/8ef41d2a-f934-449e-98e5-d24e8a7c7d65)
  ![스크린샷 2024-01-25 오후 7 28 39](https://github.com/jingugugu/park/assets/116573862/18992627-6064-4fd6-a202-2cf0adb4a94b)
  ![스크린샷 2024-01-25 오후 7 28 48](https://github.com/jingugugu/park/assets/116573862/34f920f2-c00d-42f4-938d-aee8bc9ec67e)
</details>

<h2>6.전체적인 기능구현 영상</h2>

https://youtu.be/E_9O2JzCBUM

[![Video Label](http://img.youtube.com/vi/E_9O2JzCBUM/0.jpg)](https://youtu.be/E_9O2JzCBUM?t=0s)

<h2>7. 배포용 주소</h2>
http://ec2-3-38-11-205.ap-northeast-2.compute.amazonaws.com
