import type { FC } from "react";
import { useFilterStore } from "../../../store/filterStore";

interface IProps {
	checkOutDay: string | number;
	checkOutMounth: string | number;
	checkOutYear: string | number;
}

const ReservationCheckOut: FC<IProps> = ({ checkOutDay, checkOutMounth, checkOutYear }) => {
	const { checkOutDate } = useFilterStore();

	return (
		<div className="reservationD__checkout">
			<div>Check out</div>
			<span>
				{!checkOutDate ? "__/__/__" : `${checkOutDay}/${checkOutMounth}/${checkOutYear}`}
			</span>
		</div>
	);
};

export default ReservationCheckOut;
