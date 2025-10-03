import type { FC } from "react";
import styles from "./ProductsPolitics.module.css";

const ProductsPolitics: FC = () => {
	return (
		<div className={styles.productsPolitics}>
			<h2 className={styles.productsPolitics__title}>¿Qué tenés que saber?</h2>
			<div className={styles.line}></div>
			<div className={styles.productsPolitics__details}>
				<div className={styles.productsPolitics__details__sections}>
					<h3>Normas de la casa</h3>
					<div>
						<p>Check-out: 10:00</p>
						<p>No se permiten fiestas</p>
						<p>No fumar</p>
					</div>
				</div>
				<div className={styles.productsPolitics__details__sections}>
					<h3>Salud y seguridad</h3>
					<div>
						<p>
							Se aplican las pautas de distanciamiento social y otras formas
							relacionadas con el coronavirus
						</p>
						<p>Detector de humo</p>
						<p>Depósito de seguridad</p>
					</div>
				</div>
				<div className={styles.productsPolitics__details__sections}>
					<h3>Política de cancelacíon</h3>
					<div>
						<p>
							Agregá las fechas de tu viaje para obtener los detalles de esta estadía.
						</p>
					</div>
				</div>
			</div>
		</div>
	);
};

export default ProductsPolitics;
