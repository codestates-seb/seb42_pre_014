import { useLocation } from "react-router-dom";

function HideLeftSidebar() {
    const locationName = useLocation().pathname;

    switch (locationName) {
        case "/login":
        case "/register":
        case "/ask":
            return true;
        default:
            return false;
    }
}

export default HideLeftSidebar;