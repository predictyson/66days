import { Header } from "antd/es/layout/layout";
import styled from "styled-components";
import LogoTitle from "../../assets/logo-title.svg";
import Logo from "../../assets/logo.svg";
import avartar from "../../assets/avatar.svg";
import { Dropdown, MenuProps, Space } from "antd";
import { useState } from "react";
import { CreateGroupModal } from "../group/CreateGroupModal";
import { LoginOutlined, LogoutOutlined } from "@ant-design/icons";
import { useAuthStore } from "../../stores/useAuthStore";
import { expireUserToken } from "../../api/auth";

export default function CustomHeader() {
  const isLoggedIn = useAuthStore((state) => state.isLoggedIn);
  const [isOpenCreateGroupModal, setOpenCreateGroupModal] = useState(false);

  const items: MenuProps["items"] = [
    {
      key: "1",
      label: <a href="/mypage">my page</a>,
    },
    {
      key: "2",
      danger: true,
      label: (
        <a
          onClick={() => {
            expireUserToken();
            const resetUser = useAuthStore((state) => state.resetUser);
            resetUser();
          }}
        >
          <LogoutOutlined /> logout
        </a>
      ),
    },
  ];

  return (
    <>
      <StyledHeader>
        <Space className="logo">
          <a href="/">
            <img src={Logo} />
          </a>
          <a href="/">
            <img src={LogoTitle} />
          </a>
        </Space>
        <Space className="link">
          <a href="/groups">search</a>
          <a onClick={() => setOpenCreateGroupModal((prev) => !prev)}>
            create group
          </a>
          {isLoggedIn() ? (
            <Dropdown menu={{ items }} placement="bottomRight" arrow>
              <img src={avartar} />
            </Dropdown>
          ) : (
            <a
              // FIXME: href={`${ import.meta.env.VITE_SERVER_DOMAIN }/oauth2/authorization/kakao`}
              href="http://localhost:8080/oauth2/authorization/kakao"
            >
              <LoginOutlined /> login
            </a>
          )}
        </Space>
      </StyledHeader>
      <CreateGroupModal
        open={isOpenCreateGroupModal}
        toggleModal={() => setOpenCreateGroupModal((prev) => !prev)}
      />
    </>
  );
}

const StyledHeader = styled(Header)`
  position: fixed;
  width: 100%;
  z-index: 1;
  top: 0;
  border-bottom: 1px solid rgba(158, 158, 158, 0.2);
  text-align: center;
  height: 8rem;
  padding-inline: 5rem;
  line-height: 64px;
  background-color: #fff;
  display: flex;
  align-items: center;
  justify-content: space-between;
  /* font-size: 1.6rem; */
  .logo {
    display: flex;

    img {
      display: block;
    }
  }

  .link {
    display: flex;
    align-items: center;

    a {
      margin-right: 2rem;
      font-size: 1.6rem;
      color: black;
    }

    img {
      display: block;
    }
  }
`;
