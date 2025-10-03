import type { FC } from "react";
import { Link } from "react-router-dom";
import styles from "./ProductsHeader.module.css";
import type { IProduct } from "../../../../types/IProduct";
import { imgLeft } from "../../../../styleAux/fontAwesomeIcons";

interface IProps {
	productDetails: IProduct;
	linkProps: string;
}

const ProductsHeaderTop: FC<IProps> = ({ productDetails, linkProps }) => {
	return (
		<div className={styles.productsHeader}>
			<div className={styles.productsHeader__blockLeft}>
				<h2>{productDetails.categoria.title}</h2>
				<h3>{productDetails.name}</h3>
			</div>
			<div className={styles.productsHeader__blockRight}>
				<Link to={linkProps}>
					<span className={styles.productsHeader__blockRight__button}>{imgLeft}</span>
				</Link>
			</div>
		</div>
	);
};

export default ProductsHeaderTop;
