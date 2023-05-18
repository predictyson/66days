import { LoadingOutlined } from "@ant-design/icons";
import { Spin } from "antd";
import { useEffect, useState } from "react";
import { useAuthStore } from "../stores/useAuthStore";
import { useNavigate } from "react-router-dom";
import instance from "../api/api";

export default function OAuth2Redirect() {
  const setToken = useAuthStore((state) => state.setToken);
  const navigate = useNavigate();

  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    if (isLoading) {
      setIsLoading(false);
    } else {
      const queryParams = new URLSearchParams(location.search);
      // console.log(queryParams.get("token"));
      setToken(`Bearer ${queryParams.get("token")}`);
      instance.defaults.headers.common.Authorization = `Bearer ${queryParams.get(
        "token"
      )}`;
      navigate("/", { replace: true });
    }
  }, [isLoading]);

  return <Spin indicator={<LoadingOutlined style={{ fontSize: 24 }} spin />} />;
}
