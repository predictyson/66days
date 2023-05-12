import { useEffect, useState } from "react";
// import Stomp from "stompjs";
// import SockJS from "sockjs-client";
import { mockMe } from "../mock/challenge";

// FIXME: URL
// const URL = "http://70.12.247.243:8080";
const WS_URL = "ws://70.12.247.243:8080";
// const WS_URL = "ws://localhost:3000";
export default function useChat(roomId: string) {
  const user = mockMe;
  const [messages, setMessages] = useState<MessageType[]>([]);
  const [inputValue, setInputValue] = useState("");
  const [client, setClient] = useState<WebSocket | null>(null);

  useEffect(() => {
    // where to?
    console.log("where: ", `${WS_URL}/challenges/${roomId}`);

    // Create a WebSocket connection
    const ws = new WebSocket(`${WS_URL}/challenges/${roomId}`);
    setClient(ws);

    // Handle incoming messages
    ws.onmessage = (event) => {
      const message = JSON.parse(event.data);
      console.log("message: ", message);
      setMessages((prevMessages) => [...prevMessages, message]);
    };

    // Clean up the WebSocket connection when the component unmounts
    return () => {
      ws.close();
    };
  }, [roomId]);

  const handleSubmit = () => {
    if (!client) return;

    // Send a message to the WebSocket server
    const messagePacket: MessageType = {
      roomId,
      type: "CHAT",
      date: new Date(),
      nickname: user.nickname,
      image: user.image,
      value: inputValue,
    };

    client.send(JSON.stringify(messagePacket));

    // Clear the input value
    setInputValue("");
  };

  return {
    messages,
    handleSubmit,
    inputValue,
    setInputValue,
    // sendMessage,
  };
}
