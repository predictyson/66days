import { create } from "zustand";
import { produce } from "immer";

interface authSlice {
  user: UserType | null;
  setUser: (user: UserType) => void;
  resetUser: () => void;
  isLoggedIn: () => boolean;
}

export const useAuthStore = create<authSlice>((set, get) => ({
  user: null,
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
  isLoggedIn: () => get().user !== null,
}));
