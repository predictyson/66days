import { useState } from "react";
import styled from "styled-components";
// TODO: assets
import PrevArrow from "../../assets/main/PrevArrow.png";
import NextArrow from "../../assets/main/NextArrow.png";
import GroupItem from "./GroupItem";
// slider

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
    setSliderData(Math.abs(sliderdata - 1));
  };

  const handleNextClick = () => {
    setSliderData(Math.abs(sliderdata - 1));
  };

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
  padding: 2% 2%;
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
  margin-top: 2rem;
`;

const ArrowWrapper = styled.div`
  width: 16rem;
  display: flex;
  justify-content: space-between;
  img {
    cursor: pointer;
  }
`;
// dummy
const BoxWrapper = styled.div`
  width: 27%;
  height: 43.8rem;
  border-radius: 15px;
`;
