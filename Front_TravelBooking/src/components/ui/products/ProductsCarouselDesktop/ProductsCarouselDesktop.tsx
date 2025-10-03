import type { FC } from "react";
import { useState } from "react";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import styles from "./ProductsCarouselDesktop.module.css";
import type { IProduct } from "../../../../types/IProduct";
import { useOpenCarouselStore } from "../../../../store/openCarouselStore";

interface IProps {
	productDetails: IProduct;
}

const ProductsCarouselDesktop: FC<IProps> = ({ productDetails }) => {
	const { openCarousel, handleCarousel } = useOpenCarouselStore();

	const [nav1, setNav1] = useState<Slider | null>(null);
	const [nav2, setNav2] = useState<Slider | null>(null);

	const total = productDetails.imagen.length;

	const handleClose: VoidFunction = () => handleCarousel();

	return (
		<div
			className={openCarousel ? styles.productsCarouselDesktop : styles.closeCarousel}
			onClick={handleClose}
		>
			<div
				className={styles.modalContent}
				onClick={(e) => e.stopPropagation()}
			>
				<span
					className={styles.productsCarouselDesktop__closeCarousel}
					onClick={handleClose}
				>
					X
				</span>

				<Slider
					asNavFor={nav2 ?? undefined}
					ref={(slider) => setNav1(slider)}
					dots
					customPaging={(i: number) => (
						<div className={styles.carouselDesktopDots}>
							{i + 1}/{total}
						</div>
					)}
				>
					{productDetails.imagen.map((img, idx) => (
						<div key={img.id ?? idx}>
							<h3 className={styles.carousel__img__top}>
								<img
									className={styles.carousel__img__top}
									src={img.url}
									alt={img.alt ?? `img_${idx}`}
								/>
							</h3>
						</div>
					))}
				</Slider>

				<h4 className={styles.secondTitle}>Second Slider</h4>

				<Slider
					className={styles.secondSlider}
					asNavFor={nav1 ?? undefined}
					ref={(slider) => setNav2(slider)}
					slidesToShow={4}
					swipeToSlide
					focusOnSelect
				>
					{productDetails.imagen.map((img, idx) => (
						<div key={`thumb_${img.id ?? idx}`}>
							<h3 className={styles.carousel__img__bot}>
								<img
									src={img.url}
									alt={img.alt ?? `thumb_${idx}`}
								/>
							</h3>
						</div>
					))}
				</Slider>
			</div>
		</div>
	);
};

export default ProductsCarouselDesktop;
