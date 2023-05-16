import algo from "../../assets/landing//algoBox.png";
import cs from "../../assets/landing//csBox.png";
import lecture from "../../assets/landing//lectureBox.png";
import document from "../../assets/landing//documentBox.png";
import blog from "../../assets/landing//blogBox.png";
import styled from "styled-components";

// interface BoxWrapperProps {
//   delay?: number;
// }
const BOXDATA = [
  {
    img: algo,
    title: "알고리즘",
  },
  {
    img: cs,
    title: "CS 공부",
  },

  {
    img: lecture,
    title: "강의 시청",
  },

  {
    img: document,
    title: "기술 서적",
  },

  {
    img: blog,
    title: "블로그 작성",
  },
];

export default function Landing2() {
  return (
    <Container>
      <Content>
        개발자를 위한 맞춤 챌린지 카테고리
        <div className="subcontent">
          개발자에게 필요한 챌린지들을 준비했어요
        </div>
      </Content>
      <BoxContainer>
        {BOXDATA.map((v, index) => {
          return (
            <BoxWrapper
              key={index}
              style={{ marginTop: index % 2 === 1 ? "6rem" : "0" }}
            >
              <BoxImg src={v.img} alt="box img" />
              <p>{v.title}</p>
            </BoxWrapper>
          );
        })}
      </BoxContainer>
    </Container>
  );
}
const Container = styled.div`
  background-color: black;
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 76.8rem;
  border: solid 1px black;
  display: flex;
  font-family: Pretendard;
  padding-inline: 2%;
  color: white;
  @media (max-width: 800px) {
    height: 45rem;
  }
`;

const Content = styled.div`
  margin: 0 auto;
  margin-top: 12rem;
  font-size: 4rem;
  font-weight: bold;
  display: flex;
  flex-direction: column;
  text-align: center;
  @media (max-width: 800px) {
    font-size: 3rem;
    margin-top: 8rem;
  }
  .subcontent {
    font-size: 2rem;
    margin-top: 2rem;
    font-weight: 400;
    @media (max-width: 800px) {
      font-size: 1rem;
    }
  }
`;

const BoxContainer = styled.div`
  padding: 0 10%;
  margin-top: 10rem;
  justify-content: space-between;
  display: flex;
  @media (max-width: 800px) {
    font-size: 1rem;
    margin-top: 5rem;
  }
`;

const BoxWrapper = styled.div`
  display: flex;
  flex-direction: column;
  text-align: center;
  font-size: 2.4rem;
  animation: drop 0.5s ease-in-out;
  animation-delay: 2rem;
  align-items: center;
  @media (max-width: 800px) {
    font-size: 1.2rem;
  }
  /* justify-content: center; */
  @keyframes drop {
    0% {
      transform: translateY(-10rem);
      opacity: 0;
    }
    100% {
      transform: translateY(0);
      opacity: 1;
    }
  }
`;

const BoxImg = styled.img`
  width: 90%;
  /* height: 80%; */
  border-radius: 15px;
`;
