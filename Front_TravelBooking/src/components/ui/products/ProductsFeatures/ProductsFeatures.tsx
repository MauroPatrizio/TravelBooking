import type { FC } from "react";
import styles from "./ProductsFeatures.module.css";
import type { IProduct } from "../../../../types/IProduct";
import {
	imgWifi,
	imgAirCond,
	imgParking,
	imgKitchen,
	imgPet,
	imgPool,
	imgTv,
} from "../../../../styleAux/fontAwesomeIcons";

interface IProps {
	productDetails: IProduct;
}

const ProductsFeatures: FC<IProps> = ({ productDetails }) => {
	const f = productDetails.caracteristica;

	const items = [
		{ ok: f.airConditioning, icon: imgAirCond, label: "Aire acondicionado" },
		{ ok: f.freeParking, icon: imgParking, label: "Estacionamiento" },
		{ ok: f.kitchen, icon: imgKitchen, label: "Cocina" },
		{ ok: f.petsAllowed, icon: imgPet, label: "Animales permitidos" },
		{ ok: f.pool, icon: imgPool, label: "Pileta" },
		{ ok: f.tv, icon: imgTv, label: "Smart TV" },
		{ ok: f.wifi, icon: imgWifi, label: "Wifi" },
	];

	return (
		<div className={styles.productsfeatures}>
			<h3 className={styles.productsfeatures__title}>¿Qué ofrece este lugar?</h3>
			<div className={styles.productsfeatures__line}></div>
			<div className={styles.productsfeatures__icons}>
				{items
					.filter((it) => it.ok)
					.map((it, idx) => (
						<div key={idx}>
							<p>
								<span>{it.icon}</span>
								{it.label}
							</p>
						</div>
					))}
			</div>
		</div>
	);
};

export default ProductsFeatures;
