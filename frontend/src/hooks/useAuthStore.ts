import { create } from "zustand";
import { produce } from "immer";
import { expireUserToken, passKakaoCode, fetchKakaoURL } from "../api/auth";

interface authSlice {
  user: UserType | null;
  loginByKakao: () => void;
  authenticateByKakao: () => void;
  logout: () => void;
  isLoggedIn: () => boolean;
}

export const useAuthStore = create<authSlice>((set, get) => ({
  user: null,
  loginByKakao: async () => {
    const resData = await fetchKakaoURL();
    window.location = resData.redirectUrl;
  },
  authenticateByKakao: async () => {
    const user = await passKakaoCode();
    set(
      produce((state) => {
        state.user = user;
      })
    );
    (window as Window).location = "/";
  },
  logout: () => {
    produce((state) => {
      state.user = null;
    });
    expireUserToken();
  },
  isLoggedIn: () => get().user !== null,
}));
