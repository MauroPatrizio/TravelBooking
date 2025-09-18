import { useOpenToggleStore } from "../../../store/openToggleStore";
import SocialNet from "./SocialNet";
import styles from "./Footer.module.css";

const Footer = () => {
	const { openNav } = useOpenToggleStore();

	return (
		<footer className={styles.footer}>
			<h4>©2022 Digital Booking</h4>
			{!openNav && (
				<div className={styles.socialNet}>
					<SocialNet />
				</div>
			)}
		</footer>
	);
};

export default Footer;
