import { create } from "zustand";

interface IFilterStore {
	filter: string;
	setFilter: (value: string) => void;
	handleFilter: VoidFunction;

	filterDate: string[];
	setFilterDate: (dates: string[]) => void;

	filterDateSearch: string[];
	setFilterDateSearch: (dates: string[]) => void;

	placeholderCalendar: string;
	setPlaceholderCalendar: (value: string) => void;

	valueCalendar: string;
	setValueCalendar: (value: string) => void;

	searchByDate: string[];
	setSearchByDate: (dates: string[]) => void;

	checkInDate: Date | null;
	setCheckInDate: (date: Date | null) => void;

	checkOutDate: Date | null;
	setCheckOutDate: (date: Date | null) => void;

	searchFilter: boolean;
	setSearchFilter: (value: boolean) => void;

	animationSuccessful: boolean;
	setAnimationSuccessful: (value: boolean) => void;

	favoritos: string[];
	setFavoritos: (favorites: string[]) => void;

	cleanerButtonClass: string;
	setCleanerButtonClass: (value: string) => void;

	containsDisabledDates: boolean;
	setContainsDisabledDates: (value: boolean) => void;

	getCheckInISO?: () => string | null;
	getCheckOutISO?: () => string | null;
}

export const useFilterStore = create<IFilterStore>((set, get) => ({
	filter: "",
	setFilter: (value) => set({ filter: value }),
	handleFilter: () =>
		set((state) => ({
			filter: state.filter === "" ? "on" : "",
		})),

	filterDate: [],
	setFilterDate: (dates) => set({ filterDate: dates }),

	filterDateSearch: [],
	setFilterDateSearch: (dates) => set({ filterDateSearch: dates }),

	placeholderCalendar: "Check in - Check out",
	setPlaceholderCalendar: (value) => set({ placeholderCalendar: value }),

	valueCalendar: "Check In - Check Out",
	setValueCalendar: (value) => set({ valueCalendar: value }),

	searchByDate: [],
	setSearchByDate: (dates) => set({ searchByDate: dates }),

	checkInDate: null,
	setCheckInDate: (date) => set({ checkInDate: date }),

	checkOutDate: null,
	setCheckOutDate: (date) => set({ checkOutDate: date }),

	searchFilter: false,
	setSearchFilter: (value) => set({ searchFilter: value }),

	animationSuccessful: false,
	setAnimationSuccessful: (value) => set({ animationSuccessful: value }),

	favoritos: [],
	setFavoritos: (favorites) => set({ favoritos: favorites }),

	cleanerButtonClass: "off",
	setCleanerButtonClass: (value) => set({ cleanerButtonClass: value }),

	containsDisabledDates: false,
	setContainsDisabledDates: (value) => set({ containsDisabledDates: value }),

	getCheckInISO: () => {
		const d = get().checkInDate;
		return d ? d.toISOString().slice(0, 10) : null;
	},
	getCheckOutISO: () => {
		const d = get().checkOutDate;
		return d ? d.toISOString().slice(0, 10) : null;
	},
}));
