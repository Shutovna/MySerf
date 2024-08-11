import {ACCESS_TOKEN} from '../../constants';
import {Navigate, useLocation, } from "react-router-dom";

const OAuth2RedirectHandler = () => {
    const location = useLocation();

    // Создаем объект URLSearchParams из строки запроса
    const params = new URLSearchParams(location.search);


    const token =params.get('token');
    const error =params.get('error');
    console.log("OAuth2RedirectHandler: token=" + token + " error=" + error);

    if (token) {
        localStorage.setItem(ACCESS_TOKEN, token);
        return (
            <>
                <Navigate to={"/cab/main"} state={{from: location.pathname}}/>
            </>
        );

    } else {
        return (
            <>
                <Navigate to={"/auth/signin"} state={{from: location.pathname, error: error}}/>
            </>
        );
    }
}

export default OAuth2RedirectHandler;