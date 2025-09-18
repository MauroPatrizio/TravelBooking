import { useEffect } from "react";
import { Link } from "react-router-dom";
import { BsHeartFill } from "react-icons/bs";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faStar as faStarRegular } from "@fortawesome/free-regular-svg-icons";

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
} from "../styleAux/fontAwesoneIcon";

import { useFilterStore } from "../store/filterStore";
import { useIsLoggedStore } from "../store/isLoggedStore";

import styles from "./ProductCard.module.css";

import axios from "axios";
import { baseUrl, getFavByUser } from "../constants/urls";

import type { FC } from "react";
import type { IProduct } from "../types/IProduct";

type IProps = {
	data: IProduct;
	searchButton: VoidFunction;
};

const ProductCard: FC<IProps> = ({ data, searchButton }) => {
	const { setFavoritos, favoritos } = useFilterStore();
	const { isLogged } = useIsLoggedStore();

	const token = JSON.parse(localStorage.getItem("userToken") || "null");
	const userId = localStorage.getItem("id");

	const headers = {
		"Content-Type": "application/json",
		Authorization: `Bearer ${token}`,
	};

	const headersDelete = {
		headers: { Authorization: `Bearer ${token}` },
	};

	const dataPost = {
		producto: { id: data.id },
		usuario: { id: userId },
	};

	const postFav = async () => {
		try {
			await axios.post(baseUrl + "favoritos/agregar", dataPost, {
				headers,
			});
			getFavById();
		} catch (error) {
			console.error(error);
		}
	};

	const pushFav = (obj: { producto: { id: number } }[]) => {
		const ids = obj.map((el) => el.producto.id);
		setFavoritos(ids);
	};

	const getFavById = () => {
		axios
			.get(baseUrl + getFavByUser + userId)
			.then((response) => pushFav(response.data))
			.catch((error) => console.error(`Error: ${error}`));
	};

	const deleteFav = (idProducto: number) => {
		axios
			.delete(baseUrl + "favoritos/" + idProducto + "/" + userId, headersDelete)
			.then(() => getFavById())
			.catch((error) => console.error(`Error: ${error}`));
	};

	useEffect(() => {
		isLogged && getFavById();
	}, [isLogged]);

	const imgStarRegular = (
		<FontAwesomeIcon
			className={styles.faStar}
			icon={faStarRegular}
		/>
	);

	return (
		<div className={styles.cardListImageContainer}>
			<div className={styles.imgContainer}>
				{favoritos.includes(data.id) && localStorage.login != null ? (
					<span
						onClick={() => deleteFav(data.id)}
						className={styles.imgHeartLike}
					>
						<BsHeartFill />
					</span>
				) : (
					<span
						onClick={postFav}
						className={styles.imgHeart}
					>
						{imgHeart}
					</span>
				)}

				{data.imagen.map(
					(ele) =>
						ele.title === "principal" && (
							<img
								key={ele.id}
								src={ele.url}
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
							<div className={styles.productsHeaderdetailsStars}>
								<span>{imgStar}</span>
								<span>{imgStar}</span>
								<span>{imgStar}</span>
								<span>{imgStarHalf}</span>
								<span>{imgStarRegular}</span>
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
						{data.ciudad.city}, a {data.id * 50} m del centro.
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
