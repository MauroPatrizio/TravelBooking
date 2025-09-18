import { create } from "zustand";

interface IOpenToggleStore {
	openNav: boolean;
	handleToggle: () => void;
}

export const useOpenToggleStore = create<IOpenToggleStore>((set) => ({
	openNav: false,
	handleToggle: () => set((state) => ({ openNav: !state.openNav })),
}));