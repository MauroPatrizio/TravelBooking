import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faLinkedin, faFacebook, faTwitter, faInstagram } from "@fortawesome/free-brands-svg-icons";
import {
	faCircleCheck,
	faLocationDot,
	faChevronLeft,
	faSquareParking,
	faFan,
	faDog,
	faTableTennisPaddleBall,
	faTv,
	faBars,
	faStar as faStarSolid,
	faStarHalfStroke,
	faCalendarDay,
	faSnowflake,
	faPaw,
	faCar,
	faWifi,
	faEye,
	faEyeSlash,
	faFireBurner,
	faCircleExclamation,
	faXmark,
} from "@fortawesome/free-solid-svg-icons";
import { faHeart, faStar as faStarRegular } from "@fortawesome/free-regular-svg-icons";
import styles from "./fontAwesomeIcons.module.css";

export const imgHeart = <FontAwesomeIcon icon={faHeart} />;
export const imgWifi = <FontAwesomeIcon icon={faWifi} />;
export const imgAirCond = <FontAwesomeIcon icon={faFan} />;
export const imgParking = <FontAwesomeIcon icon={faSquareParking} />;
export const imgKitchen = <FontAwesomeIcon icon={faFireBurner} />;
export const imgPet = <FontAwesomeIcon icon={faDog} />;
export const imgPool = <FontAwesomeIcon icon={faTableTennisPaddleBall} />;
export const imgTv = <FontAwesomeIcon icon={faTv} />;
export const imgCar = <FontAwesomeIcon icon={faCar} />;
export const imgOven = <FontAwesomeIcon icon={faFireBurner} />;
export const imgSnow = <FontAwesomeIcon icon={faSnowflake} />;
export const imgPaw = <FontAwesomeIcon icon={faPaw} />;

export const warnIcon = (
	<FontAwesomeIcon
		className={styles.warnIcon}
		icon={faCircleExclamation}
	/>
);

export const successIcon = <FontAwesomeIcon icon={faCircleCheck} />;

export const closeIcon = <FontAwesomeIcon icon={faXmark} />;

export const imgLocation = (
	<FontAwesomeIcon
		className={styles.iconLocation}
		icon={faLocationDot}
	/>
);

export const imgLocationP = <FontAwesomeIcon icon={faLocationDot} />;

export const imgLeft = <FontAwesomeIcon icon={faChevronLeft} />;

export const imgStar = <FontAwesomeIcon icon={faStarSolid} />;

export const imgStarHalf = <FontAwesomeIcon icon={faStarHalfStroke} />;

export const imgStarRegular = <FontAwesomeIcon icon={faStarRegular} />;

export const imgLinkedin = (
	<FontAwesomeIcon
		className={styles.iconNet}
		icon={faLinkedin}
	/>
);

export const imgFacebook = (
	<FontAwesomeIcon
		className={styles.iconNet}
		icon={faFacebook}
	/>
);

export const imgTwitter = (
	<FontAwesomeIcon
		className={styles.iconNet}
		icon={faTwitter}
	/>
);

export const imgInstagram = (
	<FontAwesomeIcon
		className={styles.iconNet}
		icon={faInstagram}
	/>
);

export const imgCalendar = (
	<FontAwesomeIcon
		className={styles.iconCalendar}
		icon={faCalendarDay}
	/>
);

export const imgBars = (
	<FontAwesomeIcon
		className={styles.hamburguer}
		icon={faBars}
	/>
);

export const imgEye = <FontAwesomeIcon icon={faEye} />;

export const imgEyeSlash = <FontAwesomeIcon icon={faEyeSlash} />;

export const imgLocation2 = (
	<svg
		fill="#6B6B6B"
		height="24"
		width="24"
		viewBox="0 0 24 24"
		aria-hidden="true"
	>
		<path d="M15 8.25a3 3 0 1 1-6 0 3 3 0 0 1 6 0zm1.5 0a4.5 4.5 0 1 0-9 0 4.5 4.5 0 0 0 9 0zM12 1.5a6.75 6.75 0 0 1 6.75 6.75c0 2.537-3.537 9.406-6.75 14.25-3.214-4.844-6.75-11.713-6.75-14.25A6.75 6.75 0 0 1 12 1.5zM12 0a8.25 8.25 0 0 0-8.25 8.25c0 2.965 3.594 9.945 7 15.08a1.5 1.5 0 0 0 2.5 0c3.406-5.135 7-12.115 7-15.08A8.25 8.25 0 0 0 12 0z"></path>
	</svg>
);
