import type { FC } from "react";
import { useFilterStore } from "../../../store/filterStore";

interface IProps {
	checkInDay: string | number;
	checkInMounth: string | number;
	checkInYear: string | number;
}

const ReservationCheckIn: FC<IProps> = ({ checkInDay, checkInMounth, checkInYear }) => {
	const { checkInDate } = useFilterStore();

	return (
		<div>
			<div>Check in</div>
			<span>
				{checkInDate ? `${checkInDay}/${checkInMounth}/${checkInYear}` : "__/__/__"}
			</span>
		</div>
	);
};

export default ReservationCheckIn;
