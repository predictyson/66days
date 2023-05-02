import React, { useState } from "react";
import styled from "styled-components";
import { theme } from "../../styles/theme";
import Algo from "../../assets/landing/algoBox.png";
import Blog from "../../assets/landing/blogBox.png";
import PrevArrow from "../../assets/main/PrevArrow.png";
import NextArrow from "../../assets/main/NextArrow.png";
import GroupItem from "./GroupItem";
// slider
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import Slider, { CustomArrowProps } from "react-slick";
// interface ArrowProps extends CustomArrowProps {
//   onClick?: MouseEventHandler<HTMLDivElement>;
// }

export default function GroupList() {
  const groupItems = [
    <GroupItem />,
    <GroupItem />,
    <GroupItem />,
    <GroupItem />,
    <GroupItem />,
  ];
  let group: any = [];
  group[0] = groupItems.slice(0, 3);
  group[1] = groupItems.slice(3, groupItems.length);
  if (groupItems.length === 5) {
    group[1].push(<BoxWrapper />);
  }
  const [sliderdata, setSliderData] = useState(0);
  const handlePrevClick = () => {
    // const newStartIndex = Math.max(startIndex - groupCount, 0);
    // setStartIndex(newStartIndex);
    // console.log(newStartIndex);
    setSliderData(Math.abs(sliderdata - 1));
  };

  const handleNextClick = () => {
    // const newStartIndex = Math.min(
    //   startIndex + groupCount,
    //   groupItems.length - groupCount
    // );
    // setStartIndex(newStartIndex);
    // console.log(newStartIndex);
    setSliderData(Math.abs(sliderdata - 1));
  };

  const visibleGroupItems = groupItems.slice();

  return (
    <Container>
      <Title>
        Groups
        <ArrowWrapper>
          <img src={PrevArrow} onClick={handlePrevClick} />
          <img src={NextArrow} onClick={handleNextClick} />
        </ArrowWrapper>
      </Title>
      <GroupContainer>{group[sliderdata]}</GroupContainer>
    </Container>
  );
}

const Container = styled.div`
  width: 100%;
  display: flex;
  font-family: Pretendard;
  padding: 4.6rem 8rem;
  height: 70rem;
  display: flex;
  flex-direction: column;
`;

const Title = styled.div`
  font-size: 3.2rem;
  font-weight: bold;
  display: flex;
  justify-content: space-between;
`;
const GroupContainer = styled.div`
  display: flex;
  justify-content: space-between;
  margin-top: 3.2rem;
`;

const ArrowWrapper = styled.div`
  width: 16rem;
  display: flex;
  justify-content: space-between;
  img {
    cursor: pointer;
  }
`;

const BoxWrapper = styled.div`
  width: 33.2rem;
  height: 43.8rem;
  border-radius: 15px;
`;
