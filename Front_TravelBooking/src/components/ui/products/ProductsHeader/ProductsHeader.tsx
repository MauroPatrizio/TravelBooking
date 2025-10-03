import type { FC } from "react";
import styles from "./ProductsHeader.module.css";
import type { IProduct } from "../../../../types/IProduct";
import { imgLocationP, imgStar, imgStarHalf } from "../../../../styleAux/fontAwesomeIcons";
import { faStar } from "@fortawesome/free-regular-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import ProductsHeaderTop from "./ProductsHeaderTop";

interface IProps {
	productDetails: IProduct;
}

const ProductsHeader: FC<IProps> = ({ productDetails }) => {
	const distance = Number(productDetails.id) * 50 || 0;

	return (
		<>
			<ProductsHeaderTop
				productDetails={productDetails}
				linkProps={"/"}
			/>
			<div className={styles.productsHeaderdetails}>
				<div className={styles.productsHeaderdetails__firtsblock}>
					<div className={styles.productsHeaderdetails__firtsblock__icon}>
						<span>{imgLocationP}</span>
					</div>
					<div className={styles.productsHeaderdetails__firtsblock__text}>
						<p>{productDetails.ciudad.city}, Argentina</p>
						<p className={styles.productsHeaderdetails__firtsblock__distance}>
							A {distance} m del centro
						</p>
					</div>
				</div>

				<div className={styles.productsHeaderdetails__secondblock}>
					<div>
						<p>Muy Bueno</p>
						<div className={styles.productsHeaderdetails__secondblock__stars}>
							<span>{imgStar}</span>
							<span>{imgStar}</span>
							<span>{imgStar}</span>
							<span>{imgStarHalf}</span>
							<span>
								<FontAwesomeIcon icon={faStar} />
							</span>
						</div>
					</div>
					<div className={styles.productsHeaderdetails__secondblock__number}>
						<div>8</div>
					</div>
				</div>
			</div>
		</>
	);
};

export default ProductsHeader;
