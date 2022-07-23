import React from "react";
import { Link } from "react-router-dom";

const mainappaddress = "http://localhost:8080";

export const Navbar = () => {
  return (
    <div>
      <nav className="navbar navbar-expand-sm navbar-light bg-light">

        <div className="navbar-collapse collapse justify-content-center secondmenu">
          <ul className="navbar-nav">
            <li className="nav-item dropdown">
              <a className="nav-link dropdown-toggle" href={mainappaddress + "/manage/student/view"} >Manage Students</a>
              <ul className="dropdown-menu">
                <li>
                  <a className="dropdown-item" href={mainappaddress + "/manage/student/view"}>View Students</a>
                </li>
                <li>
                  <a className="dropdown-item" href={mainappaddress + "/manage/student/create"}>Enroll Student</a>
                </li>
              </ul>
            </li>
            <li className="nav-item dropdown">
              <a className="nav-link dropdown-toggle" href={mainappaddress + "/manage/lecturer/list"}>Manage Lecturers</a>
              <ul className="dropdown-menu">
                <li>
                  <a className="dropdown-item" href={mainappaddress + "/manage/lecturer/list"}>View Lecturers</a>
                </li>
                <li>
                  <a className="dropdown-item" href={mainappaddress + "/manage/lecturer/create"}>Create Lecturer</a>
                </li>
              </ul>
            </li>
            <li className="nav-item dropdown">
              <a className="nav-link dropdown-toggle" href={mainappaddress + "/manage/course/list"}>Manage Courses</a>
              <ul className="dropdown-menu">
                <li>
                  <a className="dropdown-item" href={mainappaddress + "/manage/course/list"}>View Courses</a>
                </li>
                <li>
                <a className="dropdown-item" href={"/manage/course/create"}>Create Courses</a>
                  
                </li>
                <li>
                <a className="dropdown-item" href={"/manage/course/courselecturer"}>Assign Lecturers</a>
                  

                </li>
              </ul>
            </li>
            <li className="nav-item">
              <a className="nav-link" href={mainappaddress + "/manage/studentcourse/view"}>Manage Course Enrollment</a>
            </li>

            <li className="nav-item">
              <a className="nav-link" href={mainappaddress + "/logout"}>Log Out</a>
            </li>
          </ul>
        </div>

      </nav>
    </div>
  );
};

export default Navbar;
