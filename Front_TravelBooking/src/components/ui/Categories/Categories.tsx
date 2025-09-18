import { useState, useEffect } from "react";
import axios from "axios";
import styles from "./Categories.module.css";
import ErrorMono from "../helpers/ErrorMono";
import Loader from "../helpers/Loader";
import { baseUrl, getAllCategories } from "../constants/urls";
import { useFilterStore } from "../../../store/filterStore";

interface ICategory {
	id: number;
	title: string;
	description: string;
	imgUrl: string;
}

const Categories = () => {
	const [categories, setCategories] = useState<ICategory[]>([]);
	const { filter, setFilter, setCleanerButtonClass } = useFilterStore();

	const setFilterAndButton = (categ: ICategory) => {
		setFilter(filter !== categ.title ? categ.title : "");
		setCleanerButtonClass("on");
	};

	useEffect(() => {
		axios
			.get<ICategory[]>(baseUrl + getAllCategories)
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
