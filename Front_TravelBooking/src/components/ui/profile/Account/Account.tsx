import type { FC } from "react";
import { useState, useEffect } from "react";
import styles from "./Account.module.css";
import BannerImg from "@/assets/images/bannerBooking.png";

import axios from "axios";
import { useFilterStore } from "../../../../store/filterStore";
import Movements from "@/components/Account/Movements";
import { Link } from "react-router-dom";
import { imgLeft } from "../../../../styleAux/fontAwesomeIcons";
import type { IProduct } from "../../../../types/IProduct";
import ProductCard from "../../ProductCard/ProductCard";

interface IFavItem {
	producto: IProduct;
}

const API_BASE = import.meta.env.VITE_API_BASE_URL as string;
const EP_GET_FAV_BY_USER = import.meta.env.VITE_API_GET_FAV_BY_USER as string;

const Account: FC = () => {
	const [favoritosList, setFavoritosList] = useState<IFavItem[]>([]);
	const { setFilterDate, filterDateSearch } = useFilterStore();

	const searchButton: VoidFunction = () => setFilterDate(filterDateSearch);

	const getfavById: VoidFunction = () => {
		axios
			.get<IFavItem[]>(`${API_BASE}${EP_GET_FAV_BY_USER}${localStorage.id}`)
			.then((response) => setFavoritosList(response.data))
			.catch(() => {});
	};

	useEffect(() => {
		getfavById();
	}, []);

	return (
		<>
			<div className={styles.header}>
				<div className={styles.header__left} />
				<div className={styles.header__right}>
					<Link to={"/"}>
						<span className={styles.header__button}>{imgLeft}</span>
					</Link>
				</div>
			</div>

			<div className={styles.account}>
				<img
					className={styles.account__image}
					src={BannerImg}
					alt="banner_profile"
				/>
				<div className={styles.account__title}>
					<h2>Bienvenido {localStorage.userName}</h2>
					<h3>a tu espacio personal en Digital Booking</h3>
				</div>

				<div className={styles.account__fav}>
					<h3>Mis Reservas</h3>
					<div>
						<Movements />
					</div>
				</div>

				<div className={styles.account__fav}>
					<h3>Mis favoritos</h3>

					<div className={styles.movements}>
						<div className={styles.movements__title}>
							<span>Tus&nbsp;</span> Favoritos
						</div>
						<div className={styles.movements__title2}>
							A continuación podrás ver el detalle de tus favoritos.
						</div>
					</div>

					<div className={styles.account__favoritos__list}>
						{favoritosList.length === 0 ? (
							<div className={styles.account__favoritos__null}>
								Aún no tenes favoritos agregados en tu lista personal.
							</div>
						) : (
							favoritosList.map((host) => (
								<ProductCard
									key={host.producto.id}
									data={host.producto}
									searchButton={searchButton}
								/>
							))
						)}
					</div>
				</div>
			</div>
		</>
	);
};

export default Account;
