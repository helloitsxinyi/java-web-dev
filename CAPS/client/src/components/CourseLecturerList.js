import React, { Component } from "react";
import { Link } from "react-router-dom";
import CourseLecturerDataService from "../services/CourseLecturerDataService";

export default class CourseLecturerList extends Component {
    //This displays the course lecturer assignment screen
    constructor(props) {
        super(props);
        //Retrieve all the courses
        this.retrieveCourse = this.retrieveCourse.bind(this);
        //this.retrieveLecturer = this.retrieveLecturer.bind(this);

        //Retrieve all the lectures assigned and not assigned
        this.retrieveAssignLecturer = this.retrieveAssignLecturer.bind(this);
        this.retrieveNotAssignLecturer = this.retrieveNotAssignLecturer.bind(this);

        //SetActive (when click)
        this.setActiveCourse = this.setActiveCourse.bind(this);
        this.setActiveAssignLecturer = this.setActiveAssignLecturer.bind(this);
        this.setActiveNotAssignedLecturer = this.setActiveNotAssignedLecturer.bind(this);

        // Add or Remove lecturer from course
        this.assignLecturerToCourse = this.assignLecturerToCourse.bind(this);
        this.removeLecturerFromCourse = this.removeLecturerFromCourse.bind(this);

        this.state = {
            //Store the states to be used
            courses: [],
            currentCourse: null,
            currentCourseIndex: -1,
            notAssignedLecturers: [],
            currentNotAssignedLecturer: null,
            currentNotAssignedLecturerIndex: -1,
            assignedLecturers: [],
            currentLecturerAssigned: null,
            currentLecturerAssignedIndex: -1,
            changesMade:false,
            Lecturers: [],
        };
    }

    componentDidMount() {
        //Basically the activities that will be done when mounting this component
        this.retrieveCourse();
    }

    //Methods that are required
    retrieveCourse() {
        //Retrieve all courses (use for left table)
        CourseLecturerDataService.getCourses()
            .then(response => {
                this.setState({
                    courses: response.data
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    /*
    retrieveLecturer() {
        CourseLecturerDataService.getLecturers()
            .then(response => {
                this.setState({
                    lecturers: response.data
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }
    */

    retrieveAssignLecturer(courseId) {
        //Retrieve assign lecturers (use for right table)
        CourseLecturerDataService.getLecturersByCourseId(courseId)
            .then(response => {
                this.setState({
                    assignedLecturers: response.data
                });
                console.log(response.data);
                this.forceUpdate();
            })
            .catch(e => {
                console.log(e);
            });
    }

    retrieveNotAssignLecturer(courseId) {
        //Retrieve not assign lecturers (use for middle table)
        CourseLecturerDataService.getAvailLecturersByCourseId(courseId)
            .then(response => {
                this.setState({
                    notAssignedLecturers: response.data
                });
                console.log(response.data);
                this.forceUpdate();
            })
            .catch(e => {
                console.log(e);
            });
    }

    setActiveCourse(course, index) {
        //Select course
        
        if(this.state.currentCourse != null && this.state.changesMade){
            if(course.courseCode == this.state.currentCourse.courseCode){
                return;
            }
            if(window.confirm("Do you wish to save the changes made?")){
                this.confirmLecturerAndNavigate(this.state.currentCourse, this.state.assignedLecturers, course, index);
            }
            
        }
        this.setState({
            currentCourse: course,
            currentCourseIndex: index,
            changesMade: false,
        });
        this.retrieveAssignLecturer(course.courseCode);
        this.retrieveNotAssignLecturer(course.courseCode);

    }

    setActiveAssignLecturer(lecturer, index) {
        //Select Assigned Lecturer
        this.setState({
            currentLecturerAssigned: lecturer,
            currentLecturerAssignedIndex: index,
            currentNotAssignedLecturer: null,
            currentNotAssignedLecturerIndex: -1,
        });
    }

    setActiveNotAssignedLecturer(lecturer, index) {
        //Select Not Assigned Lecturer
        this.setState({
            currentNotAssignedLecturer: lecturer,
            currentNotAssignedLecturerIndex: index,
            currentLecturerAssigned: null,
            currentLecturerAssignedIndex: -1,
        });
    }

    assignLecturerToCourse(lecturer, notassignlec, assignlec) {
        //Assign lecturer to course
        notassignlec = notassignlec.filter(x => x.lecturerId != lecturer.lecturerId)
        assignlec.push(lecturer)
        this.setState({
            currentNotAssignedLecturer: null,
            currentNotAssignedLecturerIndex: -1,
            assignedLecturers: assignlec,
            notAssignedLecturers: notassignlec,
            changesMade:true,
        });

    }

    removeLecturerFromCourse(lecturer, notassignlec, assignlec) {
        //Remove the lecturer assigned
        assignlec = assignlec.filter(x => x.lecturerId != lecturer.lecturerId)
        notassignlec.push(lecturer)
        this.setState({
            currentLecturerAssigned: null,
            currentLecturerAssignedIndex: -1,
            assignedLecturers: assignlec,
            notAssignedLecturers: notassignlec,
            changesMade:true,
        });

    }

    confirmLecturer(currentCourse, assignlec) {
        //To confirm the lecturers
        CourseLecturerDataService.putLectuerToCourse(currentCourse.courseCode, assignlec)
            .then(response => {
                this.setState({
                    notAssignedLecturers: [],
                    currentNotAssignedLecturer: null,
                    currentNotAssignedLecturerIndex: -1,
                    assignedLecturers: [],
                    currentLecturerAssigned: null,
                    currentLecturerAssignedIndex: -1,
                    currentCourse: null,
                    currentCourseIndex: -1,
                    
                });
                console.log(response.data);
                this.forceUpdate();
                
            })
            .catch(e => {
                console.log(e);
            });
    }

    confirmLecturerAndNavigate(currentCourse, assignlec, toCourse, toCourseIndex) {
        //To confirm the lecturers
        CourseLecturerDataService.putLectuerToCourse(currentCourse.courseCode, assignlec)
            .then(response => {
                this.setState({
                    notAssignedLecturers: [],
                    currentNotAssignedLecturer: null,
                    currentNotAssignedLecturerIndex: -1,
                    assignedLecturers: [],
                    currentLecturerAssigned: null,
                    currentLecturerAssignedIndex: -1,
                    currentCourse: toCourse,
                    currentCourseIndex: toCourseIndex,
                    changesMade: false,
                });
                console.log(response.data);
                this.forceUpdate();
                this.retrieveAssignLecturer(toCourse.courseCode);
                this.retrieveNotAssignLecturer(toCourse.courseCode);
            })
            .catch(e => {
                console.log(e);
            });
    }


    //Render
    render() {
        const { courses, currentCourse, currentCourseIndex,
            lecturers,
            notAssignedLecturers, currentNotAssignedLecturer, currentNotAssignedLecturerIndex,
            assignedLecturers, currentLecturerAssigned, currentLecturerAssignedIndex } = this.state;
        return (
            <div className="container">
                <div className="row flex-nowrap main-dys">

                    <div className="col col-3 list-col">
                        <h4>Course List</h4>

                        <ul className="list-group list-dys">
                            {courses &&
                                courses.map((course, index) => (
                                    <li
                                        className={
                                            "list-group-item " +
                                            (index === currentCourseIndex ? "active" : "")
                                        }
                                        onClick={() => this.setActiveCourse(course, index)}
                                        key={index}
                                    >
                                        {course.courseCode}
                                    </li>
                                ))}
                        </ul>
                    </div>


                    <div className="col col-4 list-col">
                        <h4>Available Lecturer</h4>
                        <ul className="list-group list-dys">
                            {currentCourse ? (notAssignedLecturers.length > 0 ?
                                (notAssignedLecturers && notAssignedLecturers.map((lecturerRmd, index) => (
                                    <li
                                        className={
                                            "list-group-item " +
                                            (index === currentNotAssignedLecturerIndex ? "active" : "")
                                        }
                                        onClick={() => this.setActiveNotAssignedLecturer(lecturerRmd, index)}
                                        onDoubleClick={() => this.assignLecturerToCourse(lecturerRmd, notAssignedLecturers, assignedLecturers)}
                                        key={index}
                                    >
                                        {lecturerRmd.firstName + " " + lecturerRmd.lastName}
                                    </li>
                                ))) :
                                (<div>
                                    <br />
                                    <p>No Lecturer left...</p>
                                </div>)
                            ) : (<div>
                                <br />
                                <p>Please select a course...</p>
                            </div>)}
                        </ul>
                    </div>

                    <div className="col col-1 col-btn">
                        <div className="buttonholder">
                            {currentNotAssignedLecturer ?
                                <button
                                    onClick={(e) => {
                                        e.preventDefault();
                                        this.assignLecturerToCourse(currentNotAssignedLecturer, notAssignedLecturers, assignedLecturers);
                                        }}>
                                    <div className="triangle-right"></div></button>
                                :
                                <button>
                                    <div className="triangle-right notactiveright"></div></button>
                            }

                            <div><br></br></div>

                            {currentLecturerAssigned ?
                                <button
                                    onClick={(e)=>{
                                        e.preventDefault();
                                        this.removeLecturerFromCourse(currentLecturerAssigned, notAssignedLecturers, assignedLecturers);
                                        }}>
                                    <div className="triangle-left"></div></button>
                                :
                                <button>
                                    <div className="triangle-left notactiveleft"></div></button>
                            }
                        </div>
                    </div>
                    
                    <div className="col col-4 list-col">
                        <h4>Assigned Lecturer</h4>

                        <ul className="list-group list-dys">
                            {currentCourse ?
                                assignedLecturers.length > 0 ?
                                    (assignedLecturers &&
                                        assignedLecturers.map((lecturer, index) => (
                                            <li
                                                className={
                                                    "list-group-item " +
                                                    (index === currentLecturerAssignedIndex ? "active" : "")
                                                }
                                                onClick={() => this.setActiveAssignLecturer(lecturer, index)}
                                                onDoubleClick={() => this.removeLecturerFromCourse(lecturer, notAssignedLecturers, assignedLecturers)}
                                                key={index}
                                            >
                                                {lecturer.firstName + " " + lecturer.lastName}
                                            </li>
                                        ))) : (
                                        <div>
                                            <br />
                                            <p>No Lecturer assigned...</p>
                                        </div>
                                    ) : (
                                    <div>
                                        <br />
                                        <p>Please Select a Course...</p>
                                    </div>
                                )}
                        </ul>

                    </div>
                </div>
                <div className="bottomrow">

                    {currentCourse ? <button
                        className="m-3 btn btn-sm btn-danger"
                        onClick={() => this.confirmLecturer(currentCourse, assignedLecturers)}>
                        Confirm
                    </button> : ""}
                </div>

            </div>

        );
    }

}
