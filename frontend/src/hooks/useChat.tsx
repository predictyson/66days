import { useEffect, useState } from "react";
import Stomp from "stompjs";
import SockJS from "sockjs-client";

// FIXME: URL, roomId, and userId
const URL = "http://70.12.247.243:8080";
export default function useChat(
  roomId = 123,
  userId = "tw",
  onMessage = (content: string) => {
    console.log(content);
  }
) {
  const [client, setClient] = useState<Stomp.Client | null>(null);

  useEffect(() => {
    const client = Stomp.over(new SockJS(URL + "/speedoodle/room"));
    setClient(client);

    /**
     * 웹소켓에 접속하면, 서버에게 접속했다고 알린다음에 특정 방에 subscribe한다
     */
    client.connect({}, function () {
      console.log("hello, world");
      client.send(
        "/pub/send",
        {},
        JSON.stringify({
          chatRoomId: roomId,
          type: "JOIN",
          writer: userId,
        })
      );
      client.subscribe(`/sub/${roomId}`, function (chat) {
        // TODO: get messages from others
        const content = JSON.parse(chat.body);
        onMessage(content);
        console.log("content: " + content);
      });
    });

    return () => client.ws.close();
    // client?.disconnect(() => console.log("destroyed"));
  }, []);

  const sendMessage = () => {
    console.log("send message :)");
    client?.send(
      "/pub/send",
      {},
      JSON.stringify({
        chatRoomId: "123",
        type: "CHAT",
        message: "Hello, world!",
        writer: "member-tw",
      })
    );
  };

  return {
    sendMessage,
  };
}
