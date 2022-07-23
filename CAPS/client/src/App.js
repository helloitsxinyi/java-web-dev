import "./App.css";
import Navbar from "./components/Navbar";
import Header from "./components/Header";
import React from 'react';
import { BrowserRouter as Router, Switch, Route, Link, Redirect, useParams } from 'react-router-dom';
import "bootstrap/dist/css/bootstrap.min.css";
import CourseLecturerList from "./components/CourseLecturerList";
import CourseNew from "./components/CourseNew";
import RedirectPage from "./components/RedirectPage";

function App() {
  return (
    <div className="App">
      <Router>
      <header className="App-header">
        <Header />
        

      </header>
      
        <Switch>
          <Route exact path='/manage/course/courselecturer' component={CourseLecturerList} />
          <Route exact path="/" component={RedirectPage}></Route>
          <Route path="/manage/course/edit/:id" component={CourseNew} />
          <Route exact path="/manage/course/create" component={CourseNew} />
          <Route exact path="/manage/course/edit" component={RedirectPage}/>
            
        </Switch>
      </Router>
    </div>
  );
}

export default App;
