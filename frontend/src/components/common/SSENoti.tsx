import { useEffect, useState } from "react";
interface Notification {
  id: number;
  msg: string;
}
export default function () {
  const [lastNotification, setLastNotification] = useState<Notification | null>(
    null
  );
  const [seenNotifications, setSeenNotifications] = useState<Notification[]>(
    []
  );

  useEffect(() => {
    const eventSource = new EventSource(
      "http://70.12.247.243:8085/notification/321"
    );
    const handleNotification = (event: any) => {
      const newNotification = JSON.parse(event.data);
      // 중복 알림 체크
      if (
        !seenNotifications.some(
          (notification) => notification.id === newNotification.id
        )
      ) {
        setLastNotification(newNotification);
        setSeenNotifications((prevNotifications) => [
          ...prevNotifications,
          newNotification,
        ]);
      }
      console.log(lastNotification);
      console.log(seenNotifications);
    };

    eventSource.addEventListener("message", handleNotification);

    return () => {
      eventSource.removeEventListener("message", handleNotification);
      eventSource.close();
    };
  }, [seenNotifications]);

  useEffect(() => {
    if (lastNotification) {
      if (Notification.permission === "granted") {
        const notification = new Notification(lastNotification.msg);
        setTimeout(notification.close.bind(notification), 5000);
      } else if (Notification.permission !== "denied") {
        Notification.requestPermission().then((permission) => {
          if (permission === "granted") {
            const notification = new Notification(lastNotification.msg);
            setTimeout(notification.close.bind(notification), 5000);
          }
        });
      }
      console.log(lastNotification);
    }
  }, [lastNotification]);
  return null;
}