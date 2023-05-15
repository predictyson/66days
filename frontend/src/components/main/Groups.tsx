// import { useState } from "react";
import styled from "styled-components";
// TODO: assets
import PrevArrow from "../../assets/main/PrevArrow.png";
import NextArrow from "../../assets/main/NextArrow.png";
import GroupItem from "./GroupItem";
import { GroupData } from "../../types/main";
import Slider from "react-slick";
// slider
interface IProps {
  groups: GroupData[];
}
export default function GroupList({ groups }: IProps) {
  const settings = {
    centerMode: false,
    dots: false,
    infinite: true,
    slidesToShow: 3,
    slidesToScroll: 3,
    arrows: true,
    prevArrow: <img src={PrevArrow} />,
    nextArrow: <img src={NextArrow} />,
    responsive: [
      {
        breakpoint: 1250,
        settings: {
          slidesToShow: 2,
          slidesToScroll: 2,
          infinite: true,
        },
      },
      {
        breakpoint: 880,
        settings: {
          slidesToShow: 1,
          slidesToScroll: 1,
          infinite: true,
        },
      },
    ],
  };

  return (
    <Container>
      <Title>Groups</Title>
      <GroupContainer>
        {groups.length === 1 ? (
          <div
            style={{
              display: "flex",
              alignItems: "center",
              justifyContent: "space-between",
            }}
          >
            <GroupItem group={groups[0]} />
            <EmptyBox />
            <EmptyBox />
          </div>
        ) : groups.length === 2 ? (
          <div
            style={{
              display: "flex",
              alignItems: "center",
              justifyContent: "space-between",
            }}
          >
            {groups.map((group, idx) => {
              return <GroupItem key={idx} group={group} />;
            })}
            <EmptyBox />
          </div>
        ) : (
          <Slider {...settings}>
            {groups.map((group, idx) => {
              return <GroupItem key={idx} group={group} />;
            })}
          </Slider>
        )}
      </GroupContainer>
    </Container>
  );
}

const Container = styled.div`
  width: 100%;
  display: flex;
  font-family: Pretendard;
  padding: 2% 9%;
  display: flex;
  flex-direction: column;
  .slick-prev {
    width: 3rem;
    height: 3rem;
  }
  .slick-next {
    width: 3rem;
    height: 3rem;
  }
`;

const Title = styled.div`
  font-size: 3.2rem;
  font-weight: bold;
  display: flex;
  justify-content: space-between;
`;
const GroupContainer = styled.div`
  margin-top: 2rem;
  height: 55rem;
`;

const EmptyBox = styled.div`
  width: 35rem !important;
  margin: 0 auto;
`;
