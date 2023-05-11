import { useEffect, useState } from "react";
import Stomp from "stompjs";
import SockJS from "sockjs-client";
import { mockMe } from "../mock/challenge";

// FIXME: URL
const URL = "http://70.12.247.243:8080";
export default function useChat(roomId: string) {
  const user = mockMe;
  const [messages, setMessages] = useState<MessageType[]>([]);
  const [client, setClient] = useState<Stomp.Client | null>(null);

  useEffect(() => {
    const client = Stomp.over(new SockJS(URL + "/stomp/chat"));
    setClient(client);

    /**
     * 웹소켓에 접속하면, 특정 방에 subscribe하고나서 방 사람들에게 접속했다고 알린한다
     */
    client.connect({}, function () {
      // client.subscribe(`/sub/chat/room/${roomId}`, function (chat) {
      client.subscribe(`/chat/enter`, function (chat) {
        const content = JSON.parse(chat.body);
        // setMessages((prev) => [...prev, content]); TODO: get messages from others
        console.log("content: ", content);
      });

      const messagePacket: MessageType = {
        roomId,
        type: "ENTER",
        date: new Date().toLocaleString(),
        nickname: user.nickname,
        image: user.image,
        value: `${user.email}(이)가 참여했습니다.`,
      };

      // client.send("/pub/chat/message", {}, JSON.stringify(messagePacket));
      client.send("/challenges/room", {}, JSON.stringify(messagePacket));
    });

    return () => client.ws.close();
  }, []);

  const sendMessage = (msg: string) => {
    const messagePacket: MessageType = {
      roomId,
      type: "CHAT",
      date: new Date().toLocaleString(),
      nickname: user.nickname,
      image: user.image,
      value: msg,
    };
    // client!.send(`/sub/chat/room/${roomId}`, {}, JSON.stringify(messagePacket));
    // client!.send("/pub/chat/message", {}, JSON.stringify(messagePacket));
    client?.send("/challenges/room", {}, JSON.stringify(messagePacket));
    setMessages((prev) => [...prev, messagePacket]);
  };

  return {
    messages,
    sendMessage,
  };
}
