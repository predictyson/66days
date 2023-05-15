import styled from "styled-components";

interface IProps {
  category: string;
}
export default function EmptySign({ category }: IProps) {
  return <Container>등록된 {category}가 없습니다.</Container>;
}

const Container = styled.div`
  width: 100%;
  border: solid 5px gray;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 5rem;
  color: gray;
`;
