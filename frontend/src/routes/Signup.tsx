import { Button, Form, Input, InputRef, Typography } from "antd";
// import instance from "../api/api";
import { useRef } from "react";
import { useNavigate } from "react-router-dom";
import { useAuthStore } from "../stores/useAuthStore";
import { signup } from "../api/auth";

export default function Signup() {
  const setToken = useAuthStore((state) => state.setToken);
  const navigate = useNavigate();
  const inputRef = useRef<InputRef | null>(null);

  function onFinish() {
    const val = inputRef.current?.input?.value;
    if (val) {
      const queryParams = new URLSearchParams(location.search);

      const token = queryParams.get("token");
      setToken(token);

      // FIXME: check if nickname is duplicate
      signup(
        val,
        queryParams.get("email"),
        queryParams.get("profileImagePath")
      );
      navigate("/", { replace: true });
    }
    console.log("Success!");
  }

  function onFinishFailed(errorInfo: any) {
    console.log("Failed:", errorInfo);
  }

  return (
    <>
      <Typography.Title style={{ textAlign: "center" }}>
        회원가입
      </Typography.Title>
      <Form
        name="basic"
        labelCol={{ span: 8 }}
        wrapperCol={{ span: 16 }}
        style={{ maxWidth: 600, marginTop: "5rem" }}
        initialValues={{ remember: true }}
        onFinish={onFinish}
        onFinishFailed={onFinishFailed}
        autoComplete="off"
      >
        <Form.Item
          label="Nickname"
          name="nickname"
          rules={[{ required: true, message: "닉네임을 입력해주세요!" }]}
        >
          <Input ref={inputRef} maxLength={12} />
          {/* <input ref={inputRef} /> */}
        </Form.Item>

        <Form.Item wrapperCol={{ offset: 8, span: 16 }}>
          <Button type="primary" htmlType="submit">
            Submit
          </Button>
        </Form.Item>
      </Form>
    </>
  );
}
