import { Button, Form, Input, Select, Typography, message } from "antd";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import instance from "../api/api";

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
  const [form] = Form.useForm();
  const nickName = Form.useWatch("nickName", form);
  const [messageApi, contextHolder] = message.useMessage();

  async function finishHandler(values: any) {
    try {
      const isUnique = await instance.get(
        `/main-service/user/check-nickname/${nickName}`
      );

      if (isUnique.status !== 200) {
        messageApi.open({
          type: "error",
          content: "This is an error message",
        });
        return;
      }

      // TODO: const res = await instance.post()
      // navigate("/", { replace: true });
    } catch (error) {
      console.log(values);
    }
  }

  function resetHandler() {
    form.resetFields();
  }

  useEffect(() => {
    const queryParams = new URLSearchParams(location.search);

    // TODO:
    // const token = queryParams.get("token");
    // token && form.setFieldsValue({ : "Hi, man!" });
    const email = queryParams.get("email");
    email && form.setFieldsValue({ email });
    const nickName = queryParams.get("nickName");
    nickName && form.setFieldsValue({ nickname: nickName });
    const social = queryParams.get("social");
    social && form.setFieldsValue({ social });
  }, []);

  // const handleSignup = async () => {
  //   // 소셜로그인 시 받은 닉네임이 유효하다면
  //   if (socialNicknameErr) {
  //     setInputs({
  //       ...inputs,
  //       nickname: socialNickname,
  //     });
  //   }

  //   await axios
  //     .post(
  //       `${BACKEND_URL}/user/social`, // 소셜 로그인 POST api 주소
  //       {
  //         email: email,
  //         nickname: nickname,
  //         gender: genderStr,
  //         birth: birth,
  //         address: address,
  //         regionCode: sigunguCode,
  //         social: social,
  //       },
  //       {
  //         headers: {
  //           "Content-Type": "application/json",
  //         },
  //       }
  //     )
  //     .then((res) => {
  //       navigate("/socialsuccess", { state: { isGranted: true } });
  //     })
  //     .catch((err) => {
  //       alert("소셜 회원가입을 처리하던 중 문제가 발생하였습니다.");
  //     });
  // };

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
