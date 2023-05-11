export interface SearchData {
  result: string;
  groupList: SearchGroupData[];
}

export interface SearchGroupData {
  ownerImage: string;
  ownerName: string;
  image: string | null;
  name: string;
  categories: Array<"알고리즘" | "CS" | "블로깅" | "개발서적" | "강의">;
  description: string;
  memberCounts: number;
  maxMemberCounts: number;
  animal: string;
}
