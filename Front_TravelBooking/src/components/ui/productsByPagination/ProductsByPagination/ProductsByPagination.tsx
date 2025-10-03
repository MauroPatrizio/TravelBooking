import type { FC } from "react";
import { useState, useEffect } from "react";
import axios from "axios";
import styles from "./ProductsByPagination.module.css";
import Loader from "@/components/helpers/Loader";
import ProductCard from "../../ProductCard/ProductCard";
import PaginationButtons from "../PaginationButtons/PaginationButtons";
import { useIsLoggedStore } from "../../../../store/isLoggedStore";
import { useFilterStore } from "../../../../store/filterStore";
import type { IProduct } from "../../../../types/IProduct";

const API_BASE = import.meta.env.VITE_API_BASE_URL as string;
const EP_GET_PRODUCTS_BY_PAGE = import.meta.env.VITE_API_GET_PRODUCTS_BY_PAGE as string;

const ProductsByPagination: FC = () => {
	const [images, setImages] = useState<IProduct[]>([]);
	const [pageCount, setPageCount] = useState(1);
	const [productQuantity, setProductQuantity] = useState(0);

	const { isLogged } = useIsLoggedStore();
	const { filter } = useFilterStore();

	const userLog = localStorage.getItem("usuario") || "";
	const user = userLog ? userLog.split(" ") : [];

	useEffect(() => {
		axios
			.get<IProduct[]>(`${API_BASE}${EP_GET_PRODUCTS_BY_PAGE}`)
			.then((response) => setImages(response.data))
			.catch(() => {});
	}, []);

	const filteredProducts = () => images.slice(productQuantity, productQuantity + 6);

	const nextPageCount: VoidFunction = () => {
		if (productQuantity <= images.length) {
			setProductQuantity((prev) => prev + 6);
			setPageCount((prev) => prev + 1);
		}
	};

	const prevPageCount: VoidFunction = () => {
		if (productQuantity > 0) {
			setProductQuantity((prev) => prev - 6);
			setPageCount((prev) => prev - 1);
		}
	};

	const imagesLength = images.length;

	if (filter === "error") return <div />;

	if (images.length === 0) {
		return (
			<>
				<div className={styles.productsPaginationContainer}>
					{userLog ? (
						<h2>Nuestras recomendaciones para vos {user[0]}</h2>
					) : (
						<h2>Recomendaciones</h2>
					)}
				</div>
				<Loader />
			</>
		);
	}

	return (
		<>
			<div className={styles.productsPaginationContainer}>
				<h2>
					{isLogged ? `Nuestras recomendaciones para vos ${user[0]}` : "Recomendaciones"}
				</h2>
				{filteredProducts().map(
					(host) =>
						host.imagen.find((img) => img.title === "principal") && (
							<ProductCard
								key={host.id}
								data={host as IProduct}
								searchButton={() => {}}
							/>
						)
				)}
			</div>
			<PaginationButtons
				prevPageCount={prevPageCount}
				nextPageCount={nextPageCount}
				pageCount={pageCount}
				imagesLength={imagesLength}
			/>
		</>
	);
};

export default ProductsByPagination;
