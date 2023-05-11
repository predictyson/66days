interface MessageType {
  roomId: string;
  type: "ENTER" | "CHAT" | "LEAVE"; // 메시지 타입
  nickname: string; // 유저 닉네임
  image: string; // 유저 프로필 이미지 경로
  value: string; // 메시지 내용
  date: string; // 보낸 시간
}
