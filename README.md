# UI 디자인 견본
https://ovenapp.io/view/GFI0N9IPvtnugBAqbDu0xq5jlXMxIX9r/2yZrX

<br/><br/>

# 데이터 베이스 다이어그램

## **Field Description**

- Rooms Table

방 번호: 방 고유 번호<br/>
방 제목: 방 텍스트 제목<br/>
방 참여자/방장: 방 참여자 정보, 방장 여부(외부 테이블)<br/>
방 분류: 택시, 공동 구매, 넷플릭스 등등, int 타입 번호로 관리<br/>
채팅 내용: 말 그대로 채팅 내용 (외부 테이블)<br/>
모집인원: 남은 모집 인원<br/>
현재인원: 현재 모집된 인원

- Taxi Recurit Exclusive (Rooms table 외부 테이블)

출발지: 출발지가 어딘지 Text 형태로 저장<br/>
(point로 저장하여 위도 경도 데이터를 구글 지도 API와 연동하는 것 고려)<br/>
목적지: 동일 <br/>
예상 비용: 예상 택시비<br/>
~~해시태그 (보류, 외부 테이블)~~



## **구현**

1. 모집 채팅방 데이터 테이블

```sql
CREATE TABLE Room (
	RoomID INT AUTO_INCREMENT PRIMARY KEY,
	RoomTitle VARCHAR(255),
	RoomCategory INT, -- 방 분류 (택시, 공동구매 등)
	RecruitNum INT, -- 모집 인원
	CurrentNum INT, -- 현재 인원
);
```

1. 채팅방 메세지 테이블

```sql
CREATE TABLE ChatMessage (
	MessageID INT AUTO_INCREMENT PRIMARY KEY,
	RoomID INT, -- 룸 id (외래키)
	SenderID INT, -- 전송자 id
	MessageText TEXT, -- 텍스트 메세지 내용
	Timestamp DATETIME, -- 전송시간, 전송 시간 기준으로 sort하여 채팅창 구성
	FOREIGN KEY (RoomID) REFERENCES Rooms(RoomID)
    FOREIGN KEY (SenderID) REFERENCES Member(MemberID)
);
```


1. 채팅방 멤버 테이블

```sql
CREATE TABLE RoomMember (
  RoomMemberID INT AUTO_INCREMENT PRIMARY KEY,
  RoomID INT,--외래키
  UserID BIGINT,
  JoinDate DATETIME,
  IsRoomOwner BOOLEAN NOT NULL DEFAULT FALSE,--방장여부
  FOREIGN KEY (RoomID) REFERENCES Rooms(RoomID),
  FOREIGN KEY (UserID) REFERENCES member(MEMBER_ID)
);
```

멤버 테이블

```sql
CREATE TABLE Member (
    MEMBER_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    loginId VARCHAR(30) NOT NULL,
    password VARCHAR(30) NOT NULL,
    name VARCHAR(20) NOT NULL,
    CONSTRAINT loginId_unique UNIQUE (loginId)
);

```



1. 택시 채팅방 전용 기능

```sql
CREATE TABLE Room (
	RoomID INT AUTO_INCREMENT PRIMARY KEY,
	RoomTitle VARCHAR(255),
	RoomCategory INT, -- 방 분류 (택시, 공동구매 등)
	RecruitNum INT, -- 모집 인원
	CurrentNum INT, -- 현재 인원
);
```
