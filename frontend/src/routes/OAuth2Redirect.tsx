import { LoadingOutlined } from "@ant-design/icons";
import { Spin } from "antd";
import { useEffect, useState } from "react";
import { useAuthStore } from "../stores/useAuthStore";
import { useNavigate } from "react-router-dom";

export default function OAuth2Redirect() {
  const getToken = useAuthStore((state) => state.getToken);
  const setToken = useAuthStore((state) => state.setToken);
  const navigate = useNavigate();

  const token = getToken();
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    if (isLoading) {
      setIsLoading(false);
    } else {
      const queryParams = new URLSearchParams(location.search);
      setToken(queryParams.get("token"));
    }
  }, [isLoading]);

  useEffect(() => {
    token && navigate("/", { replace: true });
  }, [token]);

  return <Spin indicator={<LoadingOutlined style={{ fontSize: 24 }} spin />} />;
}
