import { userDetailData } from "./main";
export interface MyPageData {
  badges: BadgeData[];
  userDetail: userDetailData;
  challenges: MyChallengeData[];
  // streak: StreakData[];
  groups: MyGroupData[];
}

export interface BadgeData {
  challengeId: number;
  imagePath: string;
  number: number;
}

// export interface MyPageMemberData {
//   image: string;
//   nickname: stirng;
//   tier: string;
//   email: string;
//   currentExp: number;
//   nextTierExp: number;
//   point: number;
// }

export interface MyChallengeData {
  category: string;
  myChallengeId: number;
  name: string;
  startDate: string;
}
export interface StreakData {
  date: string;
  count: number;
}

export interface MyGroupData {
  challenges: string[];
  groupId: number;
  name: string;
}
