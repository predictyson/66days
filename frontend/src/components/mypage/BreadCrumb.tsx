import styled from "styled-components";
import { theme } from "../../styles/theme";

const items = [
  {
    title: "참여중인 그룹",
  },
  {
    title: "진행중인 챌린지",
  },
];
interface IProps {
  activeItem: number;
  handleBread: (idx: number) => void;
}
export default function BreadCrumb({ activeItem, handleBread }: IProps) {
  return (
    <Container>
      {items.map((item, idx) => {
        return (
          <CrumbItem
            key={idx}
            active={idx === activeItem}
            onClick={() => handleBread(idx)}
          >
            {item.title}
            {idx !== items.length - 1 && (
              <span style={{ marginLeft: "auto" }}>|</span>
            )}
          </CrumbItem>
        );
      })}
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  span {
    font-size: 1.6rem;
    color: ${theme.colors.gray300};
    font-weight: bold;
  }
`;
interface CrumbItemProps {
  active: boolean;
}

const CrumbItem = styled.div<CrumbItemProps>`
  display: flex;
  width: 17%;
  align-items: center;
  font-weight: ${(props) => (props.active ? "bold" : "normal")};
  color: ${(props) => (props.active ? "#333" : "#888")};
  cursor: pointer;
  margin-right: 1.6%;
  font-size: 2rem;

  &:hover {
    text-decoration: underline;
    color: #555;
  }

  &:active {
    color: #000;
  }
`;