import styled from "styled-components";
import BreadCrumb from "./BreadCrumb";
// import Challenge from "./Challenge";
import GroupItem from "../../components/mypage/GroupItem";
import { useState } from "react";
import { MyChallengeData, MyGroupData } from "../../types/mypage";
import Challenge from "../../components/mypage/Challenge";
import Slider from "react-slick";
import EmptySign from "../../util/EmptySign";
interface IProps {
  groups: MyGroupData[];
  challenges: MyChallengeData[];
}
export default function Group({ groups, challenges }: IProps) {
  const [activeItem, setActiveItem] = useState(0);
  const handleBread = (idx: number) => {
    setActiveItem(idx);
  };
  console.log(groups);
  console.log(challenges);
  const settings = {
    centerMode: false,
    dots: false,
    infinite: true,
    slidesToShow: 2,
    slidesToScroll: 2,
    arrows: false,
    responsive: [
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
      <BreadCrumb activeItem={activeItem} handleBread={handleBread} />
      <ContentWrapper>
        {activeItem === 0 ? (
          groups.length === 0 ? (
            <EmptySign category="그룹이" />
          ) : groups.length === 1 ? (
            <div style={{ display: "flex", justifyContent: "space-between" }}>
              <GroupItem group={groups[0]} />
              <EmptyBox />
            </div>
          ) : (
            <Slider {...settings}>
              {groups.map((group, idx) => {
                return <GroupItem key={idx} group={group} />;
              })}
            </Slider>
          )
        ) : challenges.length === 0 ? (
          <EmptySign category="챌린지가" />
        ) : challenges.length === 1 ? (
          <div style={{ display: "flex", justifyContent: "space-between" }}>
            <Challenge challenge={challenges[0]} />
            <EmptyBox />
          </div>
        ) : (
          <Slider {...settings}>
            {challenges.map((challenge, idx) => {
              return <Challenge key={idx} challenge={challenge} />;
            })}
          </Slider>
        )}
      </ContentWrapper>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  margin: 0 auto;
  @media (max-width: 1200px) {
    width: 80%;
  }
  @media (max-width: 800px) {
    width: 100%;
  }
`;

const ContentWrapper = styled.div`
  .slick-list {
    height: 30rem;
  }
`;
const EmptyBox = styled.div`
  width: 45rem;
  height: 22rem;
`;
