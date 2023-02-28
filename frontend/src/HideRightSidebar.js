import { useLocation } from "react-router-dom";

function HideRightSidebar() {
    const locationName = useLocation().pathname;

    switch (locationName) {
        case "/login":
        case "/register":
        case "/tags":
            return true;
        default:
            return false;
    }
}

export default HideRightSidebar;