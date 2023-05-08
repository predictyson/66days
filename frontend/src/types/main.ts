export interface MainIData {
  result: string;
  memberInfo: MemberInfoData;
  challenge: ChallengeData[];
  rank: RankData;
  group: GroupData[];
}

export interface MemberInfoData {
  tier: string;
  email: string;
  exp: number;
  point: number;
  image: string;
  animal: string;
}
export interface ChallengeData {
  image: string;
  name: string;
  participants: string[];
  startDate: string;
  status: boolean;
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
  image: string;
  name: string;
  badges: string[];
  type: string;
}
