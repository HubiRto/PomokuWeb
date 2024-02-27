import {createBrowserRouter} from 'react-router-dom'
import App from "../App";
import Home from "../pages/Home";
import Bfs from "../pages/Bfs";

export const router = createBrowserRouter([
    {
        path: "/",
        element: <App/>,
        children: [
            {path: "", element: <Home/>},
            {path: "/bfs", element: <Bfs/>}
        ]
    }
])