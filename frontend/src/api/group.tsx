import api from "./api";

async function fetchGroupPageData(id = 1) {
  try {
    const res = await api.get(`/groups/${id}`);
    if (res.status === 200) {
      return res.data;
    }

    throw new Error();
  } catch (error) {
    // login error -> login  page
    // unauthorized -> unauthorized error
  }
}

export { fetchGroupPageData };
