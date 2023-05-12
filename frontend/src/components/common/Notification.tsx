import { useEffect, useState } from "react";

interface Notification {
  id: number;
  msg: string;
}

export default function NotificationList() {
  const [notifications, setNotifications] = useState<Notification[]>([]);

  useEffect(() => {
    const eventSource = new EventSource(
      "http://70.12.247.243:8888/app/chats/chatrooms/321"
    );
    console.log(eventSource);
    eventSource.onmessage = (event) => {
      const newNotification = JSON.parse(event.data);
      console.log(newNotification);
      setNotifications((prev) => [...prev, newNotification]);
    };

    return () => {
      eventSource.close();
    };
  }, []);

  return (
    <>
      {notifications.map((notification, idx) => (
        <h1 key={idx}>{notification.msg}</h1>
      ))}
    </>
  );
}
