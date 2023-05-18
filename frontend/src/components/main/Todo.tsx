import styled from "styled-components";
// import Blog from "../../assets/landing/blogBox.png";
import PrevArrow from "../../assets/main/PrevArrow.png";
import NextArrow from "../../assets/main/NextArrow.png";
import { TodayTodoData } from "../../types/main";
// slider
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import Slider from "react-slick";
import TodoItem from "./TodoItem";
import EmptySign from "../../util/EmptySign";

interface IProps {
  challenges: TodayTodoData[];
}

export default function Todo({ challenges }: IProps) {
  console.log(challenges);
  const settings = {
    centerMode: false,
    dots: false,
    infinite: true,
    slidesToShow: 2,
    slidesToScroll: 2,
    arrows: true,
    prevArrow: <img src={PrevArrow} />,
    nextArrow: <img src={NextArrow} />,
    responsive: [
      {
        breakpoint: 1200,
        settings: {
          slidesToShow: 1,
          slidesToScroll: 1,
          infinite: true,
        },
      },
    ],
  };
  challenges.map((c) => console.log(c));
  return (
    <Container>
      <Title>Today's todo</Title>
      <TodoContianer>
        {challenges.length === 0 ? (
          <EmptySign category="할 일이" />
        ) : challenges.length === 1 ? (
          <div style={{ display: "flex", justifyContent: "space-between" }}>
            <TodoItem challenge={challenges[0]} />
            <EmptyTodo />
          </div>
        ) : (
          <Slider {...settings}>
            {challenges
              .filter((c) => c.length > 0)
              .map((challenge, idx) => {
                <TodoItem key={idx} challenge={challenge} />;
              })}
          </Slider>
        )}
      </TodoContianer>
    </Container>
  );
}

const Container = styled.div`
  width: 100%;
  display: flex;
  font-family: Pretendard;
  padding: 3% 9%;
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
`;
const TodoContianer = styled.div`
  /* display: flex; */
  margin-top: 3.2rem;
`;

const EmptyTodo = styled.div`
  width: 90% !important;
  height: 35rem;
`;
