import React, {useState, useEffect} from "react";
import CourseLecturerDataService from "../services/CourseLecturerDataService";
import Navbar from "./Navbar";

const mainappaddress = "http://localhost:8080";

const Header = () => {
  const [name, setName] = useState(null);

  useEffect(() => {
    CourseLecturerDataService.getUser()
            .then(response => {
              
              setName(response.data);

            })
            .catch(e => {
                console.log(e);
                //If fail, return to home page
                window.location.replace('http://localhost:8080/');
            });
    
  });
  return (
    
    <div>
      {""}
      {name?<div>
        <nav className="navbar navbar-expand-md navbar-light bg-light firstmenu">
        <a className="navbar-brand" href={mainappaddress + "/home"}>CAPS</a>

        <ul className="navbar-nav ml-auto">
          {name ? <li className="nav-item">
          Welcome,&nbsp;
            <em>{name}</em>&nbsp; [<em>ADMIN</em>]
            </li> :
            <li className="nav-item">
              Welcome
            </li> 
            }
                   
        </ul>
      </nav>
      <Navbar />

      </div>:<div></div>}
      
      
    </div>
         
  );
};

export default Header;
