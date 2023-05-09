import styled from "styled-components";
import BreadCrumb from "./BreadCrumb";
export default function Group() {
  return (
    <Container>
      <BreadCrumb />
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  margin: 0 auto;
`;
