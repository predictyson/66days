import styled from "styled-components";
import { theme } from "../../styles/theme";
import Profile1 from "../../assets/main/Profile1.png";
import GroupImg from "../../assets/search/group_image.png";
export default function GroupItem() {
  return (
    <Conatiner>
      <ProfileWrapper>
        <img src={Profile1} />
        <TitleWrapper>
          뭉치
          <div className="sub">게으른 카피바라</div>
        </TitleWrapper>
      </ProfileWrapper>
      <img src={GroupImg} style={{ height: "18.8rem" }} />
      <MaterialWrapper>
        <div className="title">알고리즘 파트원 구해요</div>
        <div style={{ display: "flex", marginTop: "0.7rem" }}>
          {DUMMY.map((v, idx) => {
            const category = CATEGORY.find((c) => c.title === v); // 해당 title의 category를 찾음
            const col = category ? category.color : "gray";
            return (
              <CategoryItem key={idx} color={col}>
                {v}
              </CategoryItem>
            );
          })}
        </div>
      </MaterialWrapper>
    </Conatiner>
  );
}

const Conatiner = styled.div`
  width: 36rem;
  height: 48rem;
  border-radius: 12px;
  display: flex;
  flex-direction: column;
  box-shadow: 0 0 5px 2px ${theme.colors.gray200};
  cursor: pointer;
`;

const ProfileWrapper = styled.div`
  height: 7.2rem;
  display: flex;
  align-items: center;
  padding: 0 1.6rem;
  img {
    width: 4rem;
  }
`;
const TitleWrapper = styled.div`
  margin-left: 1.6rem;
  display: flex;
  flex-direction: column;
  font-size: 1.6rem;
  .sub {
    margin-top: 0.2rem;
    color: ${theme.colors.gray400};
  }
`;

const MaterialWrapper = styled.div`
  padding: 1.7rem 1.6rem;
  border: solid 1px red;
  .title {
    font-size: 2rem;
    font-weight: ${theme.fontWeight.semibold};
  }
`;

interface CategoryItemProps {
  color: string;
}

const CategoryItem = styled.div<CategoryItemProps>`
  color: ${(props) => props.color};
  font-size: 1.2rem;
  font-weight: ${theme.fontWeight.semibold};
  margin-right: 1.6rem;
  padding: 0.5rem 1.6rem;
  border: solid 2px ${(props) => props.color};
  border-radius: 10px;
`;

const DUMMY = ["알고리즘", "블로그"];
const CATEGORY = [
  {
    title: "알고리즘",
    color: "#6CD3C0",
  },
  {
    title: "CS",
    color: "#FBA9D6",
  },
  {
    title: "블로그",
    color: "#F37F3A",
  },
  {
    title: "강의 시청",
    color: "#B8A9FB",
  },
  {
    title: "개발 서적",
    color:
      "#FF8383                                                                                                                                      ",
  },
];
