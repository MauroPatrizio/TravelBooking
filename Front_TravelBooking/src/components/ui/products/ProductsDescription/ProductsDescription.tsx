import type { FC } from "react";
import styles from "./ProductsDescription.module.css";
import type { IProduct } from "../../../../types/IProduct";

interface IProps {
	productDetails: IProduct;
}

const ProductsDescription: FC<IProps> = ({ productDetails }) => {
	return (
		<div className={styles.productsDescription}>
			<h3>Alojate en el corazon de {productDetails.ciudad.city}</h3>
			<p>{productDetails.description}</p>
		</div>
	);
};

export default ProductsDescription;
