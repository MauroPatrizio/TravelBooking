import styles from "./ReservationSuccessful.module.css";
import monoSucc from "../../assets/images/mono_successful.png";
import { BsCheckCircle } from "react-icons/bs";
import { Link } from "react-router-dom";
import { useOpenCarouselStore } from "../../../../store/openCarouselStore";
import { useFilterStore } from "../../../../store/filterStore";

const ReservationSuccessful = () => {
	const { handleCarousel } = useOpenCarouselStore();
	const { setAnimationSuccessful } = useFilterStore();

	const loaderSuccessful = () => {
		setAnimationSuccessful(false);
		handleCarousel();
	};

	return (
		<div>
			<img
				className={styles.mono__succs}
				src={monoSucc}
				alt="Reserva exitosa!"
			/>
			<div className={styles.reservationSuccessful}>
				<h2>Reserva exitosa!</h2>
				<span className={styles.icon__successfull}>
					<BsCheckCircle />
				</span>
				<Link to={"/"}>
					<button
						onClick={loaderSuccessful}
						className={styles.button__successful}
					>
						Volver a la home
					</button>
				</Link>
			</div>
		</div>
	);
};

export default ReservationSuccessful;
