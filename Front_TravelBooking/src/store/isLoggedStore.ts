import { create } from "zustand";

interface IIsLoggedStore {
	isLogged: boolean | string;
	setIsLogged: (value: boolean | string) => void;

	flag: boolean;
	setFlag: (value: boolean) => void;
}

export const useIsLoggedStore = create<IIsLoggedStore>((set) => ({
	isLogged: localStorage.getItem("login") || false,
	setIsLogged: (value) => set({ isLogged: value }),

	flag: false,
	setFlag: (value) => set({ flag: value }),
}));
