import type { FC } from "react";
import { useState, useEffect } from "react";
import axios from "axios";
import styles from "./Categories.module.css";
import ErrorMono from "../helpers/ErrorMono";
import Loader from "../helpers/Loader";
import { useFilterStore } from "../../../store/filterStore";
import type { ICategoria } from "../../../types/IProduct";

const API_BASE = import.meta.env.VITE_API_BASE_URL as string;
const EP_GET_ALL_CATEGORIES = import.meta.env.VITE_API_GET_ALL_CATEGORIES as string;

const Categories: FC = () => {
	const [categories, setCategories] = useState<ICategoria[]>([]);
	const { filter, setFilter, setCleanerButtonClass } = useFilterStore();

	const setFilterAndButton = (categ: ICategoria): void => {
		setFilter(filter !== categ.title ? categ.title : "");
		setCleanerButtonClass("on");
	};

	useEffect(() => {
		axios
			.get<ICategoria[]>(`${API_BASE}${EP_GET_ALL_CATEGORIES}`)
			.then((response) => setCategories(response.data))
			.catch(() => setFilter("error"));
	}, [setFilter]);

	if (filter === "error") {
		return (
			<div>
				<ErrorMono />
			</div>
		);
	}

	if (categories.length === 0) {
		return (
			<>
				<div className={styles.categoriesContainer}>
					<h2>Buscar por tipo de alojamiento</h2>
				</div>
				<Loader />
			</>
		);
	}

	return (
		<div className={styles.categoriesContainer}>
			<div className={styles.headingCards}>
				<h2>Buscar por tipo de alojamiento</h2>
				{categories.map((categ) => (
					<div
						id={filter === categ.title ? `categoriesCard__select__${categ.id}` : ""}
						className={styles.categoriesCard}
						key={categ.id}
						onClick={() => setFilterAndButton(categ)}
					>
						<img
							src={categ.imgUrl}
							alt={categ.title}
						/>
						<div>
							<h3>{categ.title}</h3>
							<h5>{categ.description}</h5>
						</div>
					</div>
				))}
			</div>
		</div>
	);
};

export default Categories;
