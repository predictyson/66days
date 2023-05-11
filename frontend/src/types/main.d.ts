interface MainData {
  result: string;
  memberInfo: MemberInfoData;
  challenge: ChallengeData[];
  rank: RankData;
  group: GroupData[];
}

// FIXME: deprecated; to be replaced with interface User
interface MemberInfoData {
  tier: string;
  email: string;
  exp: number;
  point: number;
  image: string;
  animal: string;
}
interface ChallengeData {
  image: string;
  name: string;
  participants: string[];
  startDate: string;
  status: boolean;
}

interface RankData {
  badgeRank: BadgeRankData[];
  expRank: ExpRankData[];
  myExpRank: number;
  myExp: number;
  myBadgeRank: number;
  myBadge: number;
}

interface BadgeRankData {
  image: string;
  name: string;
  animal: string;
  tier: string;
  exp: number;
  badge: number;
}
interface ExpRankData {
  image: string;
  name: string;
  animal: string;
  tier: string;
  exp: number;
  badge: number;
}

interface GroupData {
  image: string;
  name: string;
  badges: string[];
  type: string;
}

///

interface User {
  tierImage: string; // 티어 이미지 경로
  tierTitle: string; // 티어 형용사
  id: string; // 유저 아이디
  nickname: string; // 유저 닉네임
  email: string; // 유저 이메일
  exp: number; // 유저 경험치
  point: number; // 유저 포인트
  image: string; // 유저 프로필 이미지 경로
  animal: {
    id: number; // 동물 아이디
    name: string; // 동물 이름
    image: string; // 동물 이미지 경로
  };
  mygroup: {
    id: number;
    name: string;
    image: string;
    badges: string[];
  };
  groups: {
    id: number; // 그룹 아이디
    name: string; // 그룹 이름
    image: string; // 그룹 이미지 경로
    badges: string[]; // 그룹 뱃지 이미지 경로
  }[];
}
