import { Button, Form, Input, InputRef, Typography } from "antd";
import instance from "../api/api";
import { useRef } from "react";

export default function Signup() {
  const inputRef = useRef<InputRef | null>(null);

  function onFinish() {
    const val = inputRef.current?.input?.value;
    console.log("hoid", val);
    if (val) {
      instance
        .get(`/signup/nickname/${val}`)
        .then((res) => console.log(res.data));
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
