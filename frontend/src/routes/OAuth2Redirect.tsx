import { LoadingOutlined } from "@ant-design/icons";
import { Spin } from "antd";
import { useEffect, useState } from "react";
import { useAuthStore } from "../hooks/useAuthStore";

export default function OAuth2Redirect() {
  const authenticate = useAuthStore((state) => state.authenticateByKakao);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    if (isLoading) {
      setIsLoading(false);
    } else {
      authenticate();
    }
  }, [isLoading]);

  return <Spin indicator={<LoadingOutlined style={{ fontSize: 24 }} spin />} />;
}
