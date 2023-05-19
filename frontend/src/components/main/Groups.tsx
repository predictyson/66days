// import { useState } from "react";
import styled from "styled-components";
// TODO: assets
import PrevArrow from "../../assets/main/PrevArrow.png";
import NextArrow from "../../assets/main/NextArrow.png";
import GroupItem from "./GroupItem";
import { GroupData, MyGroupData } from "../../types/main";
import Slider from "react-slick";
// slider
interface IProps {
  groups?: GroupData[];
  myGroup?: MyGroupData;
}
export default function GroupList({ groups, myGroup }: IProps) {
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
        {/**TODO: mygroup */}
        {groups.length === 1 ? (
          <div
            style={{
              display: "flex",
              alignItems: "center",
              justifyContent: "space-between",
            }}
          >
            {/* <MyGroupItem>
              <ImageWrapper imageUrl={getImagePath(myGroup.profileImagePath)}>
                <span>개인 챌린지 </span>
              </ImageWrapper>
              <ChallengeWrapper>dd</ChallengeWrapper>
            </MyGroupItem> */}
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

// const ImageWrapper = styled.div<ImageWrapperProps>`
//   position: relative;
//   width: 100%;
//   height: 0;
//   padding-bottom: 100%; /* 이미지 비율을 1:1로 유지 */
//   background-image: url(${(props) => props.imageUrl});
//   background-size: cover; /* 이미지를 부모 요소 크기에 맞춤 */
//   background-repeat: no-repeat;

//   span {
//     position: absolute;
//     bottom: 0;
//     left: 0;
//     right: 0;
//     margin-bottom: 2rem;
//     font-size: 2.4rem;
//     font-weight: bold;
//     padding: 1rem 2rem;
//     color: white;
//   }
// `;
// // const ChallengeWrapper = styled.div`
// //   margin-top: 0.3rem;
// //   display: flex;
// //   justify-content: space-between;
// // `;
// // const MyGroupItem = styled.div`
// //   width: 35rem !important;
// //   border-radius: 15px;
//   margin: 0 auto !important;
//   cursor: pointer;
// `;
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
