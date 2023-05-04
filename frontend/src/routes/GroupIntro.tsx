import styled from "styled-components";
import GroupIntroImage from "../assets/group-intro.svg";
import { Button, Card } from "antd";

export default function GroupIntro() {
  return (
    <>
      <StyledTitle>
        <h1>Group Introduction</h1>
      </StyledTitle>
      <StyledContainer>
        <div style={{ flex: 1 }}>
          <img src={GroupIntroImage} style={{ width: "100%" }} />
        </div>
        <StyledBriefIntro style={{ flex: 1 }}>
          <Card style={{ marginInline: "auto" }}>
            <div style={{ display: "flex" }}>
              <div>
                그룹원
                <br /> 80명
              </div>
              <div>
                진행
                <br /> 3건
              </div>
              <div>
                완료
                <br /> 10건
              </div>
            </div>
          </Card>
          <div>
            뭉치뭉치똥뭉치네
            <p>뭉치의 친구들로 이루어진 뭉치뭉치똥뭉치네 공간입니다.</p>
          </div>
          <Button>apply</Button>
        </StyledBriefIntro>
      </StyledContainer>
    </>
  );
}

const StyledTitle = styled.section`
  margin-top: 4rem;
  margin-bottom: 1.6rem;
  h1 {
    font-size: 3.2rem;
    font-family: "Kanit-Bold";
    margin: 0;
  }
  h2.title {
    font-size: 2.4rem;
    font-family: "Kanit-Bold";

    .title__days {
      color: #b8a9fb;
    }
    .title__desc {
      font-size: 1.6rem;
    }
  }
  div {
    font-size: 1.6rem;
    margin-left: 5rem;
    color: #b4b4b4;
  }
`;

const StyledContainer = styled.div`
  display: flex;
  margin-bottom: 4rem;
`;

const StyledBriefIntro = styled.section`
  flex: 1;
  background-color: #dbff73;

  h1 {
    font-size: 3.2rem;
    font-family: "Kanit-Bold";
    margin: 0;
  }
  div {
    font-size: 1.6rem;
    margin-left: 5rem;
    color: #b4b4b4;
  }
`;
