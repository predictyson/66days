import { create } from "zustand";
import { produce } from "immer";

interface authSlice {
  user: UserType | null;
  login: (user: UserType) => void;
  logout: () => void;
}

export const useAuthStore = create<authSlice>((set) => ({
  user: null,
  login: (user: UserType) => {
    set(
      produce((state) => {
        state.user = user;
      })
    );
  },
  logout: () => {
    produce((state) => {
      state.user = null;
    });
  },
}));
