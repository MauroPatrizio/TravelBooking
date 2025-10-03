import type { FC } from "react";
import styles from "./ProductsLocation.module.css";
import type { IProduct } from "../../../../types/IProduct";
import Map from "../ProductsMap/ProductsMap";

interface IProps {
	productDetails: IProduct;
}

const ProductsLocation: FC<IProps> = ({ productDetails }) => {
	return (
		<div className={styles.productsLocation}>
			<h3 className={styles.productsLocation__title}>¿Dónde vas a estar?</h3>
			<div className={styles.line} />
			<h4 className={styles.productsLocation__subtitle}>
				{productDetails.ciudad.city}, Argentina
			</h4>
			<div className={styles.productsLocation__imgContainer}>
				<Map {...productDetails} />
			</div>
		</div>
	);
};

export default ProductsLocation;
