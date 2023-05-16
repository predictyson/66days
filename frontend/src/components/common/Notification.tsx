import { useEffect, useState } from "react";
import { Dropdown, Menu } from "antd";
import { BellFilled } from "@ant-design/icons";
interface Notification {
  id: number;
  msg: string;
}

export default function NotificationList() {
  const [notifications, setNotifications] = useState<Notification[]>([]);
  const [length, setLength] = useState<number>(0);
  useEffect(() => {
    const eventSource = new EventSource(
      "http://70.12.247.243:8080/notification/321"
    );
    eventSource.onmessage = (event) => {
      const newNotification = JSON.parse(event.data);
      setNotifications((prev) => [...prev, newNotification]);
      // Notification API를 이용해 알림을 보내주는 코드
      if (notifications.length !== length) {
        setLength(notifications.length);
        if (Notification.permission === "granted") {
          const notification = new Notification(newNotification.msg);
          setTimeout(notification.close.bind(notification), 5000);
        } else if (Notification.permission !== "denied") {
          Notification.requestPermission().then((permission) => {
            if (permission === "granted") {
              const notification = new Notification(newNotification.msg);
              setTimeout(notification.close.bind(notification), 5000);
            }
          });
        }
      }
    };
    return () => {
      eventSource.close();
    };
  }, []);

  const menu = (
    <Menu>
      {notifications.length === 0 && (
        <h2 style={{ padding: "3rem" }}>알림이 없습니다</h2>
      )}
      {notifications.map((noti, idx) => {
        return <Menu.Item key={idx}>{noti.msg}</Menu.Item>;
      })}
    </Menu>
  );
  return (
    <Dropdown overlay={menu}>
      <BellFilled
        style={{ fontSize: "2.25rem", color: "#B8A9FB", cursor: "pointer" }}
      />
    </Dropdown>
  );
}
