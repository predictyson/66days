import styled from "styled-components";
import { useState } from "react";
import { theme } from "../../styles/theme";

const items = [
  {
    title: "전체보기",
  },
  {
    title: "알고리즘",
  },
  {
    title: "CS",
  },
  {
    title: "블로깅",
  },
  {
    title: "강의",
  },
  {
    title: "개발서적",
  },
];
export default function BreadCrumb() {
  const [activeItem, setActiveItem] = useState(0);
  return (
    <Container>
      {items.map((item, idx) => {
        return (
          <div key={idx} style={{ display: "flex" }}>
            <CrumbItem
              key={idx}
              active={idx === activeItem}
              onClick={() => setActiveItem(idx)}
            >
              {item.title}
            </CrumbItem>
            {idx !== items.length - 1 && (
              <span style={{ marginLeft: "1.6rem" }}>|</span>
            )}
          </div>
        );
      })}
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  margin-top: 1.3rem;
  align-self: flex-end;
  span {
    display: flex;
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
  align-items: center;
  font-weight: ${(props) => (props.active ? "bold" : "normal")};
  color: ${(props) => (props.active ? "#333" : "#888")};
  cursor: pointer;
  margin-left: 1.6rem;
  font-size: 1.6rem;

  &:hover {
    text-decoration: underline;
    color: #555;
  }

  &:active {
    color: #000;
  }
  span {
    font-weight: normal;
    color: #888;
    text-decoration: none;
    pointer-events: none;
    &:hover {
      text-decoration: none;
    }
  }
`;
