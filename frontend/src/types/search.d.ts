export interface SearchData {
  result: string;
  groupList: SearchGroupData[];
}

export interface SearchGroupData {
  ownerImage: string;
  ownerName: string;
  image: string | null;
  name: string;
  categories: string[];
  description: string;
  memberCounts: number;
  maxMemberCounts: number;
  animal: string;
}
