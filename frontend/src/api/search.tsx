import api from "./api";

export async function getSearchData(searchContent: string, filterBy: string) {
  try {
    const res = await api.get(
      `/api/v1/group/search/searchContent=${searchContent}/filterBy=${filterBy}`
    );
    if (res.status === 200) {
      console.log(res.data);
      return res.data;
    }

    throw new Error();
  } catch (error) {
    console.log("search get data err");
  }
}
