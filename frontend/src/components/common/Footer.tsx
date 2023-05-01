import { Footer } from "antd/es/layout/layout";
import styled from "styled-components";

export default function CustomFooter() {
  return <StyledFooter>Footer</StyledFooter>;
}

const StyledFooter = styled(Footer)`
  text-align: center;
  color: #fff;
  background-color: #6d6d6d;
  padding-inline: 5rem;
`;
