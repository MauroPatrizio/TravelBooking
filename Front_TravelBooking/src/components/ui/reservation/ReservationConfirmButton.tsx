import type { FC } from "react";

interface IProps {
	loaderSuccessful: () => void;
	animationSuccessful: boolean;
	isDisabled: boolean;
}

const ReservationConfirmButton: FC<IProps> = ({
	loaderSuccessful,
	animationSuccessful,
	isDisabled,
}) => {
	return (
		<div className="button__successful__container">
			<div
				onClick={loaderSuccessful}
				className="buttonBar"
				id={animationSuccessful ? "buttonBar2" : ""}
			>
				<span
					id="progreso"
					className="llenandose"
				></span>
			</div>

			<button
				disabled={isDisabled}
				className={
					!isDisabled
						? "reservationD__checkout_button"
						: "reservationD__checkout_button btnDisabled"
				}
				id={animationSuccessful ? "buttonBar3" : ""}
			>
				Confirmar reserva
			</button>
		</div>
	);
};

export default ReservationConfirmButton;
