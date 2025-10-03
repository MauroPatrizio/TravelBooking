import type { FC } from "react";
import { Link } from "react-router-dom";
import type { IProduct } from "../../../../types/IProduct";
import type { IReservation } from "../../../../types/IReservation";
import styles from "./ProductsCalendar.module.css";
import ProductsDatePicker from "../ProductsDatePicker/ProductsDatePicker";

interface IProps {
	productDetails: IProduct & { reservations?: IReservation[] };
}

const ProductsCalendar: FC<IProps> = ({ productDetails }) => {
	const option = false;
	return (
		<div className={styles.productsCalendar}>
			<h2 className={styles.productsCalendar__title}>Fechas Disponibles</h2>
			<div className={styles.productsCalendar__blockCalendar}>
				<div className={styles.productsCalendar__calendar}>
					<ProductsDatePicker
						productDetails={productDetails}
						pickerDisabled={option}
					/>
				</div>
				<div className={styles.productsCalendar__reservation}>
					<p>Agregá tus fechas de viaje para obtener precios exactos</p>
					<Link
						to={`/alojamientos/${productDetails.id}/${productDetails.name.replace(
							/[+ ]|%20/g,
							"-"
						)}/reservar`}
					>
						<button>Iniciar reserva</button>
					</Link>
				</div>
			</div>
		</div>
	);
};

export default ProductsCalendar;
