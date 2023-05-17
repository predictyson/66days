import { Layout } from "antd";
import { Outlet, useNavigate } from "react-router-dom";
import styled from "styled-components";
import Header from "../components/common/Header";
import Footer from "../components/common/Footer";
import { useEffect } from "react";
import { useAuthStore } from "../stores/useAuthStore";

export default function Root() {
  const navigate = useNavigate();
  const user = useAuthStore((state) => state.user);

  /**
   * Whenever a user is logged in, logged out, or update User info
   */
  // useEffect(() => {
  //   user !== null && navigate("/", { replace: true });
  // }, [user]);

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
