import { Button, Form, Input, Select, Typography, message } from "antd";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import instance from "../api/api";
import { useAuthStore } from "../stores/useAuthStore";

const { Option } = Select;

const layout = {
  labelCol: { span: 6 },
  wrapperCol: { span: 18 },
};

const tailLayout = {
  wrapperCol: { offset: 6, span: 18 },
};

export default function SocialSignup() {
  const navigate = useNavigate();
  const setToken = useAuthStore((state) => state.setToken);
  const getToken = useAuthStore((state) => state.getToken);
  const [form] = Form.useForm();
  const nickName = Form.useWatch("nickName", form);
  const [messageApi, contextHolder] = message.useMessage();

  async function finishHandler(values: any) {
    try {
      const isUnique = await instance.get(
        `/main-service/user/check-nickname/${nickName}`,
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: getToken(),
          },
        }
      );

      if (isUnique.status !== 200) {
        messageApi.open({
          type: "error",
          content: "This is an error message",
        });
        return;
      }

      const values = form.getFieldsValue();
      // console.log("values: ", form.getFieldsValue());
      const res = await instance.post(
        `/user/social`,
        {
          email: values.email,
          nickname: values.nickName,
          social: values.social,
        },
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: getToken(),
          },
        }
      );
      if (res.status === 200) {
        messageApi.open({
          type: "success",
          content: "Success",
        });
        navigate("/", { replace: true });
      }
    } catch (error) {
      console.error(values);
      messageApi.open({
        type: "info",
        content: `회원가입에 실패하였습니다`,
      });
      navigate("/", { replace: true });
    }
  }

  function resetHandler() {
    form.resetFields();
  }

  useEffect(() => {
    const queryParams = new URLSearchParams(location.search);

    const token = queryParams.get("token");
    token && setToken(token);
    const email = queryParams.get("email");
    email && form.setFieldsValue({ email });
    const nickName = queryParams.get("nickName");
    nickName && form.setFieldsValue({ nickname: nickName });
    const social = queryParams.get("social");
    social && form.setFieldsValue({ social });
  }, []);

  return (
    <>
      {contextHolder}
      <Form
        {...layout}
        form={form}
        name="control-hooks"
        onFinish={finishHandler}
        style={{ maxWidth: 600, margin: "auto" }}
        labelAlign="left"
      >
        <Typography.Title>66 days 회원가입</Typography.Title>
        <Form.Item
          name="email"
          label="Email"
          rules={[{ required: true }]}
          help="필수값입니다"
        >
          <Input />
        </Form.Item>
        <Form.Item
          name="nickName"
          label="NickName"
          rules={[{ required: true, len: 10 }]}
          help="글자수는 10자 이상이어야합니다."
        >
          <Input />
        </Form.Item>
        <Form.Item
          name="social"
          label="Social"
          rules={[{ required: true }]}
          help="필수값입니다"
        >
          <Select placeholder="선택해주세요." allowClear>
            <Option value="kakao">Kakao</Option>
          </Select>
        </Form.Item>
        <Form.Item {...tailLayout}>
          <Button
            type="primary"
            htmlType="submit"
            style={{ marginRight: "8px" }}
          >
            회원가입
          </Button>
          <Button htmlType="button" onClick={resetHandler}>
            초기화
          </Button>
        </Form.Item>
      </Form>
    </>
  );
}
