import { ChallengeData, GroupData, MemberInfoData } from "./main";

export interface MyPageData {
  badges: ImageData[];
  result: string;
  memberInfo: MemberInfoData;
  challenge: ChallengeData[];
  streak: StreakData[];
  group: GroupData[];
}

export interface ImageData {
  image: string;
}

export interface StreakData {
  date: string;
  count: number;
}
