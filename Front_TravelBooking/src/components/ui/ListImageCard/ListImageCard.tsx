import { useState, useEffect } from "react";
import axios from "axios";
import styles from "./ListImageCard.module.css";
import Loader from "../helpers/Loader";
import ProductCard from "../ProductCard/ProductCard";
import PaginationButtons from "./productsByPagination/PaginationBottons";
import { useFilterStore } from "../../../store/filterStore";
import { useFilterByCityStore } from "../../../store/filterByCityStore";
import { useIsLoggedStore } from "../../../store/isLoggedStore";

interface IProduct {
	id: number;
	name: string;
	categoria: { title: string };
	ciudad: { city: string };
	reservas: { fechaInicio: string; fechaFin: string }[];
}

const API_BASE = import.meta.env.VITE_API_BASE_URL as string;
const EP_GET_ALL_PRODUCTS = import.meta.env.VITE_API_GET_ALL_PRODUCTS as string;
const EP_GET_FAV_BY_USER = import.meta.env.VITE_API_GET_FAV_BY_USER as string;

const ListImageCard = () => {
	const [images, setImages] = useState<IProduct[]>([]);
	const [pageCount, setPageCount] = useState(1);
	const [productQuantity, setProductQuantity] = useState(0);

	const { filterCity } = useFilterByCityStore();
	const {
		filter,
		filterDateSearch,
		filterDate,
		setFilterDate,
		searchByDate,
		searchFilter,
		setFavoritos,
	} = useFilterStore();
	const { isLogged } = useIsLoggedStore();

	const searchButton: VoidFunction = () => {
		setFilterDate(filterDateSearch);
	};

	const setValueCalendar = (arrayWithDates: string[]) =>
		`${arrayWithDates[0]} al ${arrayWithDates[arrayWithDates.length - 1]}`;

	const sumarDias = (fecha: Date, dias: number) => {
		const newDate = new Date(fecha);
		newDate.setDate(newDate.getDate() + dias);
		return newDate;
	};

	const getDates = (arrayFechas: { fechaInicio: string; fechaFin: string }[]): number => {
		let resultado = 0;
		arrayFechas.forEach((element) => {
			const fechaInicio = new Date(element.fechaInicio + "T00:00:00");
			const fechaFin = new Date(element.fechaFin + "T00:00:00");

			const dateArray: Date[] = [];
			let currentDate = fechaInicio;
			const rangoHotelparce: number[] = [];
			const rangoCalendarparce: Date[] = [];
			const rangoCalendarparceV2: number[] = [];

			while (currentDate <= fechaFin) {
				dateArray.push(currentDate);
				currentDate = sumarDias(currentDate, 1);
			}
			dateArray.forEach((d) => rangoHotelparce.push(Date.parse(d)));
			filterDate.forEach((f) => rangoCalendarparce.push(new Date(f)));
			rangoCalendarparce.forEach((d) => rangoCalendarparceV2.push(Date.parse(d)));

			rangoCalendarparceV2.forEach((d2) => {
				if (rangoHotelparce.includes(d2)) {
					resultado = resultado + 1;
				}
			});
		});
		return resultado;
	};

	const list = images.filter(
		(item) =>
			(filter === "" ? true : filter === item.categoria.title) &&
			(filterCity.length === 0 ? true : item.ciudad.city === filterCity) &&
			(filterDateSearch.length === 0 ? true : getDates(item.reservas) === 0)
	);

	const getImgById = () => {
		axios
			.get<IProduct[]>(`${API_BASE}${EP_GET_ALL_PRODUCTS}`)
			.then((response) => {
				setImages(response.data);
			})
			.catch((error) => console.error(`Error: ${error}`));
	};

	useEffect(() => {
		getImgById();
	}, []);

	const filteredProducts = () => list.slice(productQuantity, productQuantity + 6);
	const imagesLength = list.length;

	const nextPageCount: VoidFunction = () => {
		if (productQuantity <= images.length) {
			setProductQuantity((prevState) => prevState + 6);
			setPageCount((prevState) => prevState + 1);
		}
	};

	const prevPageCount: VoidFunction = () => {
		if (productQuantity > 0) {
			setProductQuantity((prevState) => prevState - 6);
			setPageCount((prevState) => prevState - 1);
		}
	};

	const getfavById = () => {
		axios
			.get(`${API_BASE}${EP_GET_FAV_BY_USER}${localStorage.id}`)
			.then((response) => {
				pushearFav(response.data);
			})
			.catch((error) => console.error(`Error: ${error}`));
	};

	useEffect(() => {
		if (isLogged) getfavById();
	}, [isLogged]);

	const pushearFav = (obj: { producto: { id: number } }[]) => {
		const initialState: number[] = [];
		obj.forEach((element) => initialState.push(element.producto.id));
		setFavoritos(initialState);
	};

	if (filter === "error") {
		return <div></div>;
	}

	if (images.length === 0) {
		return (
			<>
				<div className={styles.listImageContainer}>
					<h2>Productos filtrados</h2>
				</div>
				<Loader />
			</>
		);
	}

	if (filterCity.length > 0 || filter !== "" || searchByDate.length > 0) {
		return (
			<div className={styles.listImageContainer}>
				{filter !== "" && filterCity.length === 0 && searchByDate.length === 0 && (
					<h2>{filter} disponibles</h2>
				)}

				{filter !== "" && filterCity.length === 0 && searchByDate.length !== 0 && (
					<h2>
						{filter} disponibles del {setValueCalendar(searchByDate)}
					</h2>
				)}

				{filter !== "" && filterCity.length !== 0 && searchByDate.length === 0 && (
					<h2>
						{filter} disponibles en {filterCity}
					</h2>
				)}

				{filter === "" && filterCity.length !== 0 && searchByDate.length === 0 && (
					<h2>Alojamientos disponibles en {filterCity}</h2>
				)}

				{filter === "" && filterCity.length !== 0 && searchByDate.length !== 0 && (
					<h2>
						Alojamientos disponibles en {filterCity} del{" "}
						{setValueCalendar(searchByDate)}
					</h2>
				)}

				{filter === "" && filterCity.length === 0 && searchByDate.length !== 0 && (
					<h2>Alojamientos disponibles del {setValueCalendar(searchByDate)}</h2>
				)}

				{filter !== "" && filterCity.length !== 0 && searchByDate.length !== 0 && (
					<h2>
						{filter} disponibles en {filterCity} del {setValueCalendar(searchByDate)}
					</h2>
				)}

				{list.map((host) => (
					<ProductCard
						key={host.id}
						data={host}
						searchButton={searchButton}
					/>
				))}
			</div>
		);
	}

	if (filterCity.length === 0 && filter === "" && searchByDate.length === 0 && searchFilter) {
		return (
			<>
				<div className={styles.listImageContainer}>
					<h2>Alojamientos disponibles</h2>
					{filteredProducts().map((host) => (
						<ProductCard
							key={host.id}
							data={host}
							searchButton={searchButton}
						/>
					))}
				</div>
				<PaginationButtons
					prevPageCount={prevPageCount}
					nextPageCount={nextPageCount}
					pageCount={pageCount}
					imagesLength={imagesLength}
				/>
			</>
		);
	}

	return null;
};

export default ListImageCard;
