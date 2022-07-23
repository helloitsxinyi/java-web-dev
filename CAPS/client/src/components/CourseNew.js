import React, { Component } from "react";
import { Link, withRouter } from "react-router-dom";
import CourseLecturerDataService from "../services/CourseLecturerDataService";
import Navbar from "./Navbar";
import Header from "./Header";

class CourseNew extends Component {
    constructor(props) {
        super(props);
        this.onChangeCode = this.onChangeCode.bind(this);
        this.onChangeTitle = this.onChangeTitle.bind(this);
        this.onChangeDescription = this.onChangeDescription.bind(this);
        this.onChangeCredits = this.onChangeCredits.bind(this);
        this.onChangeCapacity = this.onChangeCapacity.bind(this);
        this.onChangeStatus = this.onChangeStatus.bind(this);
        this.retrieveCourse = this.retrieveCourse.bind(this);
        this.submitForm = this.submitForm.bind(this);

        this.retrieveAssignLecturer = this.retrieveAssignLecturer.bind(this);
        this.retrieveNotAssignLecturer = this.retrieveNotAssignLecturer.bind(this);
        this.retrieveLecturer = this.retrieveLecturer.bind(this);
        this.setActiveAssignLecturer = this.setActiveAssignLecturer.bind(this);
        this.setActiveNotAssignedLecturer = this.setActiveNotAssignedLecturer.bind(this);
        this.assignLecturerToCourse = this.assignLecturerToCourse.bind(this);
        this.removeLecturerFromCourse = this.removeLecturerFromCourse.bind(this);

        this.state = {
            id: this.props.match.params.id,
            course: null,
            courseCode: "",
            courseTitle: "",
            courseDescription: "",
            courseCredits: 0,
            courseCapacity: 0,
            courseStatus: "OPEN",

            changeableId: null,
            notAssignedLecturers: [],
            currentNotAssignedLecturer: null,
            currentNotAssignedLecturerIndex: -1,
            assignedLecturers: [],
            currentLecturerAssigned: null,
            currentLecturerAssignedIndex: -1,
        };
    }

    componentDidMount() {
        if (this.state.id == null) {
            this.setState({
                changeableId: true,
            });
            this.retrieveLecturer();
        } else {

            this.setState({
                changeableId: null,
            });
            this.retrieveCourse(this.state.id);
        }
    }

    onChangeCode = (e) => {
        this.setState({
            courseCode: e.target.value
        });
    }

    onChangeTitle(e) {
        this.setState({
            courseTitle: e.target.value
        })
    }

    onChangeDescription(e) {
        this.setState({
            courseDescription: e.target.value
        })
    }

    onChangeCredits(e) {

        this.setState({
            courseCredits: e.target.value
        });

    }

    onChangeCapacity(e) {
        this.setState({
            courseCapacity: e.target.value
        });
    }

    onChangeStatus(e) {
        this.setState({
            courseStatus: e.target.value
        });
    }

    retrieveCourse(courseCode) {
        CourseLecturerDataService.getCourseById(courseCode).then(
            response => {
                this.setState({
                    course: response.data,
                    courseCode: response.data.courseCode,
                    courseTitle: response.data.courseTitle,
                    courseDescription: response.data.courseDescription,
                    courseCredits: parseInt(response.data.courseCredits),
                    courseCapacity: parseInt(response.data.courseCapacity),
                    courseStatus: response.data.courseStatus,
                    assignedLecturers: response.data.courseLecturers,
                    
                    changeableId: null,
                });
                console.log(response.data);
                //this.retrieveAssignLecturer(this.state.courseCode);
                this.retrieveNotAssignLecturer(this.state.courseCode);
            })
            .catch(e => {
                console.log(e);
                window.location.replace('http://localhost:3000/editCourse');
            });

    }

    retrieveAssignLecturer(courseId) {
        //Retrieve assign lecturers (use for right table)
        CourseLecturerDataService.getLecturersByCourseId(courseId)
            .then(response => {
                this.setState({
                    assignedLecturers: response.data
                });
                console.log(response.data);
                
            })
            .catch(e => {
                console.log(e);
            });
    }

    retrieveNotAssignLecturer(courseId) {
        //Retrieve not assign lecturers (use forleft table)
        CourseLecturerDataService.getAvailLecturersByCourseId(courseId)
            .then(response => {
                this.setState({
                    notAssignedLecturers: response.data
                });
                console.log(response.data);
                
            })
            .catch(e => {
                console.log(e);
            });
    }

    retrieveLecturer() {
        //Retrieve all lecturers
        CourseLecturerDataService.getLecturers()
            .then(response => {
                this.setState({
                    notAssignedLecturers: response.data
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
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

    assignLecturerToCourse(lecturer) {
        //Assign lecturer to course
        var notassignlec = this.state.notAssignedLecturers.filter(x => x.lecturerId != lecturer.lecturerId);
        var assignlec = this.state.assignedLecturers;
        assignlec.push(lecturer)
        this.setState({
            currentNotAssignedLecturer: null,
            currentNotAssignedLecturerIndex: -1,
            assignedLecturers: assignlec,
            notAssignedLecturers: notassignlec,
            changesMade: true,
        });

    }

    removeLecturerFromCourse(lecturer) {
        //Remove the lecturer assigned
        var assignlec = this.state.assignedLecturers.filter(x => x.lecturerId != lecturer.lecturerId);
        var notassignlec = this.state.notAssignedLecturers;
        notassignlec.push(lecturer)
        this.setState({
            currentLecturerAssigned: null,
            currentLecturerAssignedIndex: -1,
            assignedLecturers: assignlec,
            notAssignedLecturers: notassignlec,
            changesMade: true,
        });

    }

    submitForm() {
        var course = {
            courseCode: this.state.courseCode,
            courseTitle: this.state.courseTitle,
            courseDescription: this.state.courseDescription,
            courseCredits: this.state.courseCredits,
            courseCapacity: this.state.courseCapacity,
            courseStatus: this.state.courseStatus,
            courseLecturers: this.state.assignedLecturers
        };
        console.log(course);
        CourseLecturerDataService.putCourse(course).then(
            response => {
                
                console.log(response.data);
                window.location.replace('http://localhost:8080/manage/course/list');
            })
            .catch(e => {
                console.log(e);
                window.alert("Failed to save." + e);
            })
    }


    render() {
        return (
            
            <div>

                
                <div className="container">
                    <div className="row">
                        <div className="col-2"></div>
                        <div className="col-9">
                            <h2>Course Form</h2>
                            <form method="">
                                <div className="row mb-3">
                                    <label htmlFor="courseCode" className="col-sm-3 col-form-label">
                                        Course Code
                                    </label>
                                    {(this.state.changeableId == null) ?
                                        (<div className="col-sm-9">
                                            <input
                                                type="text"
                                                className="form-control"
                                                id="courseCode"
                                                value={this.state.courseCode}
                                                disabled
                                            />
                                        </div>) :
                                        (<div className="col-sm-9">
                                            <input
                                                type="text"
                                                className="form-control"
                                                id="courseCode"
                                                value={this.state.courseCode}
                                                onChange={this.onChangeCode}
                                                name="courseCode"

                                            />
                                        </div>)

                                    }

                                </div>
                                <div className="row mb-3">
                                    <label htmlFor="courseTitle" className="col-sm-3 col-form-label">
                                        Course Title
                                    </label>
                                    <div className="col-sm-9">
                                        <input
                                            type="text"
                                            className="form-control"
                                            id="courseTitle"
                                            value={this.state.courseTitle}
                                            onChange={this.onChangeTitle}
                                            name="courseTitle"
                                        />
                                        <p></p>
                                    </div>
                                </div>
                                <div className="row mb-3">
                                    <label htmlFor="courseDescription" className="col-sm-3 col-form-label">
                                        Course Description
                                    </label>
                                    <div className="col-sm-9">
                                        <input
                                            type="text"
                                            className="form-control"
                                            id="courseDescription"
                                            value={this.state.courseDescription}
                                            onChange={this.onChangeDescription}
                                        />
                                        <p></p>
                                    </div>
                                </div>
                                <div className="row mb-3">
                                    <label htmlFor="courseCredits" className="col-sm-3 col-form-label">
                                        Credits
                                    </label>
                                    <div className="col-sm-9">
                                        <input
                                            type="number"
                                            className="form-control"
                                            id="courseCredits"
                                            value={this.state.courseCredits}
                                            onChange={this.onChangeCredits}
                                        />
                                        <p></p>
                                    </div>
                                </div>
                                <div className="row mb-3">
                                    <label htmlFor="courseCapacity" className="col-sm-3 col-form-label">
                                        Capacity
                                    </label>
                                    <div className="col-sm-9">
                                        <input
                                            type="number"
                                            className="form-control"
                                            id="courseCapacity"
                                            value={this.state.courseCapacity}
                                            onChange={this.onChangeCapacity}
                                        />
                                        <p></p>
                                    </div>
                                </div>
                                <div className="row mb-3">
                                    <label htmlFor="courseStatus" className="col-sm-3 col-form-label">
                                        Status
                                    </label>
                                    <div className="col-sm-9">
                                        <select
                                            className="form-select-lg mb-3"
                                            id="courseStatus"
                                            aria-label="Default select example"
                                            value={this.state.courseStatus}
                                            onChange={this.onChangeStatus}
                                        >
                                            <option value="OPEN">Open</option>
                                            <option value="CLOSE">Close</option>
                                        </select>
                                    </div>
                                </div>

                                <div className="row mb-3">
                                <label className="col-sm-3 col-form-label">
                                        Lecturers:
                                    </label>
                                </div>

                                <div className="row mb-5 flex-nowrap main-dys-form">
                                    <div className="col col-5 list-col">
                                        <b>Available Lecturer</b>
                                        <div className="linebr"><br></br></div>
                                        <ul className="list-group list-dys">
                                            {(this.state.notAssignedLecturers.length > 0 ?
                                                (this.state.notAssignedLecturers && this.state.notAssignedLecturers.map((lecturerRmd, index) => (
                                                    <li
                                                        className={
                                                            "list-group-item " +
                                                            (index === this.state.currentNotAssignedLecturerIndex ? "active" : "")
                                                        }
                                                        onClick={() => this.setActiveNotAssignedLecturer(lecturerRmd, index)}
                                                        onDoubleClick={() => this.assignLecturerToCourse(lecturerRmd)}
                                                        key={index}
                                                    >
                                                        {lecturerRmd.firstName + " " + lecturerRmd.lastName}
                                                    </li>
                                                ))) :
                                                (<div>
                                                    <br />
                                                    <p>No Lecturer left...</p>
                                                </div>)
                                            )}
                                        </ul>
                                    </div>

                                    <div className="col col-1 col-btn">
                                        <div className="buttonholder">
                                            {this.state.currentNotAssignedLecturer ?
                                                <button 
                                                    onClick={(e) => {
                                                        e.preventDefault();
                                                        this.assignLecturerToCourse(this.state.currentNotAssignedLecturer);
                                                        }}>
                                                    <div className="triangle-right"></div></button>
                                                :
                                                <button>
                                                    
                                                    <div className="triangle-right notactiveright"></div></button>
                                            }

                                            <div><br></br></div>

                                            {this.state.currentLecturerAssigned ?
                                                <button
                                                    onClick={(e) => {
                                                        e.preventDefault();
                                                        this.removeLecturerFromCourse(this.state.currentLecturerAssigned);
                                                        }}>
                                                    <div className="triangle-left"></div></button>
                                                :
                                                <button>
                                                   
                                                    <div className="triangle-left notactiveleft"></div></button>
                                            }
                                        </div>
                                    </div>
                                    <div className="linebr"><br></br></div>
                                    <div className="col col-5 list-col">
                                        <b>Assigned Lecturer</b>
                                        <div className="linebr"><br></br></div>
                                        <ul className="list-group list-dys">
                                            {this.state.assignedLecturers.length > 0 ?
                                                (this.state.assignedLecturers &&
                                                    this.state.assignedLecturers.map((lecturer, index) => (
                                                        <li
                                                            className={
                                                                "list-group-item " +
                                                                (index === this.state.currentLecturerAssignedIndex ? "active" : "")
                                                            }
                                                            onClick={() => this.setActiveAssignLecturer(lecturer, index)}
                                                            onDoubleClick={() => this.removeLecturerFromCourse(lecturer)}
                                                            key={index}
                                                        >
                                                            {lecturer.firstName + " " + lecturer.lastName}
                                                        </li>
                                                    ))) : (
                                                    <div>
                                                        <br />
                                                        <p>No Lecturer assigned...</p>
                                                    </div>
                                                )}
                                        </ul>
                                    </div>
                                </div>

                                <input
                                    type="submit"
                                    value="Submit"
                                    className="btn btn-secondary"
                                    onClick={(e) => {
                                        e.preventDefault();
                                        this.submitForm();
                                    }}
                                ></input>
                                &nbsp; &nbsp;
                                <input
                                    type="reset"
                                    value="Reset"
                                    className="btn btn-secondary"
                                    onClick={(e) => {
                                        e.preventDefault();
                                        window.location.reload();
                                    }}
                                ></input>
                            </form>
                        </div>
                        <div className="col-3"></div>
                    </div>
                </div>


            </div>
        )
    }

}
export default withRouter(CourseNew);