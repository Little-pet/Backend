# UMC_LittlePet Backend

UMC_LittlePet Backend는 반려 소동물 관리 모바일 웹 애플리케이션의 서버 로직을 담당합니다.  
이 프로젝트는 사용자가 반려 소동물의 건강, 관리 및 커뮤니티 활동을 손쉽게 진행할 수 있도록 다양한 API를 제공합니다.

---

## 📖 프로젝트 소개

### 서비스 기능
- **소동물 맞춤형 정보 제공**  
  - **관리 방법 가이드:** 반려인이 소동물을 효율적으로 관리할 수 있도록 카테고리별 특징, 먹이, 사육 환경, 놀이 방법 안내  
  - **필수템 추천:** 관리에 필요한 용품(사료, 장난감, 케이지 등) 정보 제공 및 판매처 링크 연동
- **소동물 건강 관리**  
  - **건강 관리 방법 안내:** 증상 별 대처법 제공 (예: 햄스터의 체중 감소, 털 빠짐 등)  
  - **건강 기록:** 체중, 식사량, 의료 데이터 기록 및 분석  
  - **병원 검색 및 리뷰:** 지도 기반 소동물 전문 병원 검색 및 사용자 리뷰 공유
- **소동물 커뮤니티 기능**  
  - **Q&A:** 사용자 간 정보 교류  
  - **일상 기록:** 소동물 일상 콘텐츠 게시  
  - **반려동물 소개:** 사용자가 자신의 반려 소동물을 소개하고 공유

---

## 🛠️ 기술 스택 및 인프라

### Backend Framework & 언어
- **Spring Boot:** RESTful API 개발
- **Java:** 주요 개발 언어
- **Spring Security:** 사용자 인증 및 권한 관리

### 데이터베이스
- **MySQL:** 관계형 데이터베이스
- **JPA/Hibernate:** ORM 프레임워크

### 컨테이너 및 오케스트레이션
- **Docker:** 컨테이너화를 통한 환경 일관성 확보
- **Docker Compose:** 다중 컨테이너 구성 및 관리

### 클라우드 및 배포
- **AWS EC2:** 서버 호스팅
- **AWS S3:** 파일 스토리지 (이미지, 문서 등)
- **Let's Encrypt & HTTPS 인증:** SSL/TLS 인증서 관리
- **Nginx:** 리버스 프록시 서버 (SSL termination, 요청 분배)
- **도메인 관리:** 구매한 도메인을 통한 서비스 운영

### 빌드 및 배포 관리
- **Gradle:** 빌드 도구
- **CI/CD 파이프라인:** (GitHub Actions 등으로 배포 자동화 가능)

### 문서화 및 테스트
- **Swagger:** API 문서화 및 테스트 인터페이스 제공

---

## 👬 팀원 소개

**Backend_SpringBoot (5명)**
- **준재 / 서재준** (팀장)
- **감자 / 김지웅**
- **건 / 강건희**
- **누드리 / 황효은**
- **유키 / 심은지**

---

## 🙆‍♀️ 협업 규칙

- **브랜치 전략**
  - Issue 기반 feature 브랜치 생성 (브랜치 source: `develop`)
  - 작업 전 항상 최신 `main` 브랜치 반영 후 진행
- **커밋 메시지 규칙**
  - `[태그] 설명` (예: `[feat] 로그인 기능 구현`)
  - 주요 태그: `feat`, `fix`, `docs`, `refactor`, `style`, `test`
- **PR 및 코드 리뷰**
  - 기능 구현 후 Pull Request 생성 및 팀원 간 리뷰 진행
  - 자주 commit, merge, pull 하여 코드 동기화
