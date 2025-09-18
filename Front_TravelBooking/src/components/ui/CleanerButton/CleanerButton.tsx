import { useFilterStore } from "../../../store/filterStore";
import { useFilterByCityStore } from "../../../store/filterByCityStore";
import styles from "./CleanerButton.module.css";

const CleanerButton = () => {
	const { setFilterCity, setFilterCity2, setPlaceholder } = useFilterByCityStore();
	const {
		setFilterDateSearch,
		setPlaceholderCalendar,
		setFilterDate,
		setCheckInDate,
		setFilter,
		setCheckOutDate,
		setValueCalendar,
		setSearchFilter,
		setSearchByDate,
		cleanerButtonClass,
		setCleanerButtonClass,
	} = useFilterStore();

	const clearnerFilters: VoidFunction = () => {
		setCheckOutDate("");
		setCheckInDate("");
		setValueCalendar("Check In - Check Out");
		setPlaceholderCalendar("Check In - Check Out");
		setFilterDateSearch([]);
		setFilter("");
		setFilterDate([]);
		setFilterCity([]);
		setFilterCity2([]);
		setPlaceholder("¿A dónde vamos?");
		setSearchFilter(false);
		setSearchByDate([]);
		setCleanerButtonClass("off");
	};

	return (
		<div className={cleanerButtonClass === "on" ? styles.cleanerButton : styles.none}>
			<button onClick={clearnerFilters}>Limpiar filtros 🧹</button>
		</div>
	);
};

export default CleanerButton;
