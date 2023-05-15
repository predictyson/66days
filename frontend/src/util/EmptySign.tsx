import styled from "styled-components";

interface IProps {
  category: string;
}
export default function EmptySign({ category }: IProps) {
  return <Container>등록된 {category} 없습니다.</Container>;
}

const Container = styled.div`
  width: 100%;
  border: dashed 3px gray;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 3rem;
  color: gray;
  padding: 10rem 0;
  border-radius: 10px;
`;
