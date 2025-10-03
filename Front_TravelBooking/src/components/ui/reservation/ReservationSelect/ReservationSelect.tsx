import type { FC } from "react";
import Select, { type SingleValue } from "react-select";
import styles from "./ReservationTimeArrival.module.css";

interface Option {
	value: string;
	label: string;
}

interface IProps {
	handleChange: (option: SingleValue<Option>) => void;
}

const ReservationTimeArrival: FC<IProps> = ({ handleChange }) => {
	const times = [
		"11:00hs.",
		"12:00hs.",
		"13:00hs.",
		"14:00hs.",
		"15:00hs.",
		"16:00hs.",
		"17:00hs.",
		"18:00hs.",
		"19:00hs.",
		"20:00hs.",
	];

	const options: Option[] = times.map((time) => ({
		value: time,
		label: time,
	}));

	return (
		<div className={styles.reservationSelect}>
			<Select
				options={options}
				onChange={handleChange}
				name="time"
				inputId="reservationSelect"
				placeholder="Seleccionar hora de llegada"
				classNamePrefix="rs"
			/>
		</div>
	);
};

export default ReservationTimeArrival;
