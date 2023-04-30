import { Layout } from "antd";
import styled from "styled-components";
import LogoTitle from "../../assets/logo-title.svg";
import Logo from "../../assets/logo.svg";
import avartar from "../../assets/avatar.svg";

const { Header, Footer, Content } = Layout;

interface PropsType {
  children: React.ReactNode;
}

export default function CustomLayout(props: PropsType) {
  return (
    <StyledLayout>
      <CustomHeader />
      <StyledContent>{props.children}</StyledContent>
      <StyledFooter>Footer</StyledFooter>
    </StyledLayout>
  );
}

function CustomHeader() {
  return (
    <StyledHeader>
      <div className="logo">
        <img src={Logo} />
        <img src={LogoTitle} />
      </div>
      <div className="link">
        <a href="/search">search</a>
        <a href="/create">create group</a>
        <img src={avartar} />
      </div>
    </StyledHeader>
  );
}

const StyledLayout = styled(Layout)`
  width: 100vw;
  height: 100vh;
`;

const StyledHeader = styled(Header)`
  text-align: center;
  height: 64;
  padding-inline: 5rem;
  line-height: 64px;
  background-color: #fff;
  display: flex;
  align-items: center;
  justify-content: space-between;

  .logo {
    display: flex;
  }

  .link {
    display: flex;
    align-items: center;

    a {
      margin-right: 1rem;
    }
  }
`;

const StyledContent = styled(Content)`
  text-align: center;
  min-height: 120;
  line-height: 120px;
  color: #fff;
  background-color: #108ee9;
  padding-inline: 5rem;
`;

const StyledFooter = styled(Footer)`
  text-align: center;
  color: #fff;
  background-color: #6d6d6d;
  padding-inline: 5rem;
`;
