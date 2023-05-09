import instance from "./api";

export async function getMyPageInfo() {
  return await instance.get(`/me`);
}
