import type { FC } from "react";
import DatePicker, { registerLocale } from "react-datepicker";
import { es } from "date-fns/locale";
import "react-datepicker/dist/react-datepicker.css";
import styles from "./ProductsDatePicker.module.css";
import { useFilterStore } from "../../../../store/filterStore";
import type { IProduct } from "../../../../types/IProduct";
import type { IReservation } from "../../../../types/IReservation";
import { expandReservationsToDates, countOverlaps } from "../../../../utils/date";

registerLocale("es", es);

interface IProps {
	productDetails: IProduct & { reservations?: IReservation[] };
	pickerDisabled: boolean;
}

const ProductsDatePicker: FC<IProps> = ({ productDetails, pickerDisabled }) => {
	const { checkInDate, setCheckInDate, checkOutDate, setCheckOutDate, setContainsDisabledDates } =
		useFilterStore();

	const normalized = (productDetails.reservations ?? []).map((r) => ({
		fechaInicio: r.startDate,
		fechaFin: r.endDate,
	}));

	const excludedDates = expandReservationsToDates(normalized);
	const overlapCount = countOverlaps(checkInDate, checkOutDate, excludedDates);

	const onChange = (update: [Date | null, Date | null] | Date | null): void => {
		let start: Date | null = null;
		let end: Date | null = null;

		if (Array.isArray(update)) {
			[start, end] = update;
		} else {
			start = update;
		}

		setCheckInDate(start);
		setCheckOutDate(end);
		setContainsDisabledDates(countOverlaps(start, end, excludedDates) > 0);
	};

	return (
		<>
			<div className={styles.productsDatePicker}>
				{pickerDisabled ? (
					<DatePicker
						excludeDates={excludedDates}
						minDate={new Date()}
						inline
						monthsShown={2}
						locale="es"
						disabledKeyboardNavigation
						onChange={onChange}
						startDate={checkInDate}
						endDate={checkOutDate}
						selectsRange
						selectsDisabledDaysInRange
					/>
				) : (
					<DatePicker
						excludeDates={excludedDates}
						minDate={new Date()}
						inline
						monthsShown={2}
						locale="es"
						disabledKeyboardNavigation
						onChange={onChange}
						startDate={checkInDate}
						endDate={checkOutDate}
						selectsDisabledDaysInRange
					/>
				)}
				<span className={overlapCount > 0 ? styles.datePicker__error : styles.none}>
					El rango no puede contener fechas reservadas!
				</span>
			</div>

			<div className={styles.productsDatePicker__mobile}>
				<DatePicker
					excludeDates={excludedDates}
					minDate={new Date()}
					inline
					monthsShown={2}
					locale="es"
					disabledKeyboardNavigation
					onChange={onChange}
					startDate={checkInDate}
					endDate={checkOutDate}
					selectsRange
					selectsDisabledDaysInRange={false}
				/>
				<span className={overlapCount > 0 ? styles.datePicker__error : styles.none}>
					El rango no puede contener fechas reservadas!
				</span>
			</div>
		</>
	);
};

export default ProductsDatePicker;
