import { create } from "zustand";
import { produce } from "immer";

interface authSlice {
  user: UserType | null;
  getToken: () => string | null;
  setToken: (token: string | null) => void;
  removeToken: () => void;
  setUser: (user: UserType) => void;
  resetUser: () => void;
  isLoggedIn: () => boolean;
}

export const useAuthStore = create<authSlice>((set, get) => ({
  user: null,
  getToken: () => localStorage.getItem("token"),
  setToken: (token: string | null) =>
    token && localStorage.setItem("token", token),
  removeToken: () => localStorage.removeItem("token"),
  setUser: (user: UserType) => {
    set(
      produce((state) => {
        state.user = user;
      })
    );
  },
  resetUser: () => {
    produce((state) => {
      state.user = null;
    });
  },
  isLoggedIn: () => get().getToken() !== null,
}));
