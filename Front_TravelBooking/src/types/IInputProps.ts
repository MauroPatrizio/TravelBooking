import type { IState } from "./IState";

export type IInputProps = {
	placeholder?: string;
	state: IState;
	setState: (newState: IState) => void;
	className?: string;
	funct?: VoidFunction;
	label: string;
	messageError: string;
	name: string;
	regExp?: RegExp;
	type?: string;
};
