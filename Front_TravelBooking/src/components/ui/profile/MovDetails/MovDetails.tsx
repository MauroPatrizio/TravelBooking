import type { FC } from "react";
import styles from "./MovDetails.module.css";

interface IProps {
	id: number | string;
	propiedad: string;
	ubicacion: string;
	pais: string;
	checkIn: string;
	checkOut: string;
	hora: string;
}

const MovDetails: FC<IProps> = ({ id, propiedad, ubicacion, pais, checkIn, checkOut, hora }) => {
	return (
		<div className={styles.movements__details}>
			<div className={styles.movements__details_id}>{id}</div>
			<div className={styles.movements__details_name}>{propiedad}</div>
			<div className={styles.movements__location}>
				<div className={styles.movements__location}>{ubicacion},</div>
				<div className={styles.movements__country}>{pais}</div>
			</div>
			<div className={styles.movements__check}>{checkIn}</div>
			<div className={styles.movements__check}>{checkOut}</div>
			<div className={styles.movements__hour}>{hora}</div>
		</div>
	);
};

export default MovDetails;
