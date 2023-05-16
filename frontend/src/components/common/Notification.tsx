import { useEffect, useState } from "react";
import { Dropdown, Menu } from "antd";
import { BellFilled } from "@ant-design/icons";
import axios from "axios";
interface Notification {
  id: number;
  msg: string;
}
export default function NotificationList() {
  const [notifications, setNotifications] = useState<Notification[]>([]);
  const [lastNotification, setLastNotification] = useState<Notification | null>(
    null
  );
  const [notiList, setNotiList] = useState<Notification[] | null>([]);
  const getNotiList = async () => {
    try {
      const res = await axios.get(
        "http://70.12.247.243:8085/notification/list/321"
      );
      const dataObjects = res.data.match(/data:(\{.*?\})/g);

      if (!dataObjects) {
        return [];
      }

      const dataArr = dataObjects.map((v: string) =>
        JSON.parse(v.substring(5))
      );
      setNotiList(dataArr);
    } catch (err) {
      console.log(err);
    }
  };
  useEffect(() => {
    getNotiList();
  }, []);

  useEffect(() => {
    const eventSource = new EventSource(
      "http://70.12.247.243:8085/notification/321"
    );

    eventSource.onmessage = (event) => {
      const newNotification = JSON.parse(event.data);
      setNotifications((prev) => [...prev, newNotification]);
      setLastNotification(newNotification);
    };

    return () => {
      eventSource.close();
    };
  }, []);

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
    }
  }, [lastNotification]);

  const menu = (
    <Menu>
      {notiList?.length === 0 && (
        <h2 style={{ padding: "3rem" }}>알림이 없습니다</h2>
      )}
      {notiList?.map((noti, idx) => {
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
