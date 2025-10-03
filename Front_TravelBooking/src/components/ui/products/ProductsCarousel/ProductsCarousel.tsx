import type { FC } from "react";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import styles from "./ProductsCarousel.module.css";
import type { IProduct } from "../../../../types/IProduct";

interface IProps {
	productDetails: IProduct;
}

const ProductsCarousel: FC<IProps> = ({ productDetails }) => {
	const total = productDetails.imagen.length;

	const settings = {
		rows: 1,
		arrows: true,
		dots: true,
		autoplay: true,
		autoplaySpeed: 3000,
		customPaging: (i: number) => (
			<div>
				{i + 1}/{total}
			</div>
		),
	};

	return (
		<div className={styles.productsCarousel}>
			<Slider {...settings}>
				{productDetails.imagen.map((img, idx) => (
					<div
						key={img.id ?? idx}
						className={styles.products__img__carousel}
					>
						<h3>
							<img
								src={img.url}
								alt={img.alt ?? `img_${idx}`}
							/>
						</h3>
					</div>
				))}
			</Slider>
		</div>
	);
};

export default ProductsCarousel;
