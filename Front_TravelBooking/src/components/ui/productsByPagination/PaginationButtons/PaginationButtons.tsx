import type { FC } from "react";
import styles from "./PaginationButtons.module.css";

interface PaginationButtonsProps {
	prevPageCount: VoidFunction;
	nextPageCount: VoidFunction;
	pageCount: number;
	imagesLength: number;
}

const PaginationButtons: FC<PaginationButtonsProps> = ({
	prevPageCount,
	nextPageCount,
	pageCount,
	imagesLength,
}) => {
	return (
		<div className={styles.navegationBtn}>
			<button
				onClick={prevPageCount}
				className={pageCount <= 1 ? styles.withoutBtn : ""}
			>
				{pageCount <= 1 ? "" : "Anterior"}
			</button>
			<p>{`Página ${pageCount}`}</p>
			<button
				onClick={nextPageCount}
				className={pageCount >= imagesLength / 6 ? styles.withoutBtn : ""}
			>
				{pageCount >= imagesLength / 6 ? "" : "Siguiente"}
			</button>
		</div>
	);
};

export default PaginationButtons;
