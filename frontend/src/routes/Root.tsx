import { Layout } from "antd";
import { Outlet } from "react-router-dom";
import styled from "styled-components";
import Header from "../components/common/Header";
import Footer from "../components/common/Footer";
import instance from "../api/api";
import { useAuthStore } from "../stores/useAuthStore";

export default function Root() {
  const getToken = useAuthStore((state) => state.getToken);
  instance.defaults.headers.common.Authorization = getToken();

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
  background-color: ${(props) => props.theme.colors.white};
`;

const StyledContent = styled.div`
  line-height: initial;
  min-height: 100vh;
  /* margin-inline: 8rem; */
`;
