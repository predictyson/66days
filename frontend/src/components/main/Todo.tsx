import styled from "styled-components";
import { theme } from "../../styles/theme";
import Algo from "../../assets/landing/algoBox.png";
// import Blog from "../../assets/landing/blogBox.png";
import Chart from "./Chart";
import { CheckCircleOutlined } from "@ant-design/icons";
import { ChallengeData } from "../../types/main";
// slider
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import Slider from "react-slick";

interface IProps {
  challenges: ChallengeData[];
}

export default function Todo({ challenges }: IProps) {
  function countDays(startDate: string): number {
    const oneDay = 24 * 60 * 60 * 1000; // 1일을 밀리초로 계산
    const startDateTime = new Date(startDate).getTime(); // 시작일을 밀리초로 변환
    const todayDateTime = new Date().getTime(); // 현w재 시간을 밀리초로 변환
    const diffDays = Math.round(
      Math.abs((todayDateTime - startDateTime) / oneDay)
    ); // 날짜 차이 계산
    return diffDays;
  }

  const settings = {
    centerMode: false,
    dots: false,
    infinite: true,
    slidesToShow: 2,
    slidesToScroll: 2,
    arrows: true,
    // prevArrow: <CustomPrevArrow />,
    // nextArrow: <CustomNextArrow />,
    // responsive: BREAKPOINT,
  };
  return (
    <Container>
      <Title>Today's todo</Title>
      <TodoContianer>
        <Slider {...settings}>
          {challenges.map((challenge, idx) => {
            return (
              <TodoBox key={idx}>
                <div className="left">
                  <img src={Algo} alt="algo" />
                  <div className="title">{challenge.name}</div>
                </div>
                <div className="right">
                  {challenge.status ? <CustomChecked /> : <CustomUnChecked />}
                  <Chart x={countDays(challenge.startDate)} />
                  <p>{countDays(challenge.startDate)} / 66</p>
                </div>
              </TodoBox>
            );
          })}
        </Slider>
      </TodoContianer>
    </Container>
  );
}

// FIXME: 슬라이더 CSS가 깨져요 :)
const CustomSlider = styled(Slider)`
  /* width: 100%; */
  /* border: solid 2px green;
  height: 50rem;
  /* height: 80%; */
  .slick-slide {
    margin: 0 1rem;
    width: auto !important;
    margin: 0 !important;
    /* display: flex !important; set margin between slides */
  }

  .slick-list {
    overflow: visible; /* show the left and right overflow */
  }

  .slick-slide > div {
    width: 55rem;
    /* margin: 0 auto; center TodoBox within slide */
  }

  /* hide the extra slide that appears when there are not enough items to fill a slide */
  .slick-slide:not(.slick-active) {
    visibility: hidden;
  }
`;
const CustomChecked = styled(CheckCircleOutlined)`
  font-size: 4rem;
  color: ${theme.colors.mint};
  cursor: pointer;
  display: flex;
  justify-content: flex-end;
`;

const CustomUnChecked = styled(CheckCircleOutlined)`
  font-size: 4rem;
  color: ${theme.colors.gray400};
  cursor: pointer;
  display: flex;
  justify-content: flex-end;
`;
const Container = styled.div`
  width: 100%;
  display: flex;
  font-family: Pretendard;
  padding: 3% 9%;
  display: flex;
  flex-direction: column;
`;

const Title = styled.div`
  font-size: 3.2rem;
  font-weight: bold;
`;
const TodoContianer = styled.div`
  /* display: flex; */
  border: solid 1px blue;
  /* justify-content: space-between; */
  margin-top: 3.2rem;
`;

const TodoBox = styled.div`
  width: 55rem;
  height: 35rem;
  border: solid 1px ${theme.colors.gray400};
  border-radius: 15px;
  /* display: flex; */
  /* flex-direction: row; */
  img {
    width: 70%;
    display: flex;
    margin: auto 0;
  }
  .left {
    border: solid 1px red;

    width: 50%;
    display: flex;
    align-items: center;
    flex-direction: column;
    font-size: 2.4rem;
    font-weight: ${theme.fontWeight.semibold};
  }
  .title {
    font-size: 90%;
    text-align: center;
    display: flex;
    margin: 0 auto;
    margin-bottom: auto;

    white-space: nowrap;
    text-overflow: ellipsis;
  }
  .right {
    border: solid 1px red;
    width: 45%;
    padding: 3rem;
    p {
      font-size: 2rem;
      text-align: center;
      font-weight: bold;
    }
  }
`;
