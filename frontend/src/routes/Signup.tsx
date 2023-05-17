import { Button, Form, Input, InputRef, Typography } from "antd";
// import instance from "../api/api";
import { useRef } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export default function Signup() {
  const navigate = useNavigate();
  const inputRef = useRef<InputRef | null>(null);

  function onFinish() {
    const val = inputRef.current?.input?.value;
    console.log("hoid", val);
    if (val) {
      // instance
      //   .get(`/signup/nickname/${val}`)
      //   .then((res) => console.log(res.data));
      const queryParams = new URLSearchParams(location.search);
      console.log(
        "values: ",
        queryParams.get("token"),
        queryParams.get("email"),
        queryParams.get("profileImagePath")
      );

      const token = queryParams.get("token");
      token && localStorage.setItem("token", token);

      axios
        .post(
          "http://localhost:8081/user/signup/nickname",
          {
            nickname: val,
            email: queryParams.get("email"),
            profileImagePath: queryParams.get("profileImagePath"),
          },
          {
            headers: {
              Authorization: queryParams.get("token"),
            },
          }
        )
        .then((res) => {
          console.log(res.data);
          navigate("/", { replace: true });
        })
        .catch((err) => console.log("error", err));
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
      <Button
        type="primary"
        onClick={() => {
          axios
            .get("http://localhost:8081/user/hello")
            .then((res) => console.log(res.data))
            .catch((err) => console.log(err));
        }}
      >
        check hello
      </Button>
    </>
  );
}
