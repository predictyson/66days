import api from "./api";

async function fetchGroupPageData(id = 1) {
  try {
    const res = await api.get(`/api/v1/groups/${id}`);
    if (res.status === 200) {
      return res.data;
    }

    throw new Error();
  } catch (error) {
    // login error -> login  page
    // unauthorized -> unauthorized error
  }
}

// 그룹 관리 or 그룹원 보기 모달의 그룹원 리스트 데이터 fetch
async function fetchGroupMembers(id = 1) {
  try {
    const res = await api.get(`/api/v1/group/${id}/manage/members`);
    if (res.status === 200) {
      return res.data;
    }

    throw new Error();
  } catch (error) {
    // login error -> login  page
    // unauthorized -> unauthorized error
  }
}

// 그룹 가입 신청원 리스트 데이터 fetch
async function fetchAppliedMembers(id = 1) {
  try {
    const res = await api.get(`/api/v1/group/${id}/manage/apply`);
    if (res.status === 200) {
      return res.data;
    }

    throw new Error();
  } catch (error) {
    // login error -> login  page
    // unauthorized -> unauthorized error
  }
}

async function fetchGroupBadges(id = 1) {
  try {
    const res = await api.get(`/api/v1/badge/list/${id}`);
    if (res.status === 200) {
      return res.data;
    }

    throw new Error();
  } catch (error) {
    // login error -> login  page
    // unauthorized -> unauthorized error
  }
}

async function fetchBoardListByPage(groupId: number = 1, page: number) {
  try {
    const res = await api.get(
      `/api/v2/article/${groupId}/articles?offset=${page}`
    );
    if (res.status === 200) {
      return res.data;
    }

    throw new Error();
  } catch (error) {
    // login error -> login  page
    // unauthorized -> unauthorized error
  }
}

async function fetchBoardData(groupId: number, articleId: number) {
  try {
    const res = await api.get(`/api/v2/article/${groupId}/${articleId}`);
    if (res.status === 200) {
      return res.data;
    }

    throw new Error();
  } catch (error) {
    // login error -> login  page
    // unauthorized -> unauthorized error
  }
}

async function fetchCommentData(articleId: number = 2, page: number) {
  try {
    const res = await api.get(
      `/api/v2/article/${articleId}/comments?offset=${page}`
    );
    if (res.status === 200) {
      return res.data;
    }

    throw new Error();
  } catch (error) {
    // login error -> login  page
    // unauthorized -> unauthorized error
  }
}

export {
  fetchGroupPageData,
  fetchGroupMembers,
  fetchAppliedMembers,
  fetchGroupBadges,
  fetchBoardListByPage,
  fetchBoardData,
  fetchCommentData,
};
