import { useEffect } from "react";
import { Link } from "react-router-dom";
import type { FC } from "react";
import axios from "axios";
import {
	imgWifi,
	imgStar,
	imgStarHalf,
	imgHeart,
	imgAirCond,
	imgParking,
	imgKitchen,
	imgPet,
	imgPool,
	imgTv,
	imgLocation,
} from "../../../styleAux/fontAwesomeIcons";
import { BsHeartFill } from "react-icons/bs";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faStar as faStarRegular } from "@fortawesome/free-regular-svg-icons";
import { useFilterStore } from "../../../store/filterStore";
import { useIsLoggedStore } from "../../../store/isLoggedStore";
import type { IProduct } from "../../../types/IProduct";
import styles from "./ProductCard.module.css";

type IProps = {
	data: IProduct;
	searchButton: VoidFunction;
};

const API_BASE = import.meta.env.VITE_API_BASE_URL as string;
const EP_GET_FAV_BY_USER = import.meta.env.VITE_API_GET_FAV_BY_USER as string;

const ProductCard: FC<IProps> = ({ data, searchButton }) => {
	const { setFavoritos, favoritos } = useFilterStore();
	const { isLogged } = useIsLoggedStore();

	const token = (() => {
		try {
			return JSON.parse(localStorage.getItem("userToken") || "null") as string | null;
		} catch {
			return null;
		}
	})();

	const userId = localStorage.getItem("id");

	const axiosAuthHeaders = token
		? {
				headers: {
					"Content-Type": "application/json",
					Authorization: `Bearer ${token}`,
				},
		  }
		: undefined;

	const axiosDeleteHeaders = token
		? { headers: { Authorization: `Bearer ${token}` } }
		: undefined;

	const refreshFavs: VoidFunction = () => {
		if (!userId) return;
		axios
			.get<{ producto: { id: number } }[]>(`${API_BASE}${EP_GET_FAV_BY_USER}${userId}`)
			.then((res) => {
				const ids = res.data.map((f) => f.producto.id);
				setFavoritos(ids as unknown as never);
			})
			.catch((e) => console.error("Favs error:", e));
	};

	const addFav: VoidFunction = () => {
		if (!isLogged || !userId || !axiosAuthHeaders) return;
		const payload = { producto: { id: data.id }, usuario: { id: userId } };
		axios
			.post(`${API_BASE}favoritos/agregar`, payload, axiosAuthHeaders)
			.then(refreshFavs)
			.catch((e) => console.error("Add fav error:", e));
	};

	const removeFav =
		(productId: number | string): VoidFunction =>
		() => {
			if (!isLogged || !userId || !axiosDeleteHeaders) return;
			axios
				.delete(`${API_BASE}favoritos/${productId}/${userId}`, axiosDeleteHeaders)
				.then(refreshFavs)
				.catch((e) => console.error("Remove fav error:", e));
		};

	useEffect(() => {
		if (isLogged) refreshFavs();
	}, [isLogged]);

	return (
		<div className={styles.cardListImageContainer}>
			<div className={styles.imgContainer}>
				{favoritos.includes(Number(data.id)) && localStorage.getItem("login") ? (
					<span
						onClick={removeFav(data.id)}
						className={styles.imgHeartLike}
					>
						<BsHeartFill />
					</span>
				) : (
					<span
						onClick={addFav}
						className={styles.imgHeart}
					>
						{imgHeart}
					</span>
				)}

				{data.imagen.map(
					(img) =>
						img.title === "principal" && (
							<img
								key={img.id}
								src={img.url}
								alt={data.name}
							/>
						)
				)}
			</div>

			<div className={styles.characteristics}>
				<div className={styles.listImageContainerTop}>
					<div className={styles.listImageContainerTop1}>
						<div className={styles.characteristicsStars}>
							<h5>{data.categoria.title.toUpperCase()}</h5>
							<div>
								<span>{imgStar}</span>
								<span>{imgStar}</span>
								<span>{imgStar}</span>
								<span>{imgStarHalf}</span>
								<span>
									<FontAwesomeIcon icon={faStarRegular} />
								</span>
							</div>
						</div>
						<div className={styles.listImageCardTitle}>
							<h3>{data.name}</h3>
						</div>
					</div>

					<div className={styles.listImageContainerTop2}>
						<span>8</span>
						<div>Muy bueno</div>
					</div>
				</div>

				<div className={styles.listImageCardCharacteristics}>
					<span>{imgLocation}</span>
					<p>
						{data.ciudad.city}, a {Number(data.id) * 50} m del centro.
					</p>
				</div>

				<div className={styles.listImageCardIcons}>
					{data.caracteristica.airConditioning && imgAirCond}
					{data.caracteristica.freeParking && imgParking}
					{data.caracteristica.kitchen && imgKitchen}
					{data.caracteristica.petsAllowed && imgPet}
					{data.caracteristica.pool && imgPool}
					{data.caracteristica.tv && imgTv}
					{data.caracteristica.wifi && imgWifi}
				</div>

				<p className={styles.listImageCardDesc}>{data.description}</p>

				<Link
					onClick={searchButton}
					to={`/alojamientos/${data.id}/${data.name.replace(/[+ ]|%20/g, "-")}`}
				>
					<button>Ver más</button>
				</Link>
			</div>
		</div>
	);
};

export default ProductCard;
