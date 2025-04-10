# D-Vote

이더리움 기반 탈중앙 투표 시스템(D-Vote)은 투표 데이터의 투명성과 조작 불가능성을 보장하는 블록체인 프로젝트입니다.  
스마트 컨트랙트, React 프론트엔드, Spring Boot 백엔드, 그리고 Geth 노드를 활용해 완전한 분산형 투표 애플리케이션을 구현합니다.

## 주요 기술 스택

| 레이어 | 기술 |
|--------|------|
| 스마트 계약 | Solidity, Hardhat |
| 프론트엔드 | React, Vite, Ethers.js |
| 백엔드 | Spring Boot (Maven) |
| 블록체인 노드 | Geth (Ethereum) |
| 인프라 | Docker, GitHub Actions (CI/CD) |
| 기타 | Metamask, EC2, S3 |
| 보안 | Spring Security, JWT, PasswordEncoder (BCrypt) |
| 인증 처리 | @AuthenticationPrincipal + CustomUserDetails |
| 예외 처리 | @RestControllerAdvice + GlobalExceptionHandler |
| 스케줄링 | @Scheduled 기반 투표 마감 처리 |
| 데이터 | Spring Data JPA, MySQL |

## 주요 기능

### 회원 관리
    - 회원가입 / 로그인 (JWT 발급)
    - 관리자 / 일반 사용자 권한 구분
    - 로그인 유저 정보 추출 및 인증
    
### 투표 기능
    - 투표 생성 (관리자 전용)
    - 후보 등록 (관리자 전용)
    - 투표 마감 스케줄링 (Scheduler 기반)
    - 투표 참여 (중복 투표 방지)
    - 투표 결과 확인 (득표율 + 당선자)
    
### 조회 기능
    - 전체 투표 목록
    - 특정 투표의 후보 목록
    - 유저별 투표 참여 기록
    - 특정 투표 참여 여부 확인

### 관리자 전용 통계
    - 전체 유저 수
    - 전체 투표 수
    - 투표별 참여 수 및 투표율
    
## 프로젝트 구조
    d-vote/
    ├── contracts/             # 스마트 계약 개발
    │   ├── contracts/
    │   │   └── Vote.sol       # 기본 투표 스마트 계약
    │   ├── scripts/
    │   │   └── deploy.js      # 배포 스크립트
    │   ├── test/
    │   │   └── vote.test.js   # 테스트 코드
    │   ├── hardhat.config.js  # 하드햇 설정 파일
    │   └── package.json       # npm 설정 파일
    │
    ├── backend/               # 백엔드 서버 (Spring Boot - Maven)
    │   ├── src/
    │   │   └── main/java/...  # Java 코드
    │   └── pom.xml            # Maven 설정 파일
    │
    ├── frontend/              # 프론트엔드 (React + Vite)
    │   ├── public/
    │   ├── src/
    │   │   ├── components/    # 공통 컴포넌트
    │   │   ├── pages/         # 페이지 구성
    │   │   ├── App.jsx
    │   │   └── main.jsx
    │   ├── index.html
    │   ├── package.json
    │   └── vite.config.js
    │
    ├── infra/                 # Geth 로컬 노드, docker-compose 등
    │   ├── docker-compose.yml
    │   ├── geth/
    │   │   └── init.sh
    │   └── README.md
    │
    ├── docs/                  # 기술 문서, API 명세, 기획서
    │   └── architecture.md
    │
    ├── .gitignore
    ├── README.md
    └── LICENSE

## 실행 방법

### 1. 스마트 계약
    cd contracts
    npm install
    npx hardhat compile
    npx hardhat test

### 2. 프론트엔드
    cd frontend
    npm install
    npm run dev

### 3. 백엔드
    cd backend
    ./mvnw spring-boot:run

### 4. 로컬 Geth 노드 실행
    cd infra/geth
    ./init.sh

## 배포 환경
    EC2 (Ubuntu 22.04)
    Geth 노드: 로컬 테스트넷 or Goerli
    프론트:S3 + CloudFront
    백엔드:EC2 + Nginx + Spring Boot

## 기여 방법
    이 프로젝트는 학습 및 포트폴리오 목적이며, PR 및 피드백 환영합니다!
