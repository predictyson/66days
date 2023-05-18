export interface MainData {
  group: GroupData[];
  myGroup: MyGroupData[];
  todayToto: TodayTodoData[];
  userDetail: userDetailData;
}

export interface MyGroupData {
  challengeImagePath: string[];
  profileImagePath: string;
}

export interface TodayTodoData {
  myChallengeId: number | null;
  groupChallengeId: number | null;
  challengeName: string;
  startAt: string;
  todayStreak: boolean;
}
export interface userDetailData {
  animalDTO: animalDtoData;
  blogUrl: string;
  email: string;
  exp: number;
  githubUrl: string;
  nickname: string;
  point: number;
  profileImagePath: string;
  tierDTO: TierDtoData;
  userId: string;
}
export interface TierDtoData {
  imagePath: string;
  name: string;
  requiredExp: number;
  tierId: number;
  title: string;
}
export interface animalDtoData {
  animalId: number;
  animalName: string;
  imagePath: string;
}
export interface RankData {
  badgeRank: BadgeRankData[];
  expRank: ExpRankData[];
  myExpRank: number;
  myExp: number;
  myBadgeRank: number;
  myBadge: number;
}

export interface BadgeRankData {
  image: string;
  name: string;
  animal: string;
  tier: string;
  exp: number;
  badge: number;
}
export interface ExpRankData {
  image: string;
  name: string;
  animal: string;
  tier: string;
  exp: number;
  badge: number;
}

export interface GroupData {
  imagePath: string;
  name: string;
  badges: string[];
  groupId: number;
}
