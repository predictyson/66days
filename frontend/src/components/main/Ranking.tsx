import styled from "styled-components";
import { theme } from "../../styles/theme";
import Profile1 from "../../assets/main/Profile1.png";
import Profile2 from "../../assets/main/Profile2.png";
import Profile3 from "../../assets/main/Profile3.png";
import Vector1 from "../../assets/main/VectorMain.svg";
import Vector2 from "../../assets/main/VectorMain2.svg";
import { RankData } from "../../types/main";

interface IProps {
  ranking: RankData;
}
export default function Ranking({ ranking }: IProps) {
  return (
    <div
      style={{
        display: "flex",
        justifyContent: "space-between",
        padding: "2% 3%",
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
                @{ranking.expRank[1].name}
                <br />
                {ranking.expRank[1].animal}
              </div>
              <EXPBox className="silver" type="silver">
                {ranking.expRank[1].exp}
              </EXPBox>
            </RankingItem>
            <RankingItem>
              <img src={Profile2} />
              <div className="content">
                @{ranking.expRank[0].name}
                <br />
                성실한 {ranking.expRank[0].animal}
              </div>
              <EXPBox className="gold" type="gold">
                {ranking.expRank[0].exp}
              </EXPBox>
            </RankingItem>
            <RankingItem>
              <img src={Profile3} />
              <div className="content">
                @{ranking.expRank[2].name}
                <br />
                귀찮은 {ranking.expRank[2].animal}
              </div>
              <EXPBox className="bronze" type="bronze">
                {ranking.expRank[2].exp}
              </EXPBox>
            </RankingItem>
          </RankingWrapper>
          <MyRankingBox>
            My Rank #{ranking.myExpRank}
            <div className="exp">{ranking.myExp} EXP</div>
          </MyRankingBox>
        </RankingBox>
        <RankingBox>
          <div className="title">뱃지 수 TOP3</div>
          <RankingWrapper>
            <RankingItem>
              <img src={Profile1} />
              <div className="content">
                {ranking.badgeRank[1].name}
                <br /> 게으른 {ranking.badgeRank[1].animal}
              </div>
              <EXPBox className="silver" type="silver">
                {ranking.badgeRank[1].badge}
              </EXPBox>
            </RankingItem>
            <RankingItem>
              <img src={Profile2} />
              <div className="content">
                @{ranking.badgeRank[0].name}
                <br />
                성실한 {ranking.badgeRank[0].animal}
              </div>
              <EXPBox className="gold" type="gold">
                {ranking.badgeRank[0].badge}
              </EXPBox>
            </RankingItem>
            <RankingItem>
              <img src={Profile3} />
              <div className="content">
                {ranking.badgeRank[2].name}
                <br />
                귀찮은 {ranking.badgeRank[2].animal}
              </div>
              <EXPBox className="bronze" type="bronze">
                {ranking.badgeRank[2].badge}
              </EXPBox>
            </RankingItem>
          </RankingWrapper>
          <MyRankingBox>
            My Rank #{ranking.myBadgeRank}
            <div className="exp"> {ranking.myBadge}개</div>
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
