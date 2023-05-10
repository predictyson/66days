export interface MyPageData {
  badges: ImageData[];
  result: string;
  memberInfo: MyPageMemberData;
  challenge: MyChallengeData[];
  streak: StreakData[];
  group: GroupData[];
}

export interface ImageData {
  image: string;
}

export interface MyPageMemberData {
  image: string;
  nickname: stirng;
  tier: string;
  email: string;
  currentExp: number;
  nextTierExp: number;
  point: number;
}

export interface MyChallengeData {
  category: string;
  name: string;
  startDate: string;
}
export interface StreakData {
  date: string;
  count: number;
}
