import { LoadingOutlined } from "@ant-design/icons";
import { Spin } from "antd";
import { useEffect, useState } from "react";
import { useAuthStore } from "../stores/useAuthStore";
import { fetchUserByKakao } from "../api/auth";

export default function OAuth2Redirect() {
  const setUser = useAuthStore((state) => state.setUser);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    if (isLoading) {
      setIsLoading(false);
    } else {
      fetchUserByKakao().then((user) => setUser(user));
    }
  }, [isLoading]);

  return <Spin indicator={<LoadingOutlined style={{ fontSize: 24 }} spin />} />;
}
