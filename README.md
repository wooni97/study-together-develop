## Study Together

### 소개

💻 개발자들이 함께 모여 코딩할 수 있는 플랫폼 💻

누구나 자유롭게 스터디 그룹을 생성하거나, 스터디 그룹에 입장할 수 있습니다.

함께 모여 코드 에디터를 통해 같이 코딩하고, 채팅으로 대화할 수 있습니다.

---
### 사용 기술
- **JDK** : Java17

- **Framework** : SpringBoot
  
- **Database** : MySQL 8.0.24
  
- **CI/CD** : GitHub Actions
  
- **배포** : Naver Cloud Platform

----
### 예상 UI
![study together ui-1](https://github.com/user-attachments/assets/20e21201-660a-4f27-8969-4fe769f659cf)
![study together ui (1)](https://github.com/user-attachments/assets/307e5c05-ee1c-4598-830b-1728b484f22b)
![study together ui (2)](https://github.com/user-attachments/assets/466b91fe-c87c-448c-8397-06ecc53b8046)
![study together ui (3)](https://github.com/user-attachments/assets/1d0ea8ef-7c86-4432-bfef-857fb13f5a00)

---

### 🚀 프로젝트 Develop 과정

**Develop 이유 :** 
- 해당 비즈니스를 실제로 서비스한다면 어떤 고민이 필요할지, 그리고 비즈니스를 성공적으로 이끌기 위해서는 무엇을 개선해야 할지에 대한 답을 찾고자 고도화 과정을 진행하게 되었습니다.

**Develop 원칙 : 점진적 레거시 개선**
- 실제 운영중인 시스템임을 가정하고, 점진적으로 레거시 개선 진행
- 사용자와 밀접하게 연관된 기능(회원가입, 로그인 등) 변경 필요 -> 기존에 사용하는 Member 모델에 기능을 추가하는 것이 아닌 새로운 MemberV2 도메인 모델 생성
  
[이전 Repository 바로가기](https://github.com/f-lab-edu/study-together)




---

### 🗂️ 기술적 고민

**✔️JPA 도입 과정**

[JpaRepository.save() 안티패턴에 대한 고찰](https://dev-wooni.tistory.com/9)

[다양한 구조로 변경 가능한 Repository 만들기](https://dev-wooni.tistory.com/11)

[일대다(OneToMany) 단방향 매핑이 불러오는 성능 비효율에 관한 고민](https://dev-wooni.tistory.com/12)


**✔️Event-Driven Architecture**

[Event 기반 아키텍처 사용 이유 및 Transactional Outbox 패턴을 통한 메시지 발행 보장](https://dev-wooni.tistory.com/13)

[Event 확장성 고민, 추상화로 해결하기 : 이벤트 동적 매핑 하는 방법](https://dev-wooni.tistory.com/14)


**✔️동시성 제어**

**계획 :** 
- **트래픽 예측** : 하나의 스터디 그룹에 3~50명 입장 가능할것으로 예정.
- **스터디 그룹 수** : 현재 규모로는 100개 이내의 스터디 그룹이 존재할 것으로 가정.
- **동시 요청 수** : 적게는 10개 미만, 많게는 수백 건 이내로 예상.
- **대규모 트래픽 여부** : 대규모 트래픽이 아니기에, 초기에 DB 락으로 동시성 제어가 충분히 가능할것으로 예측.
- **성능 테스트 후 확장** : DB 락으로 제어 후, 성능 테스트를 통해 병목 지점을 확인하고, 필요에 따라 Redis와 같은 외부 인프라를 도입하여 성능을 비교할 계획.

**진행 상황 :**
- [Pessimistic Lock 사용 및 성능 테스트 결과](https://github.com/wooni97/StudyTogether/pull/18)
