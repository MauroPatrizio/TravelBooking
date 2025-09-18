import { create } from "zustand";

interface IFilterByCityStore {
	filterCity: string[];
	setFilterCity: (city: string[] | string) => void;

	filterCity2: string[];
	setFilterCity2: (cities: string[]) => void;

	placeholder: string;
	setPlaceholder: (text: string) => void;

	fav: string[];
	setFav: (favorites: string[]) => void;
}

export const useFilterByCityStore = create<IFilterByCityStore>((set) => ({
	filterCity: [],
	setFilterCity: (city) =>
		set(() => ({
			filterCity: Array.isArray(city) ? city : [city],
		})),

	filterCity2: [],
	setFilterCity2: (cities) => set(() => ({ filterCity2: cities })),

	placeholder: "¿A dónde vamos?",
	setPlaceholder: (text) => set(() => ({ placeholder: text })),

	fav: ["Piponeta Hostel"],
	setFav: (favorites) => set(() => ({ fav: favorites })),
}));
