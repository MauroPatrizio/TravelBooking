import type { FC } from "react";
import type { IProduct } from "../../../types/IProduct";
import { imgLocationP } from "../../../styleAux/fontAwesomeIcons";

interface Props {
	product: IProduct;
}

const ReservationLocation: FC<Props> = ({ product }) => {
	return (
		<div className="reservationD__containe__city">
			<span>{imgLocationP}</span>
			<p>
				{product.ciudad.city}, {product.ciudad.pais.pais}, a {Number(product.id) * 50} m del
				centro.
			</p>
		</div>
	);
};

export default ReservationLocation;
