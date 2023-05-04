import { Layout } from "antd";
import { Outlet } from "react-router-dom";
import styled from "styled-components";
import Header from "../components/common/Header";
import Footer from "../components/common/Footer";
import { theme } from "../styles/theme";

const { Content } = Layout;

export default function Root() {
  return (
    <StyledLayout>
      <Header />
      <StyledContent>
        <Outlet />
      </StyledContent>
      <Footer />
    </StyledLayout>
  );
}

const StyledLayout = styled(Layout)`
  width: 100vw;
  min-height: 100vh;
  background-color: ${theme.colors.white};
`;

// const StyledContent = styled(Content)`
//   /* text-align: center; */
//   min-height: 120;
//   /* line-height: 120px; */
// `;

const StyledContent = styled(Content)`
  font-size: 1rem;
  text-align: center;
  min-height: 120;
  line-height: 120px;
  background-color: #fff;
  padding-inline: 5rem;
  width: 1200px;
  margin-inline: auto;
  text-align: inherit;
  line-height: initial;
  font-family: Pretendard;
  display: flex;
  flex-direction: column;

  @media screen and (max-width: 1200px) {
    width: 768px;
  }
`;
