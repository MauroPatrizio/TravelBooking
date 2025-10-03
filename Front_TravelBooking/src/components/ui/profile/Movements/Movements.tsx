import type { FC } from "react";
import { useEffect, useState } from "react";
import axios from "axios";
import styles from "./Movements.module.css";

import type { IReservationItem } from "../../../../types/IReservationItem";
import MovDetails from "../MovDetails/MovDetails";

const API_BASE = import.meta.env.VITE_API_BASE_URL as string;

const Movements: FC = () => {
	const [reservasList, setReservasList] = useState<IReservationItem[]>([]);

	const getReservasById: VoidFunction = () => {
		axios
			.get<IReservationItem[]>(`${API_BASE}reservas/usuarios/${localStorage.id}`)
			.then((response) => setReservasList(response.data))
			.catch(() => {});
	};

	useEffect(() => {
		getReservasById();
	}, []);

	return (
		<div className={styles.movements}>
			<div className={styles.movements__title}>
				Reservas <span>realizadas</span>
			</div>
			<div className={styles.movements__title2}>
				A continuación podrás ver el detalle de tus reservas.
			</div>

			<div className={styles.movements__type}>
				<div className={styles.movements__details_id}>ID</div>
				<div className={styles.movements__details_name}>Alojamiento</div>
				<div className={styles.movements__location}>Ubicación</div>
				<div className={styles.movements__check}>Check-in</div>
				<div className={styles.movements__check}>Check-out</div>
				<div className={styles.movements__hour}>Hora</div>
			</div>

			{reservasList.length === 0 ? (
				<div className={styles.movements__null}>Aún no tenes reservas realizadas.</div>
			) : (
				reservasList.map((r) => (
					<MovDetails
						key={r.id}
						id={r.id}
						propiedad={r.producto.name}
						ubicacion={r.producto.ciudad.city}
						pais={r.producto.ciudad.pais.pais}
						checkIn={r.fechaInicio}
						checkOut={r.fechaFin}
						hora={r.horaComienzo}
					/>
				))
			)}
		</div>
	);
};

export default Movements;
