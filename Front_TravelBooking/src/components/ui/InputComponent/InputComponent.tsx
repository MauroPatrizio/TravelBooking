import type { FC, ChangeEvent } from "react";
import type { IInputProps } from "../../../types/IInputProps";

import styles from "./InputComponent.module.css";

const InputComponent: FC<IInputProps> = ({
	placeholder,
	state,
	setState,
	className,
	funct,
	label,
	messageError,
	name,
	regExp,
	type = "text",
}) => {
	const onChange = (e: ChangeEvent<HTMLInputElement>) => {
		setState({
			...state,
			field: e.target.value,
		});
	};

	const validate = () => {
		if (regExp) {
			setState({
				...state,
				valid: regExp.test(state.field) ? "true" : "false",
			});
		}
		if (funct) funct();
	};

	return (
		<div className={className}>
			<label
				className={state.valid === "true" || state.valid === null ? "" : styles.labelError}
				htmlFor={name}
			>
				{label}
			</label>
			<input
				placeholder={placeholder}
				onChange={onChange}
				onKeyUp={validate}
				onBlur={validate}
				type={type}
				name={name}
				id={name}
				value={state.field}
				className={state.valid === "true" || state.valid === null ? "" : styles.error}
			/>
			<p
				className={
					state.valid === "true" || state.valid === null ? "" : styles.messageError
				}
			>
				{messageError}
			</p>
		</div>
	);
};

export default InputComponent;
