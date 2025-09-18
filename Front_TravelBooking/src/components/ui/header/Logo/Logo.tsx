import { Link } from "react-router-dom";
import logoDB from "../../assets/images/logo-DB.png";
import styles from "./Logo.module.css";

const Logo = () => {
	return (
		<div className={styles.logoContainer}>
			<Link to="/">
				<img
					className={styles.logo}
					src={logoDB}
					alt="Logo Digital Booking"
				/>
			</Link>
			<Link
				className={styles.slogan}
				to="/"
			>
				<p>SENTITE COMO EN TU CASA</p>
			</Link>
		</div>
	);
};

export default Logo;
