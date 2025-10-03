import type { FC } from "react";
import { useEffect } from "react";
import styles from "./ProductsImg.module.css";
import { BsShare, BsHeartFill } from "react-icons/bs";
import { imgHeart } from "../../../../styleAux/fontAwesomeIcons";
import { useOpenCarouselStore } from "../../../../store/openCarouselStore";
import { useFilterStore } from "../../../../store/filterStore";
import { useLocation } from "react-router-dom";
import SocialNetShare from "@/components/socialNetShare/SocialNetShare";
import axios from "axios";
import type { IProduct } from "../../../../types/IProduct";
import ProductsCarouselDesktop from "../ProductsCarouselDesktop/ProductsCarouselDesktop";
import ProductsCarousel from "../ProductsCarousel/ProductsCarousel";

interface IProps {
	productDetails: IProduct;
}

const API_BASE = import.meta.env.VITE_API_BASE_URL as string;
const EP_GET_FAV_BY_USER = import.meta.env.VITE_API_GET_FAV_BY_USER as string;

const ProductsImg: FC<IProps> = ({ productDetails }) => {
	const { openCarousel, handleCarousel } = useOpenCarouselStore();
	const { setFavoritos, favoritos } = useFilterStore();
	const location = useLocation();

	const tokenRaw = localStorage.getItem("userToken");
	const token: string | null = tokenRaw ? JSON.parse(tokenRaw) : null;

	const shareUrl = `${window.location.origin}${location.pathname}`;
	const authHeader = token ? { Authorization: `Bearer ${token}` } : undefined;

	const dataPost = {
		producto: { id: productDetails.id },
		usuario: { id: localStorage.id },
	};

	const postFav: VoidFunction = () => {
		axios
			.post(`${API_BASE}favoritos/agregar`, dataPost, {
				headers: { "Content-Type": "application/json", ...(authHeader ?? {}) },
			})
			.then(() => getfavById())
			.catch(() => {});
	};

	const pushearFav = (arr: Array<{ producto: { id: string } }>): void => {
		const initialState: string[] = [];
		for (const e of arr) initialState.push(e.producto.id);
		setFavoritos(initialState);
	};

	const getfavById: VoidFunction = () => {
		axios
			.get(`${API_BASE}${EP_GET_FAV_BY_USER}${localStorage.id}`)
			.then((r) => pushearFav(r.data))
			.catch(() => {});
	};

	const develteFav = (idProducto: string): void => {
		axios
			.delete(`${API_BASE}favoritos/${idProducto}/${localStorage.id}`, {
				headers: authHeader,
			})
			.then(() => getfavById())
			.catch(() => {});
	};

	const quitFav = (idProducto: string): void => {
		develteFav(idProducto);
	};

	const addFav: VoidFunction = () => {
		postFav();
	};

	useEffect(() => {
		getfavById();
	}, []);

	const handleOpenOverlay: VoidFunction = () => handleCarousel();

	const images = productDetails.imagen.slice(0, 5);

	return (
		<>
			<div
				onClick={handleOpenOverlay}
				className={
					openCarousel
						? styles.productsImg_carousel__desktop
						: styles.productsImg_carousel
				}
			>
				<ProductsCarouselDesktop productDetails={productDetails} />
			</div>

			<div className={styles.productsImg}>
				<div className={styles.productsImg__icons}>
					<span className={styles.products__icons__share}>
						<span>
							<BsShare />
						</span>
						<span className={styles.socialNetShare}>
							<SocialNetShare
								title={productDetails.name}
								url={shareUrl}
							/>
						</span>
					</span>

					{favoritos.includes(productDetails.id) && localStorage.login != null ? (
						<span
							onClick={() => quitFav(productDetails.id)}
							className={styles.products__icons__imgHeart__like}
						>
							<BsHeartFill />
						</span>
					) : (
						<span
							onClick={addFav}
							className={styles.products__icons__imgHeart}
						>
							{imgHeart}
						</span>
					)}
				</div>

				<div className={styles.productsImg_carousel}>
					<ProductsCarousel productDetails={productDetails} />
				</div>

				<div className={styles.productsImg__img}>
					<div className={styles.productsImg__img__main}>
						{images[0] && (
							<img
								src={images[0].url}
								alt={images[0].alt ?? "img_main"}
							/>
						)}
					</div>
					<div className={styles.productsImg__img__secondary}>
						{images.slice(1).map((img, i) => (
							<div key={img.id ?? i}>
								<img
									src={img.url}
									alt={img.alt ?? "img_secondary"}
								/>
							</div>
						))}
					</div>
				</div>

				<button
					className={styles.productsImg__seemore}
					onClick={handleOpenOverlay}
				>
					Ver más
				</button>
			</div>
		</>
	);
};

export default ProductsImg;
