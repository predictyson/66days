import { Header } from "antd/es/layout/layout";
import styled from "styled-components";
import LogoTitle from "../../assets/logo-title.svg";
import Logo from "../../assets/logo.svg";
import avartar from "../../assets/avatar.svg";
import { Dropdown, MenuProps, Space } from "antd";
import { useState } from "react";
import { CreateGroupModal } from "../group/CreateGroupModal";
import { LoginOutlined, LogoutOutlined } from "@ant-design/icons";
import { useAuthStore } from "../../hooks/useAuthStore";

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
        onClick={(e) => {
          e.preventDefault();
          const logout = useAuthStore((state) => state.logout);
          logout();
        }}
      >
        <LogoutOutlined /> logout
      </a>
    ),
  },
];

export default function CustomHeader() {
  const [isOpenCreateGroupModal, setOpenCreateGroupModal] = useState(false);

  const isLoggedIn = useAuthStore((state) => state.isLoggedIn);
  const loginByKakao = useAuthStore((state) => state.loginByKakao);

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
            <a onClick={() => loginByKakao()}>
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
