import { userDetailData } from "./main";
export interface MyPageData {
  badges: BadgeData[];
  userDetail: MyUserData;
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

export interface MyUserData {
  animalId: number | null;
  blogUrl: number | null;
  email: string;
  exp: number;
  githubUrl: string | null;
  nickname: string;
  point: number;
  profileImagePath: string;
  tierId: number | null;
  userId: number;
}
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
