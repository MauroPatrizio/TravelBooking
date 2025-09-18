import { create } from "zustand";
import { useFilterStore } from "./filterStore";

interface IOpenCarouselStore {
	openCarousel: boolean;
	handleCarousel: () => void;
	handleClose: () => void;
}

export const useOpenCarouselStore = create<IOpenCarouselStore>((set) => ({
	openCarousel: false,

	handleCarousel: () => {
		set((state) => ({ openCarousel: !state.openCarousel }));

		const { setAnimationSuccessful } = useFilterStore.getState();
		setAnimationSuccessful(false);
	},

	handleClose: () => set({ openCarousel: false }),
}));
