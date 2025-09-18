import Logo from "../Logo/Logo";
import NavBar from "../NavBar/NavBar";
import styles from "./Header.module.css";

import { useOpenCarouselStore } from "../../../../store/openCarouselStore";
import { useFilterByCityStore } from "../../../../store/filterByCityStore";
import { useFilterStore } from "../../../../store/filterStore";
import { useIsLoggedStore } from "../../../../store/isLoggedStore";

const Header = () => {
	const { handleClose } = useOpenCarouselStore();
	const { setFilterCity, setPlaceholder, setFilterCity2 } = useFilterByCityStore();
	const {
		setFilter,
		setFilterDateSearch,
		setPlaceholderCalendar,
		setFilterDate,
		setCheckInDate,
		setCheckOutDate,
		setValueCalendar,
		setSearchFilter,
		setSearchByDate,
		setCleanerButtonClass,
	} = useFilterStore();
	const { setFlag } = useIsLoggedStore();

	const onClickButton: VoidFunction = () => {
		setPlaceholder("¿A dónde vamos?");
		setFilterCity("");
		handleClose();
		setFilter("");
		setFlag(false);
		setFilterCity2([]);
		setCheckOutDate("");
		setCheckInDate("");
		setValueCalendar("Check In - Check Out");
		setPlaceholderCalendar("Check In - Check Out");
		setFilterDateSearch([]);
		setFilterDate([]);
		setSearchFilter(false);
		setSearchByDate([]);
		setCleanerButtonClass("off");
	};

	return (
		<header
			className={styles.header}
			onClick={onClickButton}
		>
			<Logo />
			<NavBar />
		</header>
	);
};

export default Header;
