import styles from "./Login.module.css";
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";
import { jwtDecode } from "jwt-decode";
import { imgEye, imgEyeSlash, warnIcon } from "../../../styleAux/fontAwesomeIcons";
import InputComponent from "./InputComponent";
import SweetAlert from "../helpers/SweetAlert";
import { useIsLoggedStore } from "../../../store/isLoggedStore";
import type { IState } from "../../../types/IState";
import type { IUser } from "../../../types/IUser";

interface IJwtPayload {
	id: number;
	firstName: string;
	lastName: string;
	email: string;
}

const API_BASE = import.meta.env.VITE_API_BASE_URL as string;
const EP_POST_LOGIN = import.meta.env.VITE_API_POST_LOGIN as string;

const Login = () => {
	const [shown, setShown] = useState(false);
	const [email, setEmail] = useState<IState>({ field: "", valid: null });
	const [password, setPassword] = useState<IState>({ field: "", valid: null });
	const [formLogin, setFormLogin] = useState<boolean | null>(null);

	const { flag, setFlag, setIsLogged } = useIsLoggedStore();
	const navigate = useNavigate();

	const productId = sessionStorage.getItem("productId");
	const productLocation = sessionStorage.getItem("productLocation");

	const switchShown: VoidFunction = () => setShown((prev) => !prev);

	const expressions = {
		password: /^((?=.*\d)(?=.*[a-záéíóúüñ])(?=.*[A-Z])).{6,12}/,
		email: /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/,
	};

	const onSubmit = (e: React.FormEvent) => {
		e.preventDefault();
		if (email.valid === "true" && password.valid === "true") {
			postUser();
		}
	};

	const postUser = async () => {
		try {
			const response = await axios.post<{ tokenDeAcceso: string }>(
				`${API_BASE}${EP_POST_LOGIN}`,
				{
					email: email.field,
					contraseña: password.field,
				}
			);

			const token = response.data.tokenDeAcceso;
			const decoded = jwtDecode<IJwtPayload>(token);

			const user: IUser = {
				id: decoded.id,
				firstName: decoded.firstName,
				lastName: decoded.lastName,
				email: decoded.email,
			};

			setFormLogin(true);
			if (token) {
				localStorage.setItem("userFirstName", user.firstName);
				localStorage.setItem("userLastName", user.lastName);
				localStorage.setItem("userFullName", `${user.firstName} ${user.lastName}`);
				localStorage.setItem("userId", user.id.toString());
				localStorage.setItem("userEmail", user.email);
				localStorage.setItem("login", "true");
				localStorage.setItem("id", user.id.toString());
				localStorage.setItem("userToken", JSON.stringify(token));
				setIsLogged(true);

				SweetAlert.messageLoginOk("Aguarde mientras se procesa la información", () => {
					if (flag && productId && productLocation) {
						setFlag(false);
						navigate(
							`/alojamientos/${productId}/${productLocation.replace(
								/[+ ]|%20/g,
								"-"
							)}/reservar`,
							{ replace: true }
						);
					} else {
						navigate("/", { replace: true });
					}
				});
			} else {
				setFormLogin(false);
				setIsLogged(false);
			}
		} catch {
			setFormLogin(false);
			setIsLogged(false);
			SweetAlert.messageError(
				"Ooops! Ocurrió un error!",
				"Lamentablemente no ha podido iniciar sesión. Por favor intente más tarde"
			);
		}
	};

	return (
		<main className={styles.main}>
			{flag && (
				<div className={styles.flagMessageContainer}>
					{warnIcon}
					<p className={styles.flagMessage}>
						Para realizar una reserva, necesitas estar logueado
					</p>
				</div>
			)}
			<div className={styles.form}>
				<h2>Iniciar sesión</h2>
				<form
					className={styles.login}
					onSubmit={onSubmit}
				>
					<div className={styles.loginInput}>
						<InputComponent
							placeholder="user@digitalbooking.com"
							setState={setEmail}
							state={email}
							label="Correo electrónico"
							name="email"
							regExp={expressions.email}
							type="email"
							messageError="El formato de correo no es válido."
						/>
					</div>
					<div className={styles.loginInput}>
						<div className={styles.pass}>
							<InputComponent
								setState={setPassword}
								state={password}
								label="Contraseña"
								name="password"
								regExp={expressions.password}
								type={shown ? "text" : "password"}
								messageError="La contraseña debe tener de 6 a 12 caracteres, contener números, minúsculas y mayúsculas."
							/>
							<span
								className={styles.eye}
								onClick={switchShown}
							>
								{shown ? imgEye : imgEyeSlash}
							</span>
						</div>
					</div>
					<div
						className={
							formLogin === true || formLogin === null
								? styles.formLogin
								: styles.formLoginError
						}
					>
						<p>Por favor vuelva a intentarlo, sus credenciales son inválidas.</p>
					</div>
					<div className={styles.btn}>
						<button
							type="submit"
							className={styles.btnLogin}
						>
							Ingresar
						</button>
					</div>
					<p>
						¿Aún no tenés cuenta?{" "}
						<Link
							to="/signup"
							className={styles.link}
						>
							Regístrate
						</Link>
					</p>
				</form>
			</div>
		</main>
	);
};

export default Login;
