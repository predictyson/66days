import styled from "styled-components";
import { theme } from "../../styles/theme";
import Profile1 from "../../assets/main/Profile1.png";
import Profile2 from "../../assets/main/Profile2.png";
import Profile3 from "../../assets/main/Profile3.png";
import Vector1 from "../../assets/main/VectorMain.svg";
import Vector2 from "../../assets/main/VectorMain2.svg";

export default function Ranking() {
  return (
    <div
      style={{
        display: "flex",
        justifyContent: "space-between",
        padding: "2%",
      }}
    >
      <LeftConainer>
        <div className="title">Ranking</div>
        <ImgConatiner>
          <img className="right" src={Vector1} />
        </ImgConatiner>
        <ImgConatiner>
          <img src={Vector2} />
        </ImgConatiner>
      </LeftConainer>
      <RightContainer>
        <RankingBox>
          <div className="title">경험치 Top3</div>
          <RankingWrapper>
            <RankingItem>
              <img src={Profile1} />
              <div className="content">
                @moongchi
                <br /> 게으른 카피바라
              </div>
              <EXPBox className="silver" type="silver">
                64
              </EXPBox>
            </RankingItem>
            <RankingItem>
              <img src={Profile2} />
              <div className="content">
                @happy
                <br />
                성실한 문어
              </div>
              <EXPBox className="gold" type="gold">
                128
              </EXPBox>
            </RankingItem>
            <RankingItem>
              <img src={Profile3} />
              <div className="content">
                @ppoppi
                <br />
                귀찮은 멍멍이
              </div>
              <EXPBox className="bronze" type="bronze">
                32
              </EXPBox>
            </RankingItem>
          </RankingWrapper>
          <MyRankingBox>
            My Rank #2
            <div className="exp"> 1000 EXP</div>
          </MyRankingBox>
        </RankingBox>
        <RankingBox>
          <div className="title">뱃지 수 TOP3</div>
          <RankingWrapper>
            <RankingItem>
              <img src={Profile1} />
              <div className="content">
                @moongchi
                <br /> 게으른 카피바라
              </div>
              <EXPBox className="silver" type="silver">
                64
              </EXPBox>
            </RankingItem>
            <RankingItem>
              <img src={Profile2} />
              <div className="content">
                @happy
                <br />
                성실한 문어
              </div>
              <EXPBox className="gold" type="gold">
                128
              </EXPBox>
            </RankingItem>
            <RankingItem>
              <img src={Profile3} />
              <div className="content">
                @ppoppi
                <br />
                귀찮은 멍멍이
              </div>
              <EXPBox className="bronze" type="bronze">
                32
              </EXPBox>
            </RankingItem>
          </RankingWrapper>
          <MyRankingBox>
            My Rank #2
            <div className="exp"> 34개</div>
          </MyRankingBox>
        </RankingBox>
      </RightContainer>
    </div>
  );
}

const LeftConainer = styled.div`
  width: 50%;
  margin-top: 3rem;
  height: 125rem;
  background-color: ${theme.colors.purple};
  padding: 2rem;
  .title {
    font-size: 3.2rem;
    font-weight: bold;
  }
  img {
    width: 50rem;
    height: 50rem;
  }
`;

const ImgConatiner = styled.div`
  width: 100%;
  height: 45%;
  margin-top: 4rem;
  img {
    width: 70%;
  }
  .right {
    display: flex;
    margin-left: auto;
  }
`;

const RightContainer = styled.div`
  width: 50%;
  display: flex;
  flex-direction: column;
  align-items: flex-end;
`;

const RankingBox = styled.div`
  margin-top: 3.2rem;
  width: 90%;
  height: 60rem;
  border: solid 1px ${theme.colors.gray400};
  border-radius: 20px;
  padding: 3.2rem 2.4rem;
  display: flex;
  flex-direction: column;
  .title {
    font-size: 3rem;
    font-weight: ${theme.fontWeight.extrabold};
    text-align: center;
  }
`;

const RankingWrapper = styled.div`
  margin-top: 2.5rem;
  height: 30rem;
  display: flex;
  justify-content: space-between;
`;

const MyRankingBox = styled.div`
  width: 100%;
  height: 13rem;
  border-radius: 20px;
  background-color: black;
  margin-top: 3.2rem;
  padding: 2.4rem 4.8rem;
  color: white;
  font-size: 2.4rem;
  font-weight: bold;
  display: flex;
  flex-direction: column;
  .exp {
    align-self: flex-end;
    display: flex;
    margin-top: auto;
    color: #dbff73;
  }
`;

const RankingItem = styled.div`
  width: 31%;
  align-self: flex-end;
  flex-direction: column;
  justify-content: center;
  display: flex;
  img {
    width: 8rem;
    height: 8rem;
    margin: 0 auto;
  }
  .content {
    margin-top: 0.8rem;
    text-align: center;
    font-size: 1.6rem;
    font-weight: bold;
  }
`;

const EXPBox = styled.div<{ type: string }>`
  border-radius: 30px 30px 0 0;
  display: flex;
  text-align: center;
  font-size: 2.4rem;
  /* flex-direction: column-reverse; */
  font-weight: bold;
  /* width: 100%; */
  width: 100%;
  margin-top: 0.8rem;
  justify-content: center;
  align-items: flex-end;
  &.gold {
    height: 16rem;
    background-color: #ffebc2;
  }
  &.silver {
    height: 10rem;
    background-color: #eeeeee;
  }
  &.bronze {
    height: 6.4rem;
    background-color: #dfd0cc;
  }
`;
