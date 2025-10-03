import type { FC } from "react";
import { useEffect, useMemo, useState } from "react";
import axios from "axios";
import styles from "./ReservationDetails.module.css";



import { useFilterStore } from "../../../../store/filterStore";
import { useOpenCarouselStore } from "../../../../store/openCarouselStore";
import type { IProduct } from "../../../../types/IProduct";
import ProductsDatePicker from "../../products/ProductsDatePicker/ProductsDatePicker";
import ReservationCheckOut from "../ReservationCheckOut";
import ReservationCheckIn from "../ReservationCheckIn";

interface IProps {
	productDetails: IProduct;
}

interface IReservationForm {
	name: string;
	lastname: string;
	email: string;
	phone: string;
	comentaries: string;
	covid: boolean;
}

const API_BASE = import.meta.env.VITE_API_BASE_URL as string;
const EP_POST_RESERVATION = import.meta.env.VITE_API_POST_RESERVATION as string; // "reservas"

const userName = localStorage.getItem("userName") ?? "";
const userLastname = localStorage.getItem("userLastname") ?? "";
const userEmail = localStorage.getItem("userEmail") ?? "";

const initialReservationForm: IReservationForm = {
	name: userName,
	lastname: userLastname,
	email: userEmail,
	phone: "",
	comentaries: "",
	covid: false,
};

const ReservationDetails: FC<IProps> = ({ productDetails }) => {
	const [timeSelect, setTimeSelect] = useState<string>("");
	const [isDisabled, setIsDisabled] = useState<boolean>(true);
	const [hoursToData, setHoursToData] = useState<string>("");
	const [reservationForm, setReservationForm] =
		useState<IReservationForm>(initialReservationForm);

	const {
		checkInDate,
		setCheckInDate,
		checkOutDate,
		setCheckOutDate,
		animationSuccessful,
		setAnimationSuccessful,
		containsDisabledDates,
	} = useFilterStore();

	const { openCarousel, handleCarousel } = useOpenCarouselStore();

	const productId = useMemo(() => {
		const raw = sessionStorage.getItem("productId");
		return raw ? Number(JSON.parse(raw)) : productDetails.id;
	}, [productDetails.id]);

	const userId = useMemo(() => {
		const raw = localStorage.getItem("userId") ?? localStorage.getItem("id");
		return raw ? Number(JSON.parse(raw)) : undefined;
	}, []);

	const token = useMemo(() => {
		const raw = localStorage.getItem("userToken");
		try {
			return raw ? (JSON.parse(raw) as string) : null;
		} catch {
			return null;
		}
	}, []);

	const authHeaders = token
		? {
				headers: {
					"Content-Type": "application/json",
					Authorization: `Bearer ${token}`,
				},
		  }
		: { headers: { "Content-Type": "application/json" } };

	const now = new Date();
	const defaultTime = `${String(now.getHours()).padStart(2, "0")}:${String(
		now.getMinutes()
	).padStart(2, "0")}:${String(now.getSeconds()).padStart(2, "0")}`;

	const checkInParts =
		checkInDate != null
			? {
					year: checkInDate.getFullYear(),
					month: String(checkInDate.getMonth() + 1).padStart(2, "0"),
					day: String(checkInDate.getDate()).padStart(2, "0"),
			  }
			: undefined;

	const checkOutParts =
		checkOutDate != null
			? {
					year: checkOutDate.getFullYear(),
					month: String(checkOutDate.getMonth() + 1).padStart(2, "0"),
					day: String(checkOutDate.getDate()).padStart(2, "0"),
			  }
			: undefined;

	const handleChangeSelect = (selected: { value: string; label: string }) => {
		setTimeSelect(selected.value);
		setHoursToData(`${selected.value.slice(0, 2)}:00:00`);
	};

	const emailRegex = /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;

	const validationForm =
		reservationForm.name.trim() !== "" &&
		reservationForm.lastname.trim() !== "" &&
		reservationForm.email.trim() !== "" &&
		emailRegex.test(reservationForm.email);

	const validateCondition =
		timeSelect !== "" && checkInDate != null && checkOutDate != null && !containsDisabledDates;

	useEffect(() => {
		setIsDisabled(!(validateCondition && validationForm));
	}, [validateCondition, validationForm]);

	const removeCalendar: VoidFunction = () => {
		setIsDisabled(true);
		setCheckInDate(null);
		setCheckOutDate(null);
	};

	const postReserve = async (): Promise<void> => {
		if (!checkInParts || !checkOutParts || userId == null) return;

		const payload = {
			horaComienzo: hoursToData || defaultTime,
			fechaInicio: `${checkInParts.year}-${checkInParts.month}-${checkInParts.day}`,
			fechaFin: `${checkOutParts.year}-${checkOutParts.month}-${checkOutParts.day}`,
			usuario: { id: userId },
			producto: { id: productId },
			nombreReserva: reservationForm.name,
			apellidoReserva: reservationForm.lastname,
			emailReserva: reservationForm.email,
			telefonoReserva: reservationForm.phone,
			comentarios: reservationForm.comentaries,
			vacunacion: reservationForm.covid,
		};

		await axios.post(`${API_BASE}${EP_POST_RESERVATION}`, payload, authHeaders);
	};

	const loaderSuccessful: VoidFunction = () => {
		if (validateCondition && validationForm) {
			postReserve()
				.then(() => {
					setAnimationSuccessful(true);
					setTimeout(() => {
						handleCarousel();
					}, 4800);
					setTimeout(() => {
						setCheckInDate(null);
						setCheckOutDate(null);
					}, 12000);
				})
				.catch(() => {});
		}
	};

	return (
		<>
			<div className={openCarousel ? styles.successfull__on : styles.successfull__none}>
				<ReservationSuccessful productDetails={productDetails} />
			</div>

			<div className={styles.reservationD}>
				<div className={styles.reservationD__title}>
					<h2>Completá tus datos</h2>
					<p>Ingresá los datos de la persona a quien está dirigida la reserva</p>
				</div>

				<div className={styles.reservationD__container}>
					<div className={styles.reservationD__container__blockleft}>
						<ReservationForm
							reservationForm={reservationForm}
							setReservationForm={setReservationForm}
						/>

						<div className={styles.reservationD__calendar}>
							<h2>Seleccioná tu fecha de reserva</h2>
							<ProductsDatePicker
								productDetails={productDetails}
								pickerDisabled={true}
							/>
						</div>

						<div className={styles.reservationD__time}>
							<h2>Tu horario de llegada</h2>
							<ReservationTimeArrival handleChangeSelect={handleChangeSelect} />
						</div>
					</div>

					<div className={styles.reservationD__container__blockright}>
						<div>
							<h2>Detalle de la reserva</h2>
							<div className={styles.reservationD__container__card}>
								<ReservationImage product={productDetails} />

								<div className={styles.reservationD__container__blockright__dates}>
									<h3>{productDetails.categoria.title.toUpperCase()}</h3>
									<h2>{productDetails.name}</h2>
									<ReservationStars />
									<ReservationLocation product={productDetails} />

									<ReservationCheckIn
										checkInDay={checkInParts?.day ?? "__"}
										checkInMounth={checkInParts?.month ?? "__"}
										checkInYear={checkInParts?.year ?? "__"}
									/>

									<ReservationCheckOut
										checkOutDay={checkOutParts?.day ?? "__"}
										checkOutMounth={checkOutParts?.month ?? "__"}
										checkOutYear={checkOutParts?.year ?? "__"}
									/>

									<div className={styles.reservation__errors}>
										{checkInDate && checkOutDate ? (
											<button
												className={styles.reservation__button__remover}
												onClick={removeCalendar}
											>
												Limpiar fechas
											</button>
										) : null}

										{!checkInDate || !checkOutDate ? (
											<p className={styles.reservation__errors__p}>
												Ingresar fecha check in - check out
											</p>
										) : null}

										{timeSelect === "" ? (
											<p className={styles.reservation__errors__p}>
												Ingresar horario de ingreso
											</p>
										) : null}

										{!validationForm ? (
											<p className={styles.reservation__errors__p}>
												Completar tus datos requeridos
											</p>
										) : null}
									</div>

									<ReservationConfirmButton
										loaderSuccessful={loaderSuccessful}
										animationSuccessful={animationSuccessful}
										isDisabled={isDisabled}
									/>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</>
	);
};

export default ReservationDetails;
