import { LoadingOutlined } from "@ant-design/icons";
import { Spin } from "antd";
import { useEffect, useState } from "react";
// import { useAuthStore } from "../stores/useAuthStore";

export default function OAuth2Redirect() {
  // const setUser = useAuthStore((state) => state.setUser);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    if (isLoading) {
      setIsLoading(false);
    } else {
      // TODO: setUser();
      // token, email, profileImagePath
    }
  }, [isLoading]);

  return <Spin indicator={<LoadingOutlined style={{ fontSize: 24 }} spin />} />;
}
